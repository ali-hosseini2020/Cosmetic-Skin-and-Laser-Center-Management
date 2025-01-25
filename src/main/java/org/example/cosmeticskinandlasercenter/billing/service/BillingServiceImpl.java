package org.example.cosmeticskinandlasercenter.billing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.appointment.domain.Appointment;
import org.example.cosmeticskinandlasercenter.appointment.repository.AppointmentRepository;
import org.example.cosmeticskinandlasercenter.billing.domain.Invoice;
import org.example.cosmeticskinandlasercenter.billing.domain.Payment;
import org.example.cosmeticskinandlasercenter.billing.dto.*;
import org.example.cosmeticskinandlasercenter.billing.mapper.InvoiceMapper;
import org.example.cosmeticskinandlasercenter.billing.mapper.PaymentMapper;
import org.example.cosmeticskinandlasercenter.billing.repository.BillingRepository;
import org.example.cosmeticskinandlasercenter.billing.repository.PaymentRepository;
import org.example.cosmeticskinandlasercenter.common.exception.ResourceNotFoundException;
import org.example.cosmeticskinandlasercenter.patient.repository.PatientRepository;
import org.example.cosmeticskinandlasercenter.staff.domain.Staff;
import org.example.cosmeticskinandlasercenter.staff.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;
    private final PaymentRepository paymentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final InvoiceMapper invoiceMapper;
    private final PaymentMapper paymentMapper;
    private final StaffRepository staffRepository;

    @Override
    public InvoiceDTO createInvoice(InvoiceRequest request) {
        log.info("Creating new invoice: {}", request);
        Invoice invoice = invoiceMapper.toEntity(request);
        invoice.setPatient(patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId())));
        if (request.getAppointmentId() != null) {
            Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + request.getAppointmentId()));
            invoice.setAppointment(appointment);
        }
        Invoice savedInvoice = billingRepository.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        log.info("Retrieving invoice by ID: {}", id);
        Invoice invoice = billingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        log.info("Retrieving all invoices");
        return billingRepository.findAll().stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO updateInvoice(Long id, InvoiceUpdateRequest request) {
        log.info("Updating invoice with ID: {}, data: {}", id, request);
        Invoice invoice = billingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id + " for update"));
        invoiceMapper.updateEntityFromDTO(request, invoice);
        Invoice updatedInvoice = billingRepository.save(invoice);
        return invoiceMapper.toDTO(updatedInvoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        log.info("Deleting invoice with ID: {}", id);
        if (!billingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invoice not found with id: " + id + " for deletion");
        }
        billingRepository.deleteById(id);
    }

    @Override
    public PaymentDTO createPayment(PaymentRequest request) {
        log.info("Creating new payment: {}", request);
        Payment payment = paymentMapper.toEntity(request);

        Invoice invoice = billingRepository.findById(request.getInvoiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + request.getInvoiceId()));
        payment.setInvoice(invoice);

        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + request.getStaffId()));
            payment.setStaff(staff);
        }

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDTO(savedPayment);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        log.info("Retrieving payment by ID: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        log.info("Retrieving all payments");
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentUpdateRequest request) {
        log.info("Updating payment with ID: {}, data: {}", id, request);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id + " for update"));

        paymentMapper.updateEntityFromDTO(request, payment);

        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + request.getStaffId()));
            payment.setStaff(staff);
        }

        Payment updatedPayment = paymentRepository.save(payment);
        return paymentMapper.toDTO(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        log.info("Deleting payment with ID: {}", id);
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id: " + id + " for deletion");
        }
        paymentRepository.deleteById(id);
    }
}