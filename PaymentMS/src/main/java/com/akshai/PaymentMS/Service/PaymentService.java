package com.akshai.PaymentMS.Service;

import com.akshai.PaymentMS.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> getAllPayments();
    List<PaymentDTO> getPaymentsByInvoiceId(Long invoiceId);
    PaymentDTO getPaymentById(Long id);
    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);
    void deletePayment(Long id);
}
