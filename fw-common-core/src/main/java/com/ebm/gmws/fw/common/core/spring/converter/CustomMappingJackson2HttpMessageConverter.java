package com.ebm.gmws.fw.common.core.spring.converter;

import java.io.IOException;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonGenerator;

public class CustomMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	
	public CustomMappingJackson2HttpMessageConverter() {
		super();
	}

	@Override
	protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
		System.out.println("===================");
		System.out.println("进入自定义CustomMappingJackson2HttpMessageConverter");
	}
}
