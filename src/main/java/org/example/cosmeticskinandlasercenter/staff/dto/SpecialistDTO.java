package org.example.cosmeticskinandlasercenter.staff.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialistDTO extends StaffDTO {
    private Long specialistId;
    private String specialization;
    private String certification;
    private List<Long> treatmentIds; // IDs of treatments they are specialized in
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}