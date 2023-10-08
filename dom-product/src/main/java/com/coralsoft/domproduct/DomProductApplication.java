package com.coralsoft.domproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DomProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomProductApplication.class, args);
    }

}
