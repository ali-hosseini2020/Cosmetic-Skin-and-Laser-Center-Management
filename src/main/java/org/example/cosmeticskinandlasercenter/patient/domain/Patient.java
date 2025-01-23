package org.example.cosmeticskinandlasercenter.patient.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.common.enums.BloodType;
import org.example.cosmeticskinandlasercenter.common.enums.Gender;
import org.example.cosmeticskinandlasercenter.treatment.domain.TreatmentPlan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Patient extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits") // Example: 10 digits only
    @Column(nullable = false)
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Column(updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    // Relationship: Patient - MedicalRecord (One-to-One)
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private MedicalRecord medicalRecord;

    // Relationship: Patient - Appointment (One-to-Many)
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    // Relationship: Patient - TreatmentPlan (One-to-Many)
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TreatmentPlan> treatmentPlans; // List of treatment plans for this patient

    // Relationship: Patient - Invoice (One-to-Many)
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    @PrePersist
    protected void onCreate() {
        registrationDate = LocalDateTime.now();
    }
}