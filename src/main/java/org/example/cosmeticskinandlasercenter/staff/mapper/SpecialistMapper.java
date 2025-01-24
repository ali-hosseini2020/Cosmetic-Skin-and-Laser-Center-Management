package org.example.cosmeticskinandlasercenter.staff.mapper;

import org.example.cosmeticskinandlasercenter.staff.domain.Specialist;
import org.example.cosmeticskinandlasercenter.staff.dto.SpecialistDTO;
import org.example.cosmeticskinandlasercenter.staff.dto.SpecialistRequest;
import org.example.cosmeticskinandlasercenter.staff.dto.SpecialistUpdateRequest;
import org.example.cosmeticskinandlasercenter.treatment.mapper.TreatmentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TreatmentMapper.class})
public interface SpecialistMapper {

    @Mapping(target = "treatmentIds", ignore = true)
    SpecialistDTO toDTO(Specialist specialist);

    @Mapping(target = "staffRole", ignore = true) // Assuming role is set in StaffRequest
    Specialist toEntity(SpecialistRequest specialistRequest);

    @Mapping(target = "staffRole", ignore = true)
    void toEntity(SpecialistUpdateRequest specialistUpdateRequest, @MappingTarget Specialist specialist);
}