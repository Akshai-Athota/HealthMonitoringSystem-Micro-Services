package com.akshai.PaymentMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {
    private Long id;
    private String firstName;
    private String LastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String email;
    private String address;
    private String gender;
}