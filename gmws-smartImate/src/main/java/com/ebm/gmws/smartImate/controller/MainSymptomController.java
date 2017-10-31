package com.ebm.gmws.smartImate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.common.config.service.IRedisService;
import com.ebm.gmws.pojo.diagnosis.MainSymptom;
import com.ebm.gmws.smartImate.service.MainSymptomService;

@RestController
@RequestMapping("/diagnosis/mainSymptom")
public class MainSymptomController {
	
	@Resource
	private MainSymptomService mainSymptomService;
	
	@Resource
	private IRedisService redisService;

	@GetMapping("/list")
	public List<MainSymptom> list() {
		Map<String, Object> map = new HashMap<>();
		List<MainSymptom> list = mainSymptomService.list(map);
		return list;
	}
	
	@GetMapping("/put")
	public String put() {
		redisService.set("m-test", "发热");
		return "success";
	}
}
