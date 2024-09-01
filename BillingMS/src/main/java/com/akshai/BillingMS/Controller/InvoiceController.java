package com.akshai.BillingMS.Controller;

import com.akshai.BillingMS.Service.InvoiceService;
import com.akshai.BillingMS.dto.InvoiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/billings")
public class InvoiceController {

    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        log.info("Fetching all invoices");
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        log.info("Fetching invoice with id: {}", id);
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByPatientId(@PathVariable Long patientId) {
        log.info("Fetching invoices for patient with id: {}", patientId);
        return ResponseEntity.ok(invoiceService.getInvoicesByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByDoctorId(@PathVariable Long doctorId) {
        log.info("Fetching invoices for doctor with id: {}", doctorId);
        return ResponseEntity.ok(invoiceService.getInvoicesByDoctorId(doctorId));
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByAppointmentId(@PathVariable Long appointmentId) {
        log.info("Fetching invoices for appointment with id: {}", appointmentId);
        return ResponseEntity.ok(invoiceService.getInvoicesByAppointmentId(appointmentId));
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        log.info("Creating new invoice for appointmentId: {}", invoiceDTO.getAppointmentId());
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
        log.info("Updating invoice with id: {}", id);
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoiceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        log.info("Deleting invoice with id: {}", id);
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
