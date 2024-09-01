package com.akshai.PaymentMS.Client;

import com.akshai.PaymentMS.dto.InvoiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BILLING-SERVICE")
public interface BillingClient {

    @GetMapping("/api/billings/{id}")
    InvoiceDTO getInvoiceById(@PathVariable Long id);

    @PutMapping("/api/billings/{id}")
    void updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO);
}
