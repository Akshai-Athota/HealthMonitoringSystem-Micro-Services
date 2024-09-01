package com.akshai.BillingMS.Service.Impl;

import com.akshai.BillingMS.Exception.InvoiceNotFoundException;
import com.akshai.BillingMS.Repository.InvoiceRepository;
import com.akshai.BillingMS.Service.InvoiceService;
import com.akshai.BillingMS.dto.InvoiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.akshai.BillingMS.Mapper.InvoiceMapper;
import com.akshai.BillingMS.Entity.Invoice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InvoiceDTO> getInvoicesByPatientId(Long patientId) {
        return invoiceRepository.findByPatientId(patientId)
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InvoiceDTO> getInvoicesByDoctorId(Long doctorId) {
        return invoiceRepository.findByDoctorId(doctorId)
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InvoiceDTO> getInvoicesByAppointmentId(Long appointmentId) {
        return invoiceRepository.findByAppointmentId(appointmentId)
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InvoiceDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDTO)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + id));
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        invoice.setBillingDate(new Date());
        invoice.setStatus("Unpaid");
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + id));

        invoice.setAppointmentId(invoiceDTO.getAppointmentId());
        invoice.setPatientId(invoiceDTO.getPatientId());
        invoice.setDoctorId(invoiceDTO.getDoctorId());
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setBillingDate(invoiceDTO.getBillingDate());
        invoice.setStatus(invoiceDTO.getStatus());

        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(updatedInvoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

}
