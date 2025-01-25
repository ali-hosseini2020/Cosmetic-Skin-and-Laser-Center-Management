package org.example.cosmeticskinandlasercenter.appointment.service;

import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentDTO;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentRequest;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentUpdateRequest;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentRequest request);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO updateAppointment(Long id, AppointmentUpdateRequest request);
    void deleteAppointment(Long id);
}