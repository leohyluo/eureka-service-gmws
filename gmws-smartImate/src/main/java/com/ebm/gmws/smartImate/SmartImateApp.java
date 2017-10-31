package com.ebm.gmws.smartImate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.ebm.gmws.fw.common.core.annotation.SpringCloudApplicationStarter;
import com.ebm.gmws.outer.configuration.RibbonConfig4Disease;


@SpringCloudApplicationStarter
@RibbonClients(defaultConfiguration={RibbonConfig4Disease.class})
@EnableFeignClients(basePackages = {"com.ebm.gmws.rpc.disease"})
@MapperScan(basePackages = {"com.ebm.gmws.smartImate.mapper"})
@ComponentScan(basePackages = {"com.ebm.gmws.smartImate","com.ebm.gmws.common.config","com.ebm.gmws.rpc","com.ebm.gmws.fw.common.core"})
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
