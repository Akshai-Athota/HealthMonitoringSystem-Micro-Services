package com.akshai.BillingMS.Repository;

import com.akshai.BillingMS.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findByPatientId(Long patientId);
    List<Invoice> findByDoctorId(Long doctorId);
    List<Invoice> findByAppointmentId(Long appointmentId);
}
