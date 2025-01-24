package org.example.cosmeticskinandlasercenter.staff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.common.exception.BusinessException;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
import org.example.cosmeticskinandlasercenter.staff.domain.Specialist;
import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.staff.dto.*;
import org.example.cosmeticskinandlasercenter.staff.mapper.SpecialistMapper;
import org.example.cosmeticskinandlasercenter.staff.mapper.StaffMapper;
import org.example.cosmeticskinandlasercenter.staff.repository.SpecialistRepository;
import org.example.cosmeticskinandlasercenter.staff.repository.StaffRepository;
//import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.example.cosmeticskinandlasercenter.treatment.repository.TreatmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final SpecialistRepository specialistRepository;
    private final StaffMapper staffMapper;
    private final SpecialistMapper specialistMapper;
    private final PasswordEncoder passwordEncoder;
    private final TreatmentRepository treatmentRepository;

    @Override
    public StaffDTO createStaff(StaffRequest staffRequest) {
        log.info("Creating new staff: {}", staffRequest);
        if (staffRepository.existsByEmail(staffRequest.getEmail())) {
            throw new BusinessException("Email already exists", HttpStatus.CONFLICT);
        }
        Staff staff = staffMapper.toEntity(staffRequest);
        staff.setPassword(passwordEncoder.encode(staffRequest.getPassword())); // Hash password
        Staff savedStaff = staffRepository.save(staff);
        return staffMapper.toDTO(savedStaff);
    }

    @Override
    public SpecialistDTO createSpecialist(SpecialistRequest specialistRequest) {
        log.info("Creating new specialist: {}", specialistRequest);
        if (staffRepository.existsByEmail(specialistRequest.getEmail())) {
            throw new BusinessException("Email already exists", HttpStatus.CONFLICT);
        }
        Specialist specialist = specialistMapper.toEntity(specialistRequest);
        specialist.setPassword(passwordEncoder.encode(specialistRequest.getPassword()));

        if (specialistRequest.getTreatmentIds() != null) {
            List<Treatment> treatments = treatmentRepository.findAllById(specialistRequest.getTreatmentIds());
            specialist.setTreatments(treatments);
        }

        Specialist savedSpecialist = specialistRepository.save(specialist);
        return specialistMapper.toDTO(savedSpecialist);
    }

    @Override
    public StaffDTO getStaffById(Long id) {
        log.info("Retrieving staff by ID: {}", id);
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return staffMapper.toDTO(staff);
    }

    @Override
    public SpecialistDTO getSpecialistById(Long id) {
        log.info("Retrieving specialist by ID: {}", id);
        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist not found with id: " + id));
        return specialistMapper.toDTO(specialist);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        log.info("Retrieving all staff");
        return staffRepository.findAll().stream()
                .map(staffMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialistDTO> getAllSpecialists() {
        log.info("Retrieving all specialists");
        return specialistRepository.findAll().stream()
                .map(specialistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDTO updateStaff(Long id, StaffUpdateRequest staffUpdateRequest) {
        log.info("Updating staff with ID: {}, data: {}", id, staffUpdateRequest);
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id + " for update"));
        staffMapper.toEntity(staffUpdateRequest, staff);
        if (staffUpdateRequest.getPassword() != null && !staffUpdateRequest.getPassword().isEmpty()) {
            staff.setPassword(passwordEncoder.encode(staffUpdateRequest.getPassword()));
        }
        Staff updatedStaff = staffRepository.save(staff);
        return staffMapper.toDTO(updatedStaff);
    }

    @Override
    public SpecialistDTO updateSpecialist(Long id, SpecialistUpdateRequest specialistUpdateRequest) {
        log.info("Updating specialist with ID: {}, data: {}", id, specialistUpdateRequest);
        Specialist specialist = specialistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialist not found with id: " + id + " for update"));

        specialistMapper.toEntity(specialistUpdateRequest, specialist);
        if (specialistUpdateRequest.getPassword() != null && !specialistUpdateRequest.getPassword().isEmpty()) {
            specialist.setPassword(passwordEncoder.encode(specialistUpdateRequest.getPassword()));
        }

        if (specialistUpdateRequest.getTreatmentIds() != null) {
            List<Treatment> treatments = treatmentRepository.findAllById(specialistUpdateRequest.getTreatmentIds());
            specialist.setTreatments(treatments);
        } else {
            specialist.getTreatments().clear(); // If no treatmentIds provided in update, clear existing treatments
        }

        Specialist updatedSpecialist = specialistRepository.save(specialist);
        return specialistMapper.toDTO(updatedSpecialist);
    }

    @Override
    public void deleteStaff(Long id) {
        log.info("Deleting staff with ID: {}", id);
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff not found with id: " + id + " for deletion");
        }
        staffRepository.deleteById(id);
    }
}