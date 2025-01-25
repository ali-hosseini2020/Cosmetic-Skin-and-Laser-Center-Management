package org.example.cosmeticskinandlasercenter.inventory.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.cosmeticskinandlasercenter.common.audit.AuditableEntity;
import org.example.cosmeticskinandlasercenter.common.enums.StockLevel;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "product") // Exclude product from equals/hashCode to avoid circular references
@ToString(callSuper = true, exclude = "product") // Exclude product from toString
public class Stock extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    @Column(nullable = false)
    private int quantityInStock;

    private int reorderPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockLevel stockLevel;

    private String location; // e.g., "Storage Room A", "Display Shelf 1"

    private String notes;
}