package org.example.cosmeticskinandlasercenter.medical.repository;

import org.example.cosmeticskinandlasercenter.medical.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}