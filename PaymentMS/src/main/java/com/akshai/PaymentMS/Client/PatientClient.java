package com.akshai.PaymentMS.Client;

import com.akshai.PaymentMS.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Patient-Service")
public interface PatientClient {

    @GetMapping("/patient/{id}")
    PatientDTO getPatientById(@PathVariable Long id);
}
