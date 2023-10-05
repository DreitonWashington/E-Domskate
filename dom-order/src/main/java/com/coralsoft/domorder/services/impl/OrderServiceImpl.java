package com.coralsoft.domorder.services.impl;

import com.coralsoft.domorder.dtos.CartDto;
import com.coralsoft.domorder.models.CartModel;
import com.coralsoft.domorder.models.OrderModel;
import com.coralsoft.domorder.repositories.CartRepository;
import com.coralsoft.domorder.repositories.OrderRepository;
import com.coralsoft.domorder.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;
    final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public OrderModel createOrder(CartDto cartDto) {

        return null;
    }
}
