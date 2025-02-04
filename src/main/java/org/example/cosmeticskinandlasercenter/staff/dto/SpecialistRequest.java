package org.example.cosmeticskinandlasercenter.staff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialistRequest extends StaffRequest {

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "Certification is required")
    private String certification;
    private List<Long> treatmentIds; // IDs of treatments they are specialized in
}