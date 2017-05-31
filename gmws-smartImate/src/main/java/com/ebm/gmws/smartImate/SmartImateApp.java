package com.ebm.gmws.smartImate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ebm.gmws.configuration.RibbonConfig;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "gmws-knowleadge-disease", configuration = RibbonConfig.class)
@EnableFeignClients
@EnableCircuitBreaker
public class SmartImateApp 
{
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	
    public static void main( String[] args )
    {
        SpringApplication.run(SmartImateApp.class, args);
    }
}
