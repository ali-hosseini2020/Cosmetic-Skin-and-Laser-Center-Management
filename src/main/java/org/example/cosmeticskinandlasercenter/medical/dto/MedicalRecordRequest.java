package org.example.cosmeticskinandlasercenter.medical.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicalRecordRequest {
    @NotNull
    private Long patientId; // ID of the patient the record is for
    private String allergies;
    private String medicalConditions;
    private String currentMedications;
    private String notes;
}