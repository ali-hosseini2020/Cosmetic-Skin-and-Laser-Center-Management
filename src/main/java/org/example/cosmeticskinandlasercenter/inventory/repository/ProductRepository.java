package org.example.cosmeticskinandlasercenter.inventory.repository;

import org.example.cosmeticskinandlasercenter.inventory.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}