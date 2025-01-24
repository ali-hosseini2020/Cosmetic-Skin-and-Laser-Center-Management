package org.example.cosmeticskinandlasercenter.staff.mapper;

import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.staff.dto.StaffDTO;
import org.example.cosmeticskinandlasercenter.staff.dto.StaffRequest;
import org.example.cosmeticskinandlasercenter.staff.dto.StaffUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffDTO toDTO(Staff staff);
    Staff toEntity(StaffRequest staffRequest);
    void toEntity(StaffUpdateRequest staffUpdateRequest, @MappingTarget Staff staff);
}