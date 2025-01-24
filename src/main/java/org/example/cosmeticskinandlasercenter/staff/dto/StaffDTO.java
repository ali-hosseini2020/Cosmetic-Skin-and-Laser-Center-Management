package org.example.cosmeticskinandlasercenter.staff.dto;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.StaffRole;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StaffDTO {
    private Long staffId;
    private String firstName;
    private String lastName;
    private StaffRole staffRole;
    private String contactNumber;
    private String email;
    private LocalDate hireDate;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}