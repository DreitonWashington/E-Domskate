package com.coralsoft.domorder.services;

import com.coralsoft.domorder.dtos.CartDto;
import com.coralsoft.domorder.models.CheckoutModel;
import com.coralsoft.domorder.models.OrderModel;

public interface OrderService {
    OrderModel createOrder(CheckoutModel checkout);
}
