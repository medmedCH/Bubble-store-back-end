package com.labs.config;

import com.orbitz.consul.Consul;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ProductBeansProducer {
    @Produces
    Consul consulClient = Consul.builder().build();
}
