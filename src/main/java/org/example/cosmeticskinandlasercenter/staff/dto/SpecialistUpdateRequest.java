package org.example.cosmeticskinandlasercenter.staff.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialistUpdateRequest extends StaffUpdateRequest {
    private String specialization;
    private String certification;
    private List<Long> treatmentIds;
}