package com.ebm.gmws.serviceRecovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRecoveryApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceRecoveryApp.class, args);
    }
}
