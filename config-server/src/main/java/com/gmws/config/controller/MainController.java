package com.gmws.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/test")
	public String showVersion() {
		return "application config-server started";
	}
}
