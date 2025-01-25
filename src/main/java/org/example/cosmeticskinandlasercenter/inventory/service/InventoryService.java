package org.example.cosmeticskinandlasercenter.inventory.service;

import org.example.cosmeticskinandlasercenter.inventory.dto.*;

import java.util.List;

public interface InventoryService {
    ProductDTO createProduct(ProductRequest request);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductUpdateRequest request);
    void deleteProduct(Long id);

    StockDTO createStock(StockRequest request);
    StockDTO getStockById(Long id);
    StockDTO updateStock(Long id, StockUpdateRequest request);
}