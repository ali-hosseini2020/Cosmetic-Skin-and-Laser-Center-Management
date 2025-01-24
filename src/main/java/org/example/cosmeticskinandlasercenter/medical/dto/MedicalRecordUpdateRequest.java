package org.example.cosmeticskinandlasercenter.medical.dto;

import lombok.Data;

@Data
public class MedicalRecordUpdateRequest {
    private String allergies;
    private String medicalConditions;
    private String currentMedications;
    private String notes;
}