package org.example.cosmeticskinandlasercenter.staff.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.StaffRole;

import java.time.LocalDate;

@Data
public class StaffUpdateRequest {
    private String firstName;
    private String lastName;
    private StaffRole staffRole;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    private String password; // Allow updating password
    private LocalDate hireDate;
}
