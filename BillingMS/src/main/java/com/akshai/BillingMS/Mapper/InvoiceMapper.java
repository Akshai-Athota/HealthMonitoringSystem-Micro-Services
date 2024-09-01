package com.akshai.BillingMS.Mapper;

import com.akshai.BillingMS.Entity.Invoice;
import com.akshai.BillingMS.dto.InvoiceDTO;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper {
    Invoice toEntity(InvoiceDTO invoiceDTO);
    InvoiceDTO toDTO(Invoice invoice);
}
