package com.akshai.PaymentMS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long invoiceId;  // References an Invoice in the Billing Service

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentMethod;  // e.g., Credit Card, PayPal

    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    private String status;  // e.g., Success, Failed, Pending

    private String transactionId;
}
