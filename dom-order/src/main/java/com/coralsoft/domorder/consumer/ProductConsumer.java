package com.coralsoft.domorder.consumer;

import com.coralsoft.domorder.models.ProductModel;
import com.coralsoft.domorder.services.ProductService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    ProductService productService;


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${dom.broker.queue.productServiceQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${dom.broker.exchange.productService}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")))
    public void listenUserEvent(@Payload ProductModel product){
        productService.saveProduct(product);
    }
}
