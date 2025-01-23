package org.example.cosmeticskinandlasercenter.treatment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.common.enums.TreatmentType;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List; // Import List

@Entity
@Table(name = "treatments")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Treatment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatmentId;

    @Column(nullable = false)
    private String treatmentName;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TreatmentType treatmentType;

    private Duration durationEstimate;
    private BigDecimal costEstimate;

    @Column(updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    // Relationship: Treatment - TreatmentPlan (Many-to-Many - Inverse side)
    @ManyToMany(mappedBy = "treatments", fetch = FetchType.LAZY)
    private List<TreatmentPlan> treatmentPlans;
}
