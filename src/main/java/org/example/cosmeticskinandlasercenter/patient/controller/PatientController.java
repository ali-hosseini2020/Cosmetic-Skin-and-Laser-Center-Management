package org.example.cosmeticskinandlasercenter.patient.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientDTO;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientRequest;
import org.example.cosmeticskinandlasercenter.patient.dto.PatientUpdateRequest;
import org.example.cosmeticskinandlasercenter.patient.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for Patient management operations.
 * Exposes API endpoints for creating, retrieving, updating, and deleting Patient resources.
 * Handles HTTP requests, validates input, interacts with the PatientService, and returns responses.
 */
@RestController
@RequestMapping("/api/patients") // Base path for patient endpoints
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;

    /**
     * Registers a new patient.
     * @param request PatientRegistrationRequest object in the request body (validated).
     * @return ResponseEntity containing PatientDTO of the registered patient and HTTP status 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<PatientDTO> registerPatient(@Valid @RequestBody PatientRequest request) {
        log.info("Received request to register new patient: {}", request);
        PatientDTO createdPatient = patientService.registerPatient(request);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    /**
     * Retrieves a patient by their ID.
     * @param id Path variable representing the patient ID.
     * @return ResponseEntity containing PatientDTO for the found patient and HTTP status 200 (OK).
     *         Throws ResourceNotFoundException if patient is not found (handled by GlobalExceptionHandler).
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        log.info("Received request to get patient by ID: {}", id);
        PatientDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }

    /**
     * Retrieves all patients.
     * @return ResponseEntity containing a list of PatientDTOs and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        log.info("Received request to get all patients");
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    /**
     * Updates an existing patient's information.
     * @param id Path variable representing the patient ID to update.
     * @param request PatientUpdateRequest object in the request body (validated).
     * @return ResponseEntity containing PatientDTO for the updated patient and HTTP status 200 (OK).
     *         Throws ResourceNotFoundException if patient is not found (handled by GlobalExceptionHandler).
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientUpdateRequest request) {
        log.info("Received request to update patient with ID: {}, data: {}", id, request);
        PatientDTO updatedPatient = patientService.updatePatient(id, request);
        return ResponseEntity.ok(updatedPatient);
    }

    /**
     * Deletes a patient by their ID.
     * @param id Path variable representing the patient ID to delete.
     * @return ResponseEntity with HTTP status 204 (NO_CONTENT) indicating successful deletion.
     *         Throws ResourceNotFoundException if patient is not found (handled by GlobalExceptionHandler).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.info("Received request to delete patient with ID: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build(); // 204 No Content - successful deletion
    }

    /**
     * Retrieves a patient by their email address.
     * @param email Query parameter for patient email.
     * @return ResponseEntity containing PatientDTO for the found patient and HTTP status 200 (OK), or 404 if not found.
     */
    @GetMapping("/email")
    public ResponseEntity<PatientDTO> getPatientByEmail(@RequestParam String email) {
        log.info("Received request to get patient by email: {}", email);
        PatientDTO patientDTO = patientService.findPatientByEmail(email);
        if (patientDTO != null) {
            return ResponseEntity.ok(patientDTO);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if no patient with that email
        }
    }
}
