package org.example.cosmeticskinandlasercenter.appointment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.AppointmentStatus;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    @NotNull
    private Long patientId;

    @NotNull
    private Long staffId;

    @NotNull
    private Long treatmentId;

    @NotNull
    @Future
    private LocalDateTime startTime;

    @NotNull
    @Future
    private LocalDateTime endTime;

    @NotNull
    private AppointmentStatus status;

    private String notes;
}