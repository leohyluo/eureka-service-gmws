package com.ebm.gmws.smartImate.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ebm.gmws.smartImate.feign.DiseaseFeign;
import com.ebm.gmws.smartImate.pojo.disease.Drug;
import com.ebm.gmws.smartImate.utils.http.HttpUtils;
import com.ebm.gmws.user.pojo.UserInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/smartImate")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private DiseaseFeign diseaseFeign;
	
	//这里读取的是github的配置文件,smart-imate->config-server->github
	@Value("${eureka.server.port}")
	private String eurekaPort;
	
	@RequestMapping(value = "/search")
	public String search() {
		logger.info("invoke from 9092");
		return "get result from smartImate service";
	}
	
	@GetMapping(value = "/test")
	@HystrixCommand(fallbackMethod = "testFallback")
	public String test() {
		ResponseEntity<String> result = restTemplate.getForEntity("http://gmws-knowleadge-disease/knowleadge/disease/getDetail", String.class);
		return result.getBody();
	}
	
	public String testFallback() {
		return "breaker";
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
	
	@GetMapping(value = "/test2_1")
	public String test2_1() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("gmws-knowleadge-drug");
		String host = serviceInstance.getHost();
		int port = serviceInstance.getPort();
		String url = "http://" + host + ":" + port + "/drugApp/getDrugDetail";
		System.out.println("invoke client uri : " + url);
		String result = HttpUtils.sendGet(url, "");
		return result;
	}
	
	@GetMapping(value = "/test3")
	public String test3() {
		return diseaseFeign.getDetail();
	}
	
	@GetMapping(value = "/test4")
	public Drug test4(Drug drug) {
		return diseaseFeign.postDrugDetail(drug);
	}
	
	@GetMapping(value = "/test5")
	public Drug test5(Drug drug) {
		return diseaseFeign.getDrugDetail(drug);
	}
	
	@GetMapping("/showGitInfo")
	public String showGitInfo(){
		return eurekaPort;
	}
	
	@GetMapping("/showUserInfo")
	public UserInfo showUserInfo() {
		UserInfo user = new UserInfo();
		user.setUserId("1000");
		user.setUserName("yama");
		return user;
	}
	
}
