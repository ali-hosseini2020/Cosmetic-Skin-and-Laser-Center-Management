package org.example.cosmeticskinandlasercenter.inventory.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.ProductType;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private String productName;
    private String description;
    private ProductType productType;
    private String brand;

    @PositiveOrZero(message = "Unit price must be positive or zero")
    private BigDecimal unitPrice;
}
