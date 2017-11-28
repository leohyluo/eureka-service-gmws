package com.ebm.gmws.smartImate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.common.config.service.RedisService;
import com.ebm.gmws.common.config.service.impl.CacheServiceImpl;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Resource
	private RedisService redisService;
	@Resource
	private CacheServiceImpl cacheServiceImpl;

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
	
	@GetMapping("/delCompanyCache/{companyId}")
	public String delCompanyCache(@PathVariable String companyId) {
		redisService.removeCompanyData(companyId);
		return "success";
	}
	
	@GetMapping("/putCompany/{companyId}")
	public String putCompany(@PathVariable Integer companyId){
		String result = redisService.putCompany(companyId);
		return result;
	}
	
	@GetMapping("/delCompany/{companyId}")
	public String delCompany(@PathVariable Integer companyId){
		redisService.delCompany(companyId);
		return "del success";
	}
	
	@GetMapping("/putCompany2/{companyId}")
	public String putCompany2(@PathVariable Integer companyId){
		String result = cacheServiceImpl.putCompany(companyId);
		return result;
	}
	
	@GetMapping("/delCompany2/{companyId}")
	public String delCompany2(@PathVariable Integer companyId){
		cacheServiceImpl.delCompany(companyId);
		return "del success";
	}
}
