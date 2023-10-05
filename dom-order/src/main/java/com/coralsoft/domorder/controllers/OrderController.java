package com.coralsoft.domorder.controllers;

import com.coralsoft.domorder.models.CheckoutModel;
import com.coralsoft.domorder.models.OrderModel;
import com.coralsoft.domorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/orders")
public class OrderController {

   @Autowired
   OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody CheckoutModel checkoutModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(checkoutModel));
    }

}
