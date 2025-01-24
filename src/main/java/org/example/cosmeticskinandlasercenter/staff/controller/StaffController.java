package org.example.cosmeticskinandlasercenter.staff.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.staff.dto.*;
import org.example.cosmeticskinandlasercenter.staff.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@Slf4j
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody StaffRequest staffRequest) {
        log.info("Received request to create new staff: {}", staffRequest);
        StaffDTO createdStaff = staffService.createStaff(staffRequest);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @PostMapping("/specialists")
    public ResponseEntity<SpecialistDTO> createSpecialist(@Valid @RequestBody SpecialistRequest specialistRequest) {
        log.info("Received request to create new specialist: {}", specialistRequest);
        SpecialistDTO createdSpecialist = staffService.createSpecialist(specialistRequest);
        return new ResponseEntity<>(createdSpecialist, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable Long id) {
        log.info("Received request to get staff by ID: {}", id);
        StaffDTO staffDTO = staffService.getStaffById(id);
        return ResponseEntity.ok(staffDTO);
    }

    @GetMapping("/specialists/{id}")
    public ResponseEntity<SpecialistDTO> getSpecialistById(@PathVariable Long id) {
        log.info("Received request to get specialist by ID: {}", id);
        SpecialistDTO specialistDTO = staffService.getSpecialistById(id);
        return ResponseEntity.ok(specialistDTO);
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        log.info("Received request to get all staff");
        List<StaffDTO> staff = staffService.getAllStaff();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/specialists")
    public ResponseEntity<List<SpecialistDTO>> getAllSpecialists() {
        log.info("Received request to get all specialists");
        List<SpecialistDTO> specialists = staffService.getAllSpecialists();
        return ResponseEntity.ok(specialists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffUpdateRequest staffUpdateRequest) {
        log.info("Received request to update staff with ID: {}, data: {}", id, staffUpdateRequest);
        StaffDTO updatedStaff = staffService.updateStaff(id, staffUpdateRequest);
        return ResponseEntity.ok(updatedStaff);
    }

    @PutMapping("/specialists/{id}")
    public ResponseEntity<SpecialistDTO> updateSpecialist(@PathVariable Long id, @Valid @RequestBody SpecialistUpdateRequest specialistUpdateRequest) {
        log.info("Received request to update specialist with ID: {}, data: {}", id, specialistUpdateRequest);
        SpecialistDTO updatedSpecialist = staffService.updateSpecialist(id, specialistUpdateRequest);
        return ResponseEntity.ok(updatedSpecialist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        log.info("Received request to delete staff with ID: {}", id);
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}