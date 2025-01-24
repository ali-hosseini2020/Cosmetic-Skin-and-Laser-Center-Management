package org.example.cosmeticskinandlasercenter.medical.mapper;

import org.example.cosmeticskinandlasercenter.appointment.mapper.AppointmentMapper;
import org.example.cosmeticskinandlasercenter.medical.domain.TreatmentHistory;
import org.example.cosmeticskinandlasercenter.medical.dto.TreatmentHistoryDTO;
import org.example.cosmeticskinandlasercenter.medical.dto.TreatmentHistoryRequest;
import org.example.cosmeticskinandlasercenter.medical.dto.TreatmentHistoryUpdateRequest;
import org.example.cosmeticskinandlasercenter.staff.mapper.StaffMapper;
import org.example.cosmeticskinandlasercenter.treatment.mapper.TreatmentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MedicalRecordMapper.class, TreatmentMapper.class, StaffMapper.class, AppointmentMapper.class})
public interface TreatmentHistoryMapper {

    @Mapping(source = "medicalRecord.recordId", target = "recordId")
    @Mapping(source = "treatment.treatmentId", target = "treatmentId")
    @Mapping(source = "staff.staffId", target = "staffId")
    @Mapping(source = "appointment.appointmentId", target = "appointmentId")
    TreatmentHistoryDTO toDTO(TreatmentHistory treatmentHistory);

    @Mapping(target = "medicalRecord", ignore = true) // Handled in service
    @Mapping(target = "treatment", ignore = true) // Handled in service
    @Mapping(target = "staff", ignore = true) // Handled in service
    @Mapping(target = "appointment", ignore = true) // Handled in service
    TreatmentHistory toEntity(TreatmentHistoryRequest request);

    @Mapping(target = "medicalRecord", ignore = true)
    @Mapping(target = "treatment", ignore = true)
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    void updateEntityFromDTO(TreatmentHistoryUpdateRequest request, @MappingTarget TreatmentHistory treatmentHistory);
}