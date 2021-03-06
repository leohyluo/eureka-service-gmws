package com.ebm.gmws.outer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import feign.Retryer;

@Configuration
public class RibbonConfig4Disease {
	
	/**
	 * Ribbon负载均衡策略定义
	 * BestAvailableRule 选择一个最小的并发请求的server
	 * AvailabilityFilteringRule 过滤掉那些因为一直连接失败的被标记为circuit tripped的后端server，并过滤掉那些高并发的的后端server（active connections 超过配置的阈值）
	 * WeightedResponseTimeRule 根据相应时间分配一个weight，相应时间越长，weight越小，被选中的可能性越低
	 * RetryRule 对选定的负载均衡策略机上重试机制
	 * RoundRobinRule roundRobin方式轮询选择server
	 * RandomRule 随机选择一个server
	 * ZoneAvoidanceRule 复合判断server所在区域的性能和server的可用性选择server
	 */

	@Bean
	public IRule ribbonRule(IClientConfig config) {		
		return new RandomRule();
	}
	
	/**
	 * 重写FeignClientsConfiguration配置
	 * @return
	 */
	@Bean
	public Retryer feignRetryer() {
		//更改了该FeignClient的重试次数，重试间隔为100ms，最大重试时间为1s,重试次数为5次
		return new Retryer.Default(100, 1000, 5);
	}
}
