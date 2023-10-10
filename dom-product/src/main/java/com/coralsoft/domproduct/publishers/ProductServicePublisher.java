package com.coralsoft.domproduct.publishers;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.dtos.PublisherProductDto;
import com.coralsoft.domproduct.enums.ActionType;
import com.coralsoft.domproduct.models.ProductModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductServicePublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${dom.broker.exchange.productService}")
    private String exchangeProductService;

    public void publishProductToOrder(PublisherProductDto publisherProductDto, ActionType actionType){
        publisherProductDto.setActionType(actionType);
        rabbitTemplate.convertAndSend(exchangeProductService, "", publisherProductDto);
    }
}
