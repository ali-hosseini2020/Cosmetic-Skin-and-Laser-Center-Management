package org.example.cosmeticskinandlasercenter.inventory.dto;

import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private String description;
    private ProductType productType;
    private String brand;
    private BigDecimal unitPrice;
    private StockDTO stock;
    private List<Long> treatmentIds;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}