package org.example.cosmeticskinandlasercenter.treatment.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Request object for updating an existing TreatmentPlan.
 */
@Data
@NoArgsConstructor
public class TreatmentPlanUpdateRequest {
    private String planName;
    private String notes;
    private List<Long> treatmentIds;
}
