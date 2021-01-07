package com.example.configmapdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ConfigmapdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigmapdemoApplication.class, args);
    }

}
