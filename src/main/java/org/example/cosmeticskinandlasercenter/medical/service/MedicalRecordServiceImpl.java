package org.example.cosmeticskinandlasercenter.medical.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
import org.example.cosmeticskinandlasercenter.medical.domain.MedicalRecord;
import org.example.cosmeticskinandlasercenter.medical.domain.TreatmentHistory;
import org.example.cosmeticskinandlasercenter.medical.dto.*;
import org.example.cosmeticskinandlasercenter.medical.mapper.MedicalRecordMapper;
import org.example.cosmeticskinandlasercenter.medical.mapper.TreatmentHistoryMapper;
import org.example.cosmeticskinandlasercenter.medical.repository.MedicalRecordRepository;
import org.example.cosmeticskinandlasercenter.medical.repository.TreatmentHistoryRepository;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import org.example.cosmeticskinandlasercenter.patient.repository.PatientRepository;
import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.staff.repository.StaffRepository;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.example.cosmeticskinandlasercenter.treatment.repository.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final TreatmentHistoryRepository treatmentHistoryRepository;
    private final PatientRepository patientRepository;
    private final TreatmentRepository treatmentRepository;
    private final StaffRepository staffRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final TreatmentHistoryMapper treatmentHistoryMapper;

    @Override
    public MedicalRecordDTO createMedicalRecord(MedicalRecordRequest request) {
        log.info("Creating medical record for patient ID: {}", request.getPatientId());
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + request.getPatientId()));

        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(request);
        medicalRecord.setPatient(patient);

        MedicalRecord savedRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toDTO(savedRecord);
    }

    @Override
    public MedicalRecordDTO getMedicalRecordById(Long id) {
        log.info("Retrieving medical record with ID: {}", id);
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with ID: " + id));
        return medicalRecordMapper.toDTO(medicalRecord);
    }

    @Override
    public MedicalRecordDTO updateMedicalRecord(Long id, MedicalRecordUpdateRequest request) {
        log.info("Updating medical record with ID: {}", id);
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with ID: " + id));
        medicalRecordMapper.updateEntityFromDTO(request, medicalRecord);
        MedicalRecord updatedRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toDTO(updatedRecord);
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        log.info("Deleting medical record with ID: {}", id);
        if (!medicalRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medical record not found with ID: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public TreatmentHistoryDTO addTreatmentHistory(Long recordId, TreatmentHistoryRequest request) {
        log.info("Adding treatment history to medical record ID: {}", recordId);
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with ID: " + recordId));

        TreatmentHistory treatmentHistory = treatmentHistoryMapper.toEntity(request);
        treatmentHistory.setMedicalRecord(medicalRecord);

        if (request.getTreatmentId() != null) {
            Treatment treatment = treatmentRepository.findById(request.getTreatmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + request.getTreatmentId()));
            treatmentHistory.setTreatment(treatment);
        }

        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));
            treatmentHistory.setStaff(staff);
        }

        if (request.getAppointmentId() != null) {
            Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + request.getAppointmentId()));
            treatmentHistory.setAppointment(appointment);
        }

        TreatmentHistory savedHistory = treatmentHistoryRepository.save(treatmentHistory);
        return treatmentHistoryMapper.toDTO(savedHistory);
    }

    @Override
    public TreatmentHistoryDTO updateTreatmentHistory(Long recordId, Long historyId, TreatmentHistoryUpdateRequest request) {
        log.info("Updating treatment history with ID: {} for medical record ID: {}", historyId, recordId);

        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with ID: " + recordId));

        TreatmentHistory treatmentHistory = treatmentHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment history not found with ID: " + historyId));

        if (!treatmentHistory.getMedicalRecord().getRecordId().equals(recordId)) {
            throw new ResourceNotFoundException("Treatment history with ID: " + historyId + " does not belong to medical record with ID: " + recordId);
        }

        treatmentHistoryMapper.updateEntityFromDTO(request, treatmentHistory);

        // Handle setting related entities if needed in the update

        if (request.getTreatmentId() != null) {
            Treatment treatment = treatmentRepository.findById(request.getTreatmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + request.getTreatmentId()));
            treatmentHistory.setTreatment(treatment);
        }

        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));
            treatmentHistory.setStaff(staff);
        }

        if (request.getAppointmentId() != null) {
            Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + request.getAppointmentId()));
            treatmentHistory.setAppointment(appointment);
        }

        TreatmentHistory updatedHistory = treatmentHistoryRepository.save(treatmentHistory);
        return treatmentHistoryMapper.toDTO(updatedHistory);
    }

    @Override
    public void deleteTreatmentHistory(Long recordId, Long historyId) {
        log.info("Deleting treatment history with ID: {} from medical record ID: {}", historyId, recordId);
        TreatmentHistory treatmentHistory = treatmentHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment history not found with ID: " + historyId));

        if (!treatmentHistory.getMedicalRecord().getRecordId().equals(recordId)) {
            throw new ResourceNotFoundException("Treatment history with ID: " + historyId + " does not belong to medical record with ID: " + recordId);
        }

        treatmentHistoryRepository.delete(treatmentHistory);
    }

    @Override
    public List<TreatmentHistoryDTO> getTreatmentHistoriesByRecordId(Long recordId) {
        log.info("Retrieving treatment histories for medical record ID: {}", recordId);
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with ID: " + recordId));

        return medicalRecord.getTreatmentHistories().stream()
                .map(treatmentHistoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}