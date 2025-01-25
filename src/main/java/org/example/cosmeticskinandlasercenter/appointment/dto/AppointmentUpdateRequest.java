package org.example.cosmeticskinandlasercenter.appointment.dto;

import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.AppointmentStatus;

import java.time.LocalDateTime;

@Data
public class AppointmentUpdateRequest {
    private Long staffId;
    private Long treatmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;
    private String notes;
}