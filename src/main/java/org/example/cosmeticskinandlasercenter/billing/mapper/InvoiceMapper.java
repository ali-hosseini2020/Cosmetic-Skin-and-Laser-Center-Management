package org.example.cosmeticskinandlasercenter.billing.mapper;
import org.example.cosmeticskinandlasercenter.billing.domain.Invoice;
import org.example.cosmeticskinandlasercenter.billing.dto.InvoiceDTO;
import org.example.cosmeticskinandlasercenter.billing.dto.InvoiceRequest;
import org.example.cosmeticskinandlasercenter.billing.dto.InvoiceUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PaymentMapper.class})
public interface InvoiceMapper {

    @Mapping(source = "patient.patientId", target = "patientId")
    @Mapping(source = "appointment.appointmentId", target = "appointmentId")
    InvoiceDTO toDTO(Invoice invoice);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    Invoice toEntity(InvoiceRequest request);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    void updateEntityFromDTO(InvoiceUpdateRequest request, @MappingTarget Invoice invoice);
}