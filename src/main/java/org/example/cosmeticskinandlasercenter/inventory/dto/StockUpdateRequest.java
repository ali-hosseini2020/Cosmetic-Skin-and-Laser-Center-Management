package org.example.cosmeticskinandlasercenter.inventory.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.example.cosmeticskinandlasercenter.common.enums.StockLevel;

@Data
public class StockUpdateRequest {

    @Min(value = 0, message = "Quantity in stock cannot be negative")
    private int quantityInStock;

    @Min(value = 0, message = "Reorder point cannot be negative")
    private int reorderPoint;

    private StockLevel stockLevel;
    private String location;
    private String notes;
}