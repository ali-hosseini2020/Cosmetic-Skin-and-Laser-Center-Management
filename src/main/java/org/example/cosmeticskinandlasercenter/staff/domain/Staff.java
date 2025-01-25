package org.example.cosmeticskinandlasercenter.staff.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.common.enums.StaffRole;
import org.example.cosmeticskinandlasercenter.medical.domain.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED) // Inheritance strategy for Specialist
public class Staff extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long staffId;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    protected String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    protected String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected StaffRole staffRole;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(nullable = false)
    protected String contactNumber;

    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    protected String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    protected String password; // Store hashed passwords

    protected LocalDate hireDate;

    // Relationship: Staff - Appointment (One-to-Many)
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    protected List<Appointment> appointments;

    // Relationship: Staff - MedicalRecord (One-to-Many) - Assuming staff can create/update medical records
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<MedicalRecord> medicalRecords;
}