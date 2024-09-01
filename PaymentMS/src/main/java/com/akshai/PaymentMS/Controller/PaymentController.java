package com.akshai.PaymentMS.Controller;

import com.akshai.PaymentMS.Service.PaymentService;
import com.akshai.PaymentMS.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        log.info("Fetching all payments");
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        log.info("Fetching payment with id: {}", id);
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByInvoiceId(@PathVariable Long invoiceId) {
        log.info("Fetching payments for invoice with id: {}", invoiceId);
        return ResponseEntity.ok(paymentService.getPaymentsByInvoiceId(invoiceId));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO) {
        log.info("Processing payment for invoiceId: {}", paymentDTO.getInvoiceId());
        return ResponseEntity.ok(paymentService.processPayment(paymentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        log.info("Updating payment with id: {}", id);
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        log.info("Deleting payment with id: {}", id);
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
