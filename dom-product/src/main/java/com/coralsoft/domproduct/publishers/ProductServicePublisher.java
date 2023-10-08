package com.coralsoft.domproduct.publishers;

import com.coralsoft.domproduct.dtos.ProductModelDto;
import com.coralsoft.domproduct.dtos.PublisherProductDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductServicePublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${dom.broker.exchange.productService}")
    private String exchangeProductService;

    public void publishNewProductCreated(PublisherProductDto publisherProductDto){
        rabbitTemplate.convertAndSend(exchangeProductService, "", publisherProductDto);
    }
}
