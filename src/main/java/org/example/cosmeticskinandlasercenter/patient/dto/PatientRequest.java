package org.example.cosmeticskinandlasercenter.patient.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cosmeticskinandlasercenter.common.enums.BloodType;
import org.example.cosmeticskinandlasercenter.common.enums.Gender;
import java.time.LocalDate;

/**
 * Request object for patient registration.
 * Carries data from the client request to the service layer for creating a new Patient.
 * Includes validation annotations to ensure data integrity at the controller level.
 */
@Data
@NoArgsConstructor
public class PatientRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    private BloodType bloodType;
}