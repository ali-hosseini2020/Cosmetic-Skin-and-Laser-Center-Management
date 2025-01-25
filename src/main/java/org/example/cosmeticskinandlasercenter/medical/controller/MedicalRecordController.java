package org.example.cosmeticskinandlasercenter.medical.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.medical.dto.*;
import org.example.cosmeticskinandlasercenter.medical.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@Valid @RequestBody MedicalRecordRequest request) {
        log.info("Received request to create medical record: {}", request);
        MedicalRecordDTO createdRecord = medicalRecordService.createMedicalRecord(request);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordById(@PathVariable Long id) {
        log.info("Received request to get medical record by ID: {}", id);
        MedicalRecordDTO recordDTO = medicalRecordService.getMedicalRecordById(id);
        return ResponseEntity.ok(recordDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@PathVariable Long id, @Valid @RequestBody MedicalRecordUpdateRequest request) {
        log.info("Received request to update medical record with ID: {}, data: {}", id, request);
        MedicalRecordDTO updatedRecord = medicalRecordService.updateMedicalRecord(id, request);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        log.info("Received request to delete medical record with ID: {}", id);
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{recordId}/treatment-histories")
    public ResponseEntity<TreatmentHistoryDTO> addTreatmentHistory(@PathVariable Long recordId, @Valid @RequestBody TreatmentHistoryRequest request) {
        log.info("Received request to add treatment history to medical record ID: {}: {}", recordId, request);
        TreatmentHistoryDTO addedHistory = medicalRecordService.addTreatmentHistory(recordId, request);
        return new ResponseEntity<>(addedHistory, HttpStatus.CREATED);
    }

    @PutMapping("/{recordId}/treatment-histories/{historyId}")
    public ResponseEntity<TreatmentHistoryDTO> updateTreatmentHistory(@PathVariable Long recordId, @PathVariable Long historyId, @Valid @RequestBody TreatmentHistoryUpdateRequest request) {
        log.info("Received request to update treatment history with ID: {} for medical record ID: {}: {}", historyId, recordId, request);
        TreatmentHistoryDTO updatedHistory = medicalRecordService.updateTreatmentHistory(recordId, historyId, request);
        return ResponseEntity.ok(updatedHistory);
    }

    @DeleteMapping("/{recordId}/treatment-histories/{historyId}")
    public ResponseEntity<Void> deleteTreatmentHistory(@PathVariable Long recordId, @PathVariable Long historyId) {
        log.info("Received request to delete treatment history with ID: {} from medical record ID: {}", historyId, recordId);
        medicalRecordService.deleteTreatmentHistory(recordId, historyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{recordId}/treatment-histories")
    public ResponseEntity<List<TreatmentHistoryDTO>> getTreatmentHistoriesByRecordId(@PathVariable Long recordId) {
        log.info("Received request to get treatment histories for medical record ID: {}", recordId);
        List<TreatmentHistoryDTO> treatmentHistories = medicalRecordService.getTreatmentHistoriesByRecordId(recordId);
        return ResponseEntity.ok(treatmentHistories);
    }
}