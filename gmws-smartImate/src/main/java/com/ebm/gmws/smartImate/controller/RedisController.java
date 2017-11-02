package com.ebm.gmws.smartImate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.common.config.service.IRedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Resource
	private IRedisService redisService;

	@GetMapping("/hset")
	public String hpush() {
		Map<String, String> map = new HashMap<>();
		map.put("ip", "127.0.0.1");
		map.put("port", "3306");
		map.put("dbname", "gmws_copy");
		map.put("user", "gmws");
		map.put("password", "gmws");
		map.entrySet().forEach(e->{
			redisService.hset("dbInfo", e.getKey(), e.getValue());
		});
		return "mset success";
	}
	
	@GetMapping("/hget/{key}/{field}")
	public String hget(@PathVariable("key") String key, @PathVariable("field") String field) {
		String str = redisService.hget(key, field);
		return str;
	}
	
	@GetMapping("/hgetall")
	public Map<String, String> hgetall() {
		String key = "dbInfo";
		return redisService.hgetAll(key);
	}
}
