package org.example.cosmeticskinandlasercenter.medical.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;

import java.time.LocalDateTime;

@Entity
@Table(name = "treatment_histories")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TreatmentHistory extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff; // Staff member who performed the treatment

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment; // Appointment during which treatment was performed

    private LocalDateTime treatmentDate;

    @Column(length = 2000)
    private String notes; // Notes about this specific treatment instance

    @Column(length = 1000)
    private String outcome; // Result or outcome of the treatment
}
