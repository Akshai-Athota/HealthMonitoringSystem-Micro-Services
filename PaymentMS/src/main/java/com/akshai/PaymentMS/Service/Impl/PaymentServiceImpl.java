package com.akshai.PaymentMS.Service.Impl;

import com.akshai.PaymentMS.Client.BillingClient;
import com.akshai.PaymentMS.Client.PatientClient;
import com.akshai.PaymentMS.Entities.Payment;
import com.akshai.PaymentMS.Exception.PaymentNotFoundException;
import com.akshai.PaymentMS.Mapper.PaymentMapper;
import com.akshai.PaymentMS.Publisher.NotificationPublisher;
import com.akshai.PaymentMS.Repository.PaymentRepository;
import com.akshai.PaymentMS.Service.PaymentService;
import com.akshai.PaymentMS.dto.InvoiceDTO;
import com.akshai.PaymentMS.dto.NotificationDTO;
import com.akshai.PaymentMS.dto.PatientDTO;
import com.akshai.PaymentMS.dto.PaymentDTO;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentMapper paymentMapper;
    private PaymentRepository paymentRepository;
    private BillingClient billingClient;
    private PatientClient patientClient;
    private NotificationPublisher notificationPublisher;

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByInvoiceId(Long invoiceId) {
        return paymentRepository.findByInvoiceId(invoiceId)
                .stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDTO)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    @Override
    @Retry(name = "paymentService", fallbackMethod = "processPaymentFallback")
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        InvoiceDTO invoice = billingClient.getInvoiceById(paymentDTO.getInvoiceId());
        if (!"Unpaid".equals(invoice.getStatus())) {
            throw new IllegalStateException("Invoice is already paid or cancelled");
        }

        paymentDTO.setPaymentDate(new Date());
        paymentDTO.setStatus("Success");

        // Save the payment
        Payment payment = paymentMapper.toEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);

        // Update the invoice status in the Billing Service
        invoice.setStatus("Paid");
        billingClient.updateInvoice(invoice.getId(), invoice);

        PatientDTO patientDTO = patientClient.getPatientById(invoice.getPatientId());

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationType("Email");
        notificationDTO.setMessage("Payment done successful");
        notificationDTO.setRecipient(patientDTO.getEmail());
        notificationPublisher.sendEmailNotification(notificationDTO);

        return paymentMapper.toDTO(savedPayment);
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

        payment.setInvoiceId(paymentDTO.getInvoiceId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setStatus(paymentDTO.getStatus());
        payment.setTransactionId(paymentDTO.getTransactionId());

        Payment updatedPayment = paymentRepository.save(payment);
        return paymentMapper.toDTO(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public PaymentDTO processPaymentFallback(PaymentDTO paymentDTO, Throwable throwable) {
        log.error("Failed to process payment: {}", throwable.getMessage());
        paymentDTO.setStatus("Failed");
        return paymentDTO;
    }
}
