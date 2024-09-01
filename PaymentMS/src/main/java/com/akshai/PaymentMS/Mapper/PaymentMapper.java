package com.akshai.PaymentMS.Mapper;

import com.akshai.PaymentMS.Entities.Payment;
import com.akshai.PaymentMS.dto.PaymentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDTO toDTO(Payment payment);

    Payment toEntity(PaymentDTO paymentDTO);
}
