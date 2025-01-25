package org.example.cosmeticskinandlasercenter.inventory.repository;

import org.example.cosmeticskinandlasercenter.inventory.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


    // Additional custom queries can be added here if needed

}
