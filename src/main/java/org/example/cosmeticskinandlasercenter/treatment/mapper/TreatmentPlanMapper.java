package org.example.cosmeticskinandlasercenter.treatment.mapper;
import org.example.cosmeticskinandlasercenter.treatment.domain.TreatmentPlan;
import org.example.cosmeticskinandlasercenter.treatment.dto.TreatmentPlanDTO;
import org.example.cosmeticskinandlasercenter.treatment.dto.TreatmentPlanRequest;
import org.example.cosmeticskinandlasercenter.treatment.dto.TreatmentPlanUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface using MapStruct for TreatmentPlan entity.
 */
@Mapper(componentModel = "spring", uses = {TreatmentMapper.class})
public interface TreatmentPlanMapper {

    @Mapping(source = "patient.patientId", target = "patientId")
    TreatmentPlanDTO toDTO(TreatmentPlan treatmentPlan);

    @Mapping(target = "patient", ignore = true) // Handled in service to fetch Patient entity
    @Mapping(target = "treatments", ignore = true) // Handled in service to fetch Treatment entities
    TreatmentPlan toEntity(TreatmentPlanRequest request);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "treatments", ignore = true)
    TreatmentPlan toEntity(TreatmentPlanUpdateRequest request, @MappingTarget TreatmentPlan treatmentPlan);
}