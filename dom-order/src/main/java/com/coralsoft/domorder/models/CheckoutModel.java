package com.coralsoft.domorder.models;

import com.coralsoft.domorder.dtos.CartDto;
import com.coralsoft.domorder.enums.PaymentMethod;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
public class CheckoutModel {

    private UUID userId;
    private CartDto cart;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
