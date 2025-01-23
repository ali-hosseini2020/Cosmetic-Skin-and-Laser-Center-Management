package org.example.cosmeticskinandlasercenter.treatment.mapper;

import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.example.cosmeticskinandlasercenter.treatment.dto.TreatmentDTO;
import org.example.cosmeticskinandlasercenter.treatment.dto.TreatmentRequest;
import org.example.cosmeticskinandlasercenter.treatment.dto.TreatmentUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface using MapStruct for Treatment entity.
 */
@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    TreatmentDTO toDTO(Treatment treatment);

    Treatment toEntity(TreatmentRequest request);

    Treatment toEntity(TreatmentUpdateRequest request, @MappingTarget Treatment treatment);
}