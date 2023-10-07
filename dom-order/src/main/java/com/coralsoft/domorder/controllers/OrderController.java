package com.coralsoft.domorder.controllers;

import com.coralsoft.domorder.models.CheckoutModel;
import com.coralsoft.domorder.models.OrderModel;
import com.coralsoft.domorder.repositories.OrderRepository;
import com.coralsoft.domorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/orders")
public class OrderController {

   final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody CheckoutModel checkoutModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(checkoutModel));
    }

    @GetMapping
    public ResponseEntity<Page<OrderModel>> findAllOrders(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders(pageable));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> findOrderById(@PathVariable(value = "orderId")UUID orderId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(orderId));
    }

    @PutMapping("/{orderId}/update-to-payment-confirmed")
    public ResponseEntity<Object> updateOrderToPaymentConfirmed(@PathVariable(value = "orderId")UUID orderId){
        orderService.updateToPaymentConfirmed(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("OrderStatus updated successfully");
    }

}
