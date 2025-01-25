package org.example.cosmeticskinandlasercenter.billing.service;
import org.example.cosmeticskinandlasercenter.billing.dto.*;
import java.util.List;

public interface BillingService {
    InvoiceDTO createInvoice(InvoiceRequest request);
    InvoiceDTO getInvoiceById(Long id);
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO updateInvoice(Long id, InvoiceUpdateRequest request);
    void deleteInvoice(Long id);

    PaymentDTO createPayment(PaymentRequest request);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
    PaymentDTO updatePayment(Long id, PaymentUpdateRequest request);
    void deletePayment(Long id);
}
