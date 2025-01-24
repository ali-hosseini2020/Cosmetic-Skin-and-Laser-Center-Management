package org.example.cosmeticskinandlasercenter.staff.repository;

import org.example.cosmeticskinandlasercenter.staff.domain.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    // You can add custom query methods for Specialists here if needed
}