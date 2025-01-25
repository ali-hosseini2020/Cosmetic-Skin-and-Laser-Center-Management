package org.example.cosmeticskinandlasercenter.inventory.mapper;

import org.example.cosmeticskinandlasercenter.inventory.domain.Product;
import org.example.cosmeticskinandlasercenter.inventory.dto.ProductDTO;
import org.example.cosmeticskinandlasercenter.inventory.dto.ProductRequest;
import org.example.cosmeticskinandlasercenter.inventory.dto.ProductUpdateRequest;
import org.example.cosmeticskinandlasercenter.treatment.domain.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StockMapper.class})
public interface ProductMapper {

    @Mapping(source = "stock", target = "stock")
    @Mapping(target = "treatmentIds", ignore = true)
    ProductDTO toDTO(Product product);

    @Mapping(target = "treatments", ignore = true)
    @Mapping(target = "stock", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(target = "treatments", ignore = true)
    @Mapping(target = "stock", ignore = true)
    void updateEntityFromDTO(ProductUpdateRequest request, @MappingTarget Product product);

    default List<Long> mapTreatmentIds(List<Treatment> treatments) {
        if (treatments == null) {
            return null;
        }
        return treatments.stream()
                .map(Treatment::getTreatmentId)
                .collect(Collectors.toList());
    }
}