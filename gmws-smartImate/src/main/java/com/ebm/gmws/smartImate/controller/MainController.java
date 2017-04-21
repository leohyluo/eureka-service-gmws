package com.ebm.gmws.smartImate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smartImate")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/search")
	public String search() {
		logger.info("invoke from 9092");
		return "get result from smartImate service";
	}
}
