package org.example.cosmeticskinandlasercenter.patient.repository;
import org.example.cosmeticskinandlasercenter.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for Patient entity, extending Spring Data JPA's JpaRepository.
 * Provides standard database operations (CRUD) and custom query methods for Patient entities.
 */
@Repository // Indicates this is a Spring Data Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Checks if a patient exists with the given email address.
     * @param email The email address to check.
     * @return true if a patient with the email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Finds a patient by their email address.
     * @param email The email address to search for.
     * @return An Optional containing the Patient if found, or empty if not found.
     */
    Optional<Patient> findByEmail(String email);

    // Spring Data JPA automatically provides methods like:
    // save(), findById(), findAll(), deleteById(), count(), etc.
    // You can add more custom query methods here if needed based on business requirements.
}