package com.ebm.gmws.gmws.knowleadge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drugApp")
public class MainController {

	@GetMapping("/getDrugDetail")
	public String getDrugDetail() {
		return "get drug detail from gmws-knowleadge-drug";
	}
}
