package org.example.cosmeticskinandlasercenter.treatment.dto;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cosmeticskinandlasercenter.common.enums.TreatmentType;
import java.math.BigDecimal;
import java.time.Duration;

/**
 * Request object for updating an existing Treatment.
 */
@Data
@NoArgsConstructor
public class TreatmentUpdateRequest {
    private String treatmentName;
    private String description;
    private TreatmentType treatmentType;
    private Duration durationEstimate;
    @Positive(message = "Cost estimate must be a positive value")
    private BigDecimal costEstimate;
}
