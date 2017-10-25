package com.ebm.gmws.smartImate.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ebm.gmws.smartImate.service.DiseaseService;

@Service
public class DiseaseServiceImpl implements DiseaseService {
	
	@Resource
	private RestTemplate restTemplate;

	@Async
	@Override
	public String getDetail() {
		restTemplate.getForEntity("http://gmws-knowleadge-disease/knowleadge/disease/getDetail", String.class);
		return "return async";
	}

}
