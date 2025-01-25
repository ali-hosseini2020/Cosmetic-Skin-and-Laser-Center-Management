package org.example.cosmeticskinandlasercenter.inventory.dto;

import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.StockLevel;

import java.time.LocalDateTime;

@Data
public class StockDTO {
    private Long stockId;
    private Long productId;
    private int quantityInStock;
    private int reorderPoint;
    private StockLevel stockLevel;
    private String location;
    private String notes;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}