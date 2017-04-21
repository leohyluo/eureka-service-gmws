package com.ebm.gmws.knowleadge.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/knowleadge")
public class MainController {
	
	@Resource
	private RestTemplate restTemplate;

	@RequestMapping(value = "/searchSymptom")
	public String searchSymptom() {
		String result = restTemplate.getForObject("http://smart-imate:9092/smartImate/search", String.class);
		return result;
	}
}
