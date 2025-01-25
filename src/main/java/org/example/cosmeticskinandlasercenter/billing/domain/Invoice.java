package org.example.cosmeticskinandlasercenter.billing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.common.enums.InvoiceStatus;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Invoice extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment; // The appointment this invoice is for

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    private BigDecimal discount;

    private BigDecimal tax;

    @Column(nullable = false)
    private BigDecimal netAmount; // Calculated amount after discount and tax

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    private LocalDate dueDate;

    @Column(length = 2000)
    private String notes;

    // Relationship: Invoice - Payment (One-to-Many)
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payment> payments;
}
