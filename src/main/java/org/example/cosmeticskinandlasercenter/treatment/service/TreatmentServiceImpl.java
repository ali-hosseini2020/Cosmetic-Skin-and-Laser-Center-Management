package org.example.cosmeticskinandlasercenter.treatment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import org.example.cosmeticskinandlasercenter.patient.repository.PatientRepository;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.example.cosmeticskinandlasercenter.treatment.domain.TreatmentPlan;
import org.example.cosmeticskinandlasercenter.treatment.dto.*;
import org.example.cosmeticskinandlasercenter.treatment.mapper.TreatmentMapper;
import org.example.cosmeticskinandlasercenter.treatment.mapper.TreatmentPlanMapper;
import org.example.cosmeticskinandlasercenter.treatment.repository.TreatmentPlanRepository;
import org.example.cosmeticskinandlasercenter.treatment.repository.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for Treatment management operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final TreatmentMapper treatmentMapper;
    private final TreatmentPlanMapper treatmentPlanMapper;
    private final PatientRepository patientRepository;

    @Override
    public TreatmentDTO createTreatment(TreatmentRequest request) {
        log.info("Creating new treatment: {}", request);
        Treatment treatment = treatmentMapper.toEntity(request);
        Treatment savedTreatment = treatmentRepository.save(treatment);
        return treatmentMapper.toDTO(savedTreatment);
    }

    @Override
    public TreatmentDTO getTreatmentById(Long id) {
        log.info("Retrieving treatment by ID: {}", id);
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + id));
        return treatmentMapper.toDTO(treatment);
    }

    @Override
    public List<TreatmentDTO> getAllTreatments() {
        log.info("Retrieving all treatments");
        return treatmentRepository.findAll().stream()
                .map(treatmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TreatmentDTO updateTreatment(Long id, TreatmentUpdateRequest request) {
        log.info("Updating treatment with ID: {}, data: {}", id, request);
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + id + " for update"));
        treatmentMapper.toEntity(request, treatment);
        Treatment updatedTreatment = treatmentRepository.save(treatment);
        return treatmentMapper.toDTO(updatedTreatment);
    }

    @Override
    public void deleteTreatment(Long id) {
        log.info("Deleting treatment with ID: {}", id);
        if (!treatmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Treatment not found with id: " + id + " for deletion");
        }
        treatmentRepository.deleteById(id);
    }

    @Override
    public TreatmentPlanDTO createTreatmentPlan(TreatmentPlanRequest request) {
        log.info("Creating new treatment plan: {}", request);
        TreatmentPlan treatmentPlan = treatmentPlanMapper.toEntity(request);

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId() + " for treatment plan"));
        treatmentPlan.setPatient(patient);

        if (request.getTreatmentIds() != null && !request.getTreatmentIds().isEmpty()) {
            List<Treatment> treatments = treatmentRepository.findAllById(request.getTreatmentIds());
            treatmentPlan.setTreatments(treatments);
        }

        TreatmentPlan savedTreatmentPlan = treatmentPlanRepository.save(treatmentPlan);
        return treatmentPlanMapper.toDTO(savedTreatmentPlan);
    }

    @Override
    public TreatmentPlanDTO getTreatmentPlanById(Long id) {
        log.info("Retrieving treatment plan by ID: {}", id);
        TreatmentPlan treatmentPlan = treatmentPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment Plan not found with id: " + id));
        return treatmentPlanMapper.toDTO(treatmentPlan);
    }

    @Override
    public List<TreatmentPlanDTO> getAllTreatmentPlans() {
        log.info("Retrieving all treatment plans");
        return treatmentPlanRepository.findAll().stream()
                .map(treatmentPlanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TreatmentPlanDTO updateTreatmentPlan(Long id, TreatmentPlanUpdateRequest request) {
        log.info("Updating treatment plan with ID: {}, data: {}", id, request);
        TreatmentPlan treatmentPlan = treatmentPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TreatmentPlan not found with id: " + id + " for update"));

        treatmentPlanMapper.toEntity(request, treatmentPlan);

        if (request.getTreatmentIds() != null && !request.getTreatmentIds().isEmpty()) {
            List<Treatment> treatments = treatmentRepository.findAllById(request.getTreatmentIds());
            treatmentPlan.setTreatments(treatments);
        } else {
            treatmentPlan.getTreatments().clear(); // If no treatmentIds provided in update, clear existing treatments
        }

        TreatmentPlan updatedTreatmentPlan = treatmentPlanRepository.save(treatmentPlan);
        return treatmentPlanMapper.toDTO(updatedTreatmentPlan);
    }

    @Override
    public void deleteTreatmentPlan(Long id) {
        log.info("Deleting treatment plan with ID: {}", id);
        if (!treatmentPlanRepository.existsById(id)) {
            throw new ResourceNotFoundException("TreatmentPlan not found with id: " + id + " for deletion");
        }
        treatmentPlanRepository.deleteById(id);
    }
}
