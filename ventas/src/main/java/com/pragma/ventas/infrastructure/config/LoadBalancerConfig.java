package com.pragma.ventas.infrastructure.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@Configuration
public class LoadBalancerConfig {

    @Bean
    @LoadBalanced
    @Primary
    public static RestTemplate restTemplate(
            LoadBalancerInterceptor loadBalancerInterceptor,
            LoadBalancerRequestFactory requestFactory) {
        RestTemplate restTemplate = new RestTemplateBuilder()
            .interceptors(loadBalancerInterceptor)
            .build();
        return restTemplate;
    }

    @Bean
    public static LoadBalancerInterceptor loadBalancerInterceptor(
            LoadBalancerClient loadBalancerClient,
            LoadBalancerRequestFactory requestFactory) {
        return new LoadBalancerInterceptor(loadBalancerClient, requestFactory);
    }

    @Bean
    public static LoadBalancerRequestFactory loadBalancerRequestFactory(
            LoadBalancerClient loadBalancerClient) {
        return new LoadBalancerRequestFactory(loadBalancerClient);
    }
} 