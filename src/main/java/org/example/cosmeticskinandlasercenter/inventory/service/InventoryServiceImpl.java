package org.example.cosmeticskinandlasercenter.inventory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.common.enums.StockLevel;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
import org.example.cosmeticskinandlasercenter.inventory.domain.Product;
import org.example.cosmeticskinandlasercenter.inventory.domain.Stock;
import org.example.cosmeticskinandlasercenter.inventory.dto.*;
import org.example.cosmeticskinandlasercenter.inventory.mapper.ProductMapper;
import org.example.cosmeticskinandlasercenter.inventory.mapper.StockMapper;
import org.example.cosmeticskinandlasercenter.inventory.repository.ProductRepository;
import org.example.cosmeticskinandlasercenter.inventory.repository.StockRepository;
import org.example.cosmeticskinandlasercenter.treatment.repository.TreatmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final ProductMapper productMapper;
    private final StockMapper stockMapper;
    private final TreatmentRepository treatmentRepository;

    @Override
    public ProductDTO createProduct(ProductRequest request) {
        log.info("Creating new product: {}", request);
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);

        // Create and associate an initial Stock entity with default values
        Stock stock = new Stock();
        stock.setProduct(savedProduct);
        stock.setQuantityInStock(0); // Default to 0 on creation
        stock.setStockLevel(StockLevel.OUT_OF_STOCK); // Or another appropriate default
        stock.setReorderPoint(0); // Default reorder point
        stock.setLocation("N/A"); // Default location
        stock.setNotes("");
        stockRepository.save(stock);

        // Set the created stock to the product
        savedProduct.setStock(stock);

        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("Retrieving product by ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("Retrieving all products");
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateRequest request) {
        log.info("Updating product with ID: {}, data: {}", id, request);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        productMapper.updateEntityFromDTO(request, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public StockDTO createStock(StockRequest request) {
        log.info("Creating new stock entry: {}", request);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getProductId()));

        Stock stock = stockMapper.toEntity(request);
        stock.setProduct(product);
        Stock savedStock = stockRepository.save(stock);
        return stockMapper.toDTO(savedStock);
    }

    @Override
    public StockDTO getStockById(Long id) {
        log.info("Retrieving stock by ID: {}", id);
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        return stockMapper.toDTO(stock);
    }

    @Override
    public StockDTO updateStock(Long id, StockUpdateRequest request) {
        log.info("Updating stock with ID: {}, data: {}", id, request);
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));

        stockMapper.updateEntityFromDTO(request, stock);
        Stock updatedStock = stockRepository.save(stock);
        return stockMapper.toDTO(updatedStock);
    }
}
