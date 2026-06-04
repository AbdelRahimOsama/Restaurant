package com.spring.resturant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class Resturant904Application {

    public static void main(String[] args) {
        SpringApplication.run(Resturant904Application.class, args);
    }

}
