package org.example.cosmeticskinandlasercenter.treatment.repository;

import org.example.cosmeticskinandlasercenter.treatment.domain.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for TreatmentPlan entity.
 */
@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
    // Custom query methods can be added if needed
}