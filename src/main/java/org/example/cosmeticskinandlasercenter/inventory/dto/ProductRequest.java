package org.example.cosmeticskinandlasercenter.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.ProductType;
import org.example.cosmeticskinandlasercenter.common.enums.StockLevel;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @NotNull(message = "Product type is required")
    private ProductType productType;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Unit price is required")
    @PositiveOrZero(message = "Unit price must be positive or zero")
    private BigDecimal unitPrice;

}