package org.example.cosmeticskinandlasercenter.medical.mapper;

import org.example.cosmeticskinandlasercenter.medical.domain.MedicalRecord;
import org.example.cosmeticskinandlasercenter.medical.dto.MedicalRecordDTO;
import org.example.cosmeticskinandlasercenter.medical.dto.MedicalRecordRequest;
import org.example.cosmeticskinandlasercenter.medical.dto.MedicalRecordUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TreatmentHistoryMapper.class})
public interface MedicalRecordMapper {

    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);

    MedicalRecord toEntity(MedicalRecordRequest request);

    void updateEntityFromDTO(MedicalRecordUpdateRequest request, @MappingTarget MedicalRecord medicalRecord);
}