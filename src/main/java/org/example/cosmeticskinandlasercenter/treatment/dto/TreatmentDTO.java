package org.example.cosmeticskinandlasercenter.treatment.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cosmeticskinandlasercenter.common.enums.TreatmentType;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Treatment entity.
 */
@Data
@NoArgsConstructor
public class TreatmentDTO {
    private Long treatmentId;
    private String treatmentName;
    private String description;
    private TreatmentType treatmentType;
    private Duration durationEstimate;
    private BigDecimal costEstimate;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}