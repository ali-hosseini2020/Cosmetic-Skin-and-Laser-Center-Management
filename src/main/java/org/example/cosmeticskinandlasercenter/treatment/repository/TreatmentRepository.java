package org.example.cosmeticskinandlasercenter.treatment.repository;

import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Treatment entity.
 */
@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    // Custom query methods can be added if needed
}