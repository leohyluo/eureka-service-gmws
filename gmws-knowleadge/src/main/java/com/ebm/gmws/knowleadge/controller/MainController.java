package com.ebm.gmws.knowleadge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/knowleadge/disease")
public class MainController {
	
	@GetMapping(value = "/getDetail")
	public String getDetail() {
		System.out.println("invoke /knowleadge/disease/getDetail ...");
		return "get disease detail from gmws-knowleadge-disease";
	}
}
