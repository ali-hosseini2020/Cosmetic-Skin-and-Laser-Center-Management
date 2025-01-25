package org.example.cosmeticskinandlasercenter.appointment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentDTO;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentRequest;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentUpdateRequest;
import org.example.cosmeticskinandlasercenter.appointment.mapper.AppointmentMapper;
import org.example.cosmeticskinandlasercenter.appointment.repository.AppointmentRepository;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
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
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final TreatmentRepository treatmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDTO createAppointment(AppointmentRequest request) {
        log.info("Creating new appointment: {}", request);
        Appointment appointment = appointmentMapper.toEntity(request);

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + request.getPatientId()));
        appointment.setPatient(patient);

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));
        appointment.setStaff(staff);

        Treatment treatment = treatmentRepository.findById(request.getTreatmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + request.getTreatmentId()));
        appointment.setTreatment(treatment);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(savedAppointment);
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        log.info("Retrieving appointment by ID: {}", id);
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        return appointmentMapper.toDTO(appointment);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        log.info("Retrieving all appointments");
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentUpdateRequest request) {
        log.info("Updating appointment with ID: {}, data: {}", id, request);
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));

        appointmentMapper.updateEntityFromDTO(request, appointment);

        // Update relationships if needed in the request
        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));
            appointment.setStaff(staff);
        }

        if (request.getTreatmentId() != null) {
            Treatment treatment = treatmentRepository.findById(request.getTreatmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with ID: " + request.getTreatmentId()));
            appointment.setTreatment(treatment);
        }

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        log.info("Deleting appointment with ID: {}", id);
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}