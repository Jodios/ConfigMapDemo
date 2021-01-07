package com.example.configmapdemo.controllers;

import com.example.configmapdemo.ConfigmapdemoApplication;
import com.example.configmapdemo.configuration.ConfigMapDemoConfiguration;
import com.example.configmapdemo.models.Header;
import com.example.configmapdemo.models.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@EnableDiscoveryClient
@RestController
public class Examples {

    @Autowired
    ConfigMapDemoConfiguration config;
    @Autowired
    Environment env;
    @Autowired
    private DiscoveryClient discoveryClient;

    private final Logger logger = LogManager.getLogger(ConfigmapdemoApplication.class);

    /**
     * This is getting the variables from application.yml
     * which reads the variables from the environment
     * @return returns message
     */
    @GetMapping("/appyaml")
    public Message fromAppYaml(){
        Date date = new Date();
        logger.info("Getting variables from application.yml");
        return new Message(new Header(java.util.UUID.randomUUID().toString(), Long.toString(date.getTime()) ), config.getVarA(), config.getVarB());
    }

    /**
     * This is getting the services from kubernetes
     * @return
     */
    @GetMapping("/services")
    public Message getServices(){
        StringBuilder b = new StringBuilder();
        discoveryClient.getServices().forEach((s) -> b.append(s).append(" , "));
        Date date = new Date();
        logger.info("Getting services from kube");
        logger.info(discoveryClient.description());
        logger.info(discoveryClient);
        return new Message(new Header(java.util.UUID.randomUUID().toString(), Long.toString(date.getTime()) ), b.toString(), null);
    }

}
