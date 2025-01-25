package org.example.cosmeticskinandlasercenter.billing.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.billing.dto.*;
import org.example.cosmeticskinandlasercenter.billing.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
@Slf4j
public class BillingController {

    private final BillingService billingService;

    // Invoice Endpoints
    @PostMapping("/invoices")
    public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceRequest request) {
        log.info("Received request to create new invoice: {}", request);
        InvoiceDTO createdInvoice = billingService.createInvoice(request);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        log.info("Received request to get invoice by ID: {}", id);
        InvoiceDTO invoiceDTO = billingService.getInvoiceById(id);
        return ResponseEntity.ok(invoiceDTO);
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        log.info("Received request to get all invoices");
        List<InvoiceDTO> invoices = billingService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceUpdateRequest request) {
        log.info("Received request to update invoice with ID: {}, data: {}", id, request);
        InvoiceDTO updatedInvoice = billingService.updateInvoice(id, request);
        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        log.info("Received request to delete invoice with ID: {}", id);
        billingService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    // Payment Endpoints
    @PostMapping("/payments")
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentRequest request) {
        log.info("Received request to create new payment: {}", request);
        PaymentDTO createdPayment = billingService.createPayment(request);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        log.info("Received request to get payment by ID: {}", id);
        PaymentDTO paymentDTO = billingService.getPaymentById(id);
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        log.info("Received request to get all payments");
        List<PaymentDTO> payments = billingService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentUpdateRequest request) {
        log.info("Received request to update payment with ID: {}, data: {}", id, request);
        PaymentDTO updatedPayment = billingService.updatePayment(id, request);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        log.info("Received request to delete payment with ID: {}", id);
        billingService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}