package com.akshai.BillingMS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long appointmentId;  // References an Appointment in the Appointment Service

    @Column(nullable = false)
    private Long patientId;  // References a Patient in the Patient Service

    @Column(nullable = false)
    private Long doctorId;  // References a Doctor in the Doctor Service

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date billingDate;

    @Column(nullable = false)
    private String status;
}
