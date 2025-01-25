package org.example.cosmeticskinandlasercenter.appointment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentDTO;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentRequest;
import org.example.cosmeticskinandlasercenter.appointment.dto.AppointmentUpdateRequest;
import org.example.cosmeticskinandlasercenter.appointment.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@Valid @RequestBody AppointmentRequest request) {
        log.info("Received request to create new appointment: {}", request);
        AppointmentDTO createdAppointment = appointmentService.createAppointment(request);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        log.info("Received request to get appointment by ID: {}", id);
        AppointmentDTO appointmentDTO = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        log.info("Received request to get all appointments");
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateRequest request) {
        log.info("Received request to update appointment with ID: {}, data: {}", id, request);
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, request);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        log.info("Received request to delete appointment with ID: {}", id);
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}