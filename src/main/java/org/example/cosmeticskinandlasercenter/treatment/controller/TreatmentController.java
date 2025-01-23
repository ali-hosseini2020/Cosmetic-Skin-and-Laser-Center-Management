package org.example.cosmeticskinandlasercenter.treatment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.treatment.dto.*;
import org.example.cosmeticskinandlasercenter.treatment.service.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Treatment management operations.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TreatmentController {

    private final TreatmentService treatmentService;

    // Treatment Endpoints

    @PostMapping("/treatments")
    public ResponseEntity<TreatmentDTO> createTreatment(@Valid @RequestBody TreatmentRequest request) {
        log.info("Received request to create new treatment: {}", request);
        TreatmentDTO createdTreatment = treatmentService.createTreatment(request);
        return new ResponseEntity<>(createdTreatment, HttpStatus.CREATED);
    }

    @GetMapping("/treatments/{id}")
    public ResponseEntity<TreatmentDTO> getTreatmentById(@PathVariable Long id) {
        log.info("Received request to get treatment by ID: {}", id);
        TreatmentDTO treatmentDTO = treatmentService.getTreatmentById(id);
        return ResponseEntity.ok(treatmentDTO);
    }

    @GetMapping("/treatments")
    public ResponseEntity<List<TreatmentDTO>> getAllTreatments() {
        log.info("Received request to get all treatments");
        List<TreatmentDTO> treatments = treatmentService.getAllTreatments();
        return ResponseEntity.ok(treatments);
    }

    @PutMapping("/treatments/{id}")
    public ResponseEntity<TreatmentDTO> updateTreatment(@PathVariable Long id, @Valid @RequestBody TreatmentUpdateRequest request) {
        log.info("Received request to update treatment with ID: {}, data: {}", id, request);
        TreatmentDTO updatedTreatment = treatmentService.updateTreatment(id, request);
        return ResponseEntity.ok(updatedTreatment);
    }

    @DeleteMapping("/treatments/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id) {
        log.info("Received request to delete treatment with ID: {}", id);
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }

    // Treatment Plan Endpoints

    @PostMapping("/treatment-plans")
    public ResponseEntity<TreatmentPlanDTO> createTreatmentPlan(@Valid @RequestBody TreatmentPlanRequest request) {
        log.info("Received request to create new treatment plan: {}", request);
        TreatmentPlanDTO createdPlan = treatmentService.createTreatmentPlan(request);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @GetMapping("/treatment-plans/{id}")
    public ResponseEntity<TreatmentPlanDTO> getTreatmentPlanById(@PathVariable Long id) {
        log.info("Received request to get treatment plan by ID: {}", id);
        TreatmentPlanDTO treatmentPlanDTO = treatmentService.getTreatmentPlanById(id);
        return ResponseEntity.ok(treatmentPlanDTO);
    }

    @GetMapping("/treatment-plans")
    public ResponseEntity<List<TreatmentPlanDTO>> getAllTreatmentPlans() {
        log.info("Received request to get all treatment plans");
        List<TreatmentPlanDTO> treatmentPlans = treatmentService.getAllTreatmentPlans();
        return ResponseEntity.ok(treatmentPlans);
    }

    @PutMapping("/treatment-plans/{id}")
    public ResponseEntity<TreatmentPlanDTO> updateTreatmentPlan(@PathVariable Long id, @Valid @RequestBody TreatmentPlanUpdateRequest request) {
        log.info("Received request to update treatment plan with ID: {}, data: {}", id, request);
        TreatmentPlanDTO updatedPlan = treatmentService.updateTreatmentPlan(id, request);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/treatment-plans/{id}")
    public ResponseEntity<Void> deleteTreatmentPlan(@PathVariable Long id) {
        log.info("Received request to delete treatment plan with ID: {}", id);
        treatmentService.deleteTreatmentPlan(id);
        return ResponseEntity.noContent().build();
    }
}
