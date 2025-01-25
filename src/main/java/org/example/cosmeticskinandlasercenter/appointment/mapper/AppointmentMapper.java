package org.example.cosmeticskinandlasercenter.appointment.mapper;

import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentDTO;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentRequest;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "patient.patientId", target = "patientId")
    @Mapping(source = "staff.staffId", target = "staffId")
    @Mapping(source = "treatment.treatmentId", target = "treatmentId")
    AppointmentDTO toDTO(Appointment appointment);

    @Mapping(target = "patient", ignore = true) // Set in service
    @Mapping(target = "staff", ignore = true)   // Set in service
    @Mapping(target = "treatment", ignore = true) // Set in service
    Appointment toEntity(AppointmentRequest request);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "treatment", ignore = true)
    void updateEntityFromDTO(AppointmentUpdateRequest request, @MappingTarget Appointment appointment);
}
