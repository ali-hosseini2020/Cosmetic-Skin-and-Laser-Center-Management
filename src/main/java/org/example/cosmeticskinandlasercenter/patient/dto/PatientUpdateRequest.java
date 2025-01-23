package org.example.cosmeticskinandlasercenter.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cosmeticskinandlasercenter.common.enums.BloodType;
import org.example.cosmeticskinandlasercenter.common.enums.Gender;

import java.time.LocalDate;

/**
 * Request object for updating patient information.
 * Carries updated patient data from the client request to the service layer for updating an existing Patient.
 * Includes validation annotations for relevant fields.
 */
@Data
@NoArgsConstructor
public class PatientUpdateRequest {

    private String firstName;
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private Gender gender;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    private BloodType bloodType;
}