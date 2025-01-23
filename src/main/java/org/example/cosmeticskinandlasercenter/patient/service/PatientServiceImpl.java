package org.example.cosmeticskinandlasercenter.patient.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.common.exception.BusinessException;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientDTO;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientRequest;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientUpdateRequest;
import org.example.cosmeticskinandlasercenter.patient.mapper.PatientMapper;
import org.example.cosmeticskinandlasercenter.patient.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for Patient management operations.
 * Implements the PatientService interface and contains the business logic for patient-related operations.
 * Interacts with PatientRepository for data access and PatientMapper for object conversion.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // Default transaction management for service methods
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO registerPatient(PatientRequest request) {
        log.info("Registering new patient: {}", request);
        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Patient with this email already exists", HttpStatus.CONFLICT);
        }
        Patient patient = patientMapper.toEntity(request);
        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient registered successfully with ID: {}", savedPatient.getPatientId());
        return patientMapper.toDTO(savedPatient);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        log.info("Retrieving patient by ID: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return patientMapper.toDTO(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        log.info("Retrieving all patients");
        return patientRepository.findAll().stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientUpdateRequest request) {
        log.info("Updating patient with ID: {}, data: {}", id, request);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id + " for update"));

        patientMapper.toEntity(request, patient); // Apply updates from request to existing patient entity
        Patient updatedPatient = patientRepository.save(patient);
        log.info("Patient updated successfully with ID: {}", updatedPatient.getPatientId());
        return patientMapper.toDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        log.info("Deleting patient with ID: {}", id);
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id + " for deletion");
        }
        patientRepository.deleteById(id);
        log.info("Patient deleted successfully with ID: {}", id);
    }

    @Override
    public PatientDTO findPatientByEmail(String email) {
        log.info("Finding patient by email: {}", email);
        return patientRepository.findByEmail(email)
                .map(patientMapper::toDTO)
                .orElse(null); // Or throw ResourceNotFoundException if you expect patient to always be found
    }
}