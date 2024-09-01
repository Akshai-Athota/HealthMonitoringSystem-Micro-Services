package com.akshai.PaymentMS.Repository;

import com.akshai.PaymentMS.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByInvoiceId(Long invoiceId);
}