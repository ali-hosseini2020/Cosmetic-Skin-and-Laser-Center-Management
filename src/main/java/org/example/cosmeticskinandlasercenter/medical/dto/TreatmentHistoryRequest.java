package org.example.cosmeticskinandlasercenter.medical.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TreatmentHistoryRequest {
    @NotNull
    private Long recordId;

    @NotNull
    private Long treatmentId;

    private Long staffId; // Staff member who performed the treatment

    private Long appointmentId; // Appointment during which treatment was performed

    @NotNull
    private LocalDateTime treatmentDate;

    private String notes;
    private String outcome;
}