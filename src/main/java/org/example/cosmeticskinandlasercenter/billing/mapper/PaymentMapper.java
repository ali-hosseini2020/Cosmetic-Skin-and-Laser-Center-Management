package org.example.cosmeticskinandlasercenter.billing.mapper;
import org.example.cosmeticskinandlasercenter.billing.domain.Payment;
import org.example.cosmeticskinandlasercenter.billing.dto.PaymentDTO;
import org.example.cosmeticskinandlasercenter.billing.dto.PaymentRequest;
import org.example.cosmeticskinandlasercenter.billing.dto.PaymentUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "invoice.invoiceId", target = "invoiceId")
    @Mapping(source = "staff.staffId", target = "staffId")
    PaymentDTO toDTO(Payment payment);

    @Mapping(target = "invoice", ignore = true) // Handled in service
    @Mapping(target = "staff", ignore = true)
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "staff", ignore = true)
    void updateEntityFromDTO(PaymentUpdateRequest request, @MappingTarget Payment payment);
}