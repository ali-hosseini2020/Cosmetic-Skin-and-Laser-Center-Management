package org.example.cosmeticskinandlasercenter.treatment.domain;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import java.time.LocalDateTime;
import java.util.List; // Import List
/**
 * Represents a Treatment Plan for a Patient in the system.
 * A treatment plan can include multiple treatments.
 */

@Entity
@Table(name = "treatment_plans")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TreatmentPlan extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    private String planName;
    private String notes; // Additional notes about the treatment plan

    @Column(updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    // Relationship: TreatmentPlan - Patient (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Relationship: TreatmentPlan - Treatment (Many-to-Many)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plan_treatments",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id"))
    private List<Treatment> treatments;
}