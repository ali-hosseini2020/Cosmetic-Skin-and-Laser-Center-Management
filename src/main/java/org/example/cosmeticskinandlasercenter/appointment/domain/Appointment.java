package org.example.cosmeticskinandlasercenter.appointment.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.common.enums.AppointmentStatus;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Appointment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id", nullable = false)
    private Treatment treatment;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(length = 2000)
    private String notes;
}