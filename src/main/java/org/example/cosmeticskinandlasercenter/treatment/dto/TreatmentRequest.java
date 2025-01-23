package org.example.cosmeticskinandlasercenter.treatment.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cosmeticskinandlasercenter.common.enums.TreatmentType;
import java.math.BigDecimal;
import java.time.Duration;

/**
 * Request object for creating a new Treatment.
 */
@Data
@NoArgsConstructor
public class TreatmentRequest {

    @NotBlank(message = "Treatment name is required")
    private String treatmentName;

    private String description;

    @NotNull(message = "Treatment type is required")
    private TreatmentType treatmentType;

    private Duration durationEstimate;

    @Positive(message = "Cost estimate must be a positive value")
    private BigDecimal costEstimate;
}