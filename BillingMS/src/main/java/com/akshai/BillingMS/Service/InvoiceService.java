package com.akshai.BillingMS.Service;

import com.akshai.BillingMS.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getAllInvoices();
    List<InvoiceDTO> getInvoicesByPatientId(Long patientId);
    List<InvoiceDTO> getInvoicesByDoctorId(Long doctorId);
    List<InvoiceDTO> getInvoicesByAppointmentId(Long appointmentId);
    InvoiceDTO getInvoiceById(Long id);
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);
    void deleteInvoice(Long id);
}
