package org.example.cosmeticskinandlasercenter.medical.repository;

import org.example.cosmeticskinandlasercenter.medical.domain.TreatmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {
}
