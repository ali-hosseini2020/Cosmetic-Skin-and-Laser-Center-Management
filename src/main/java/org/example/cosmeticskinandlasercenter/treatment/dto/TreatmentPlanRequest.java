package org.example.cosmeticskinandlasercenter.treatment.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Request object for creating a new TreatmentPlan.
 */
@Data
@NoArgsConstructor
public class TreatmentPlanRequest {

    @NotBlank(message = "Plan name is required")
    private String planName;

    private String notes;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    private List<Long> treatmentIds;
}