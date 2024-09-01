package com.akshai.PaymentMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private Long id;
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private Double amount;
    private Date billingDate;
    private String status;
}