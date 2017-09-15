package com.ebm.gmws.gmws.knowleadge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Knowleadge4DrugApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Knowleadge4DrugApp.class, args);
    }
}
