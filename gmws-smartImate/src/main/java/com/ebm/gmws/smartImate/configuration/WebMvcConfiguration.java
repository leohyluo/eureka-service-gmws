package com.ebm.gmws.smartImate.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ebm.gmws.fw.common.core.spring.converter.CustomMappingJackson2HttpMessageConverter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		CustomMappingJackson2HttpMessageConverter converter = new CustomMappingJackson2HttpMessageConverter();
		converters.add(converter);
	}
	
}
