package org.example.cosmeticskinandlasercenter.medical.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;

import java.util.List;

@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MedicalRecord extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", unique = true)
    private Patient patient;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreatmentHistory> treatmentHistories;

    @Column(length = 1000) // Adjust length as needed
    private String allergies;

    @Column(length = 1000) // Adjust length as needed
    private String medicalConditions;

    @Column(length = 1000) // Adjust length as needed
    private String currentMedications;

    @Column(length = 2000) // Adjust length as needed
    private String notes; // General notes about the patient's medical record
}