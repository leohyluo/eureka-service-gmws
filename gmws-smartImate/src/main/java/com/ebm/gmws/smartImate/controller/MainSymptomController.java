package com.ebm.gmws.smartImate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.common.config.RedisKeys;
import com.ebm.gmws.common.config.service.RedisService;
import com.ebm.gmws.pojo.diagnosis.MainSymptom;
import com.ebm.gmws.smartImate.service.MainSymptomService;

@RestController
@RequestMapping("/diagnosis/mainSymptom")
public class MainSymptomController {
	
	@Resource
	private MainSymptomService mainSymptomService;
	
	@Resource
	private RedisService redisService;

	@GetMapping("/list")
	public List<MainSymptom> list() {
		List<MainSymptom> list = redisService.lrange(RedisKeys.MAIN_SYMPTOM_ALL.getValue(), 1, 2);
		return list;
	}
	
	@GetMapping("/put")
	public String put() {
		redisService.setString("m-test", "发热");
		return "success";
	}
}
