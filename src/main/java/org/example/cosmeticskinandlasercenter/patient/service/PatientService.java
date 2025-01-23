package org.example.cosmeticskinandlasercenter.patient.service;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientDTO;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientRequest;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientUpdateRequest;

import java.util.List;

/**
 * Service interface for Patient management operations.
 * Defines the contract for patient-related business logic operations.
 */
public interface PatientService {

    /**
     * Registers a new patient.
     * @param request PatientRegistrationRequest containing patient details.
     * @return PatientDTO representing the newly registered patient.
     */
    PatientDTO registerPatient(PatientRequest request);

    /**
     * Retrieves a patient by their ID.
     * @param id The ID of the patient to retrieve.
     * @return PatientDTO representing the patient, or throws ResourceNotFoundException if not found.
     */
    PatientDTO getPatientById(Long id);

    /**
     * Retrieves all patients.
     * @return List of PatientDTOs representing all patients.
     */
    List<PatientDTO> getAllPatients();

    /**
     * Updates an existing patient's information.
     * @param id The ID of the patient to update.
     * @param request PatientUpdateRequest containing updated patient details.
     * @return PatientDTO representing the updated patient, or throws ResourceNotFoundException if not found.
     */
    PatientDTO updatePatient(Long id, PatientUpdateRequest request);

    /**
     * Deletes a patient by their ID.
     * @param id The ID of the patient to delete.
     */
    void deletePatient(Long id);

    /**
     * Finds a patient by their email address.
     * @param email The email address to search for.
     * @return PatientDTO representing the patient, or null if not found.
     */
    PatientDTO findPatientByEmail(String email);
}