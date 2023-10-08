package com.coralsoft.domserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DomServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomServiceRegistryApplication.class, args);
    }

}
