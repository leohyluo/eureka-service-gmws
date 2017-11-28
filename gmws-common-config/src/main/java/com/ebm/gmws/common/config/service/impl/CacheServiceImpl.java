package com.ebm.gmws.common.config.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl {

	@Cacheable(value = "'company'+#companyId")
	public String putCompany(Integer companyId) {
		System.out.println("put company start");
		return "ok";
	}
	
	@CacheEvict(value = "'company'+#companyId", allEntries = true)
	public void delCompany(Integer companyId) {
		System.out.println("del company start");
	}
}
