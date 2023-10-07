package com.coralsoft.domorder.services;

import com.coralsoft.domorder.dtos.CartDto;
import com.coralsoft.domorder.models.CheckoutModel;
import com.coralsoft.domorder.models.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {
    OrderModel createOrder(CheckoutModel checkout);

    Page<OrderModel> findAllOrders(Pageable pageable);

    Object findOrderById(UUID orderId);

    void updateToPaymentConfirmed(UUID orderId);

    void updateToSeparatingInStock(UUID orderId);

    void updateToShippedOut(UUID orderId);

    void updateToConcluded(UUID orderId);

    void updateToCanceled(UUID orderId);
}
