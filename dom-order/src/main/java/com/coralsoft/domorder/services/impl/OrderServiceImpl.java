package com.coralsoft.domorder.services.impl;

import com.coralsoft.domorder.enums.OrderStatus;
import com.coralsoft.domorder.models.CartModel;
import com.coralsoft.domorder.models.CheckoutModel;
import com.coralsoft.domorder.models.OrderModel;
import com.coralsoft.domorder.models.UserModel;
import com.coralsoft.domorder.repositories.OrderRepository;
import com.coralsoft.domorder.services.CartService;
import com.coralsoft.domorder.services.OrderService;
import com.coralsoft.domorder.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;
    final CartService cartService;
    final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, UserService userService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public OrderModel createOrder(CheckoutModel checkout) {
        UserModel user = userService.findUserById(checkout.getUserId());

        CartModel cartModel = cartService.saveCart(checkout.getCart());

        OrderModel order = new OrderModel();
        order.setUser(user);
        order.setCart(cartModel);
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        order.setOrderTime(LocalDateTime.now(ZoneId.of("UTC")));
        order.setPaymentMethod(checkout.getPaymentMethod());

        return orderRepository.save(order);
    }
}
