package org.example.cosmeticskinandlasercenter.billing.repository;
import org.example.cosmeticskinandlasercenter.billing.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Invoice, Long> {
}