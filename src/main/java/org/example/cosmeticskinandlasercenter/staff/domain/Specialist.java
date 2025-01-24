package org.example.cosmeticskinandlasercenter.staff.domain;
import jakarta.persistence.*;
import lombok.*;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;

import java.util.List;

@Entity
@Table(name = "specialists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Specialist extends Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialistId;

    private String specialization; // e.g., Dermatology, Laser Therapy

    private String certification; // e.g., Board Certified Dermatologist

    // Relationship: Specialist - Treatment (Many-to-Many) - Assuming specialists can perform certain treatments
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "specialist_treatments",
            joinColumns = @JoinColumn(name = "specialist_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
    private List<Treatment> treatments;
}