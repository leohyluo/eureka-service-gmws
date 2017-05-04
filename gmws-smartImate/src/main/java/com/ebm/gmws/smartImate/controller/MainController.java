package com.ebm.gmws.smartImate.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ebm.gmws.smartImate.utils.http.HttpUtils;

@RestController
@RequestMapping("/smartImate")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private LoadBalancerClient loadBalancerClient;
	
	@RequestMapping(value = "/search")
	public String search() {
		logger.info("invoke from 9092");
		return "get result from smartImate service";
	}
	
	@GetMapping(value = "/test")
	public String test() {
		ResponseEntity<String> result = restTemplate.getForEntity("http://gmws-knowleadge-disease/knowleadge/disease/getDetail", String.class);
		return result.getBody();
	}
	
	@GetMapping(value = "/test2")
	public String test2() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("gmws-knowleadge-disease");
		String host = serviceInstance.getHost();
		int port = serviceInstance.getPort();
		String url = "http://" + host + ":" + port + "/knowleadge/disease/getDetail";
		System.out.println("invoke client uri : " + url);
		String result = HttpUtils.sendGet(url, "");
		return result;
	}
}
