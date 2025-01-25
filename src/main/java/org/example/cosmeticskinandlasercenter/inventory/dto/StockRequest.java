package org.example.cosmeticskinandlasercenter.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.StockLevel;

@Data
public class StockRequest {
    @NotNull
    private Long productId;

    @NotNull
    @Min(value = 0, message = "Quantity in stock cannot be negative")
    private int quantityInStock;

    @Min(value = 0, message = "Reorder point cannot be negative")
    private int reorderPoint;

    @NotNull
    private StockLevel stockLevel;

    private String location;
    private String notes;
}