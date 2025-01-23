package org.example.cosmeticskinandlasercenter.patient.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cosmeticskinandlasercenter.common.enums.BloodType;
import org.example.cosmeticskinandlasercenter.common.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Patient entity.
 * Used to transfer Patient data between layers (e.g., service to controller, controller to client).
 */
@Data
@NoArgsConstructor
public class PatientDTO {
    private Long patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String contactNumber;
    private String email;
    private String address;
    private BloodType bloodType;
    private LocalDateTime registrationDate;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}