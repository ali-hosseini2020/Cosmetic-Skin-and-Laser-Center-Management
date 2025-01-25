package org.example.cosmeticskinandlasercenter.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.inventory.dto.*;
import org.example.cosmeticskinandlasercenter.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    // Product Endpoints
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductRequest request) {
        log.info("Received request to create new product: {}", request);
        ProductDTO createdProduct = inventoryService.createProduct(request);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        log.info("Received request to get product by ID: {}", id);
        ProductDTO productDTO = inventoryService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Received request to get all products");
        List<ProductDTO> products = inventoryService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
        log.info("Received request to update product with ID: {}, data: {}", id, request);
        ProductDTO updatedProduct = inventoryService.updateProduct(id, request);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Received request to delete product with ID: {}", id);
        inventoryService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Stock Endpoints
    @PostMapping("/stock")
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockRequest request) {
        log.info("Received request to create new stock entry: {}", request);
        StockDTO createdStock = inventoryService.createStock(request);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Long id) {
        log.info("Received request to get stock by ID: {}", id);
        StockDTO stockDTO = inventoryService.getStockById(id);
        return ResponseEntity.ok(stockDTO);
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @Valid @RequestBody StockUpdateRequest request) {
        log.info("Received request to update stock with ID: {}, data: {}", id, request);
        StockDTO updatedStock = inventoryService.updateStock(id, request);
        return ResponseEntity.ok(updatedStock);
    }
}