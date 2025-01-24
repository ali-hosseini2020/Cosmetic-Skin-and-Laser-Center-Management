package org.example.cosmeticskinandlasercenter.medical.dto;

import lombok.Data;
import java.util.List;

@Data
public class MedicalRecordDTO {
    private Long recordId;
    private Long patientId; // Or PatientDTO, if you need to include more patient details
    private List<TreatmentHistoryDTO> treatmentHistories;
    private String allergies;
    private String medicalConditions;
    private String currentMedications;
    private String notes;
    private String createdBy;
    private String lastModifiedBy;
}