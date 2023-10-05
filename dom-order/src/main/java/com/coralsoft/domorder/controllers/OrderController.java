package com.coralsoft.domorder.controllers;

import com.coralsoft.domorder.dtos.ProductDto;
import com.coralsoft.domorder.dtos.ResponsePageDto;
import com.coralsoft.domorder.models.OrderModel;
import com.coralsoft.domorder.models.ProductModel;
import com.coralsoft.domorder.repositories.ProductRepository;
import com.coralsoft.domorder.services.OrderService;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/orders")
public class OrderController {

    final
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel orderModel){
        return null;//ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderModel));
    }

}
