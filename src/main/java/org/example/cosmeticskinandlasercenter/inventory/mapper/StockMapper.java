package org.example.cosmeticskinandlasercenter.inventory.mapper;

import org.example.cosmeticskinandlasercenter.inventory.domain.Stock;
import org.example.cosmeticskinandlasercenter.inventory.dto.StockDTO;
import org.example.cosmeticskinandlasercenter.inventory.dto.StockRequest;
import org.example.cosmeticskinandlasercenter.inventory.dto.StockUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(source = "product.productId", target = "productId")
    StockDTO toDTO(Stock stock);

    @Mapping(target = "product", ignore = true)
    Stock toEntity(StockRequest request);

    @Mapping(target = "product", ignore = true)
    void updateEntityFromDTO(StockUpdateRequest request, @MappingTarget Stock stock);
}
