package com.akshai.PaymentMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long invoiceId;
    private Double amount;
    private String paymentMethod;
    private Date paymentDate;
    private String status;
    private String transactionId;
}
