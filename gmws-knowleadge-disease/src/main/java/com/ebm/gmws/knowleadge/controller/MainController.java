package com.ebm.gmws.knowleadge.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.knowleadge.pojo.Drug;

@RestController
@RequestMapping("/knowleadge/disease")
public class MainController {
	
	@GetMapping(value = "/getDetail")
	public String getDetail() {
		System.out.println("invoke /knowleadge/disease/getDetail ...");
		return "get disease detail from gmws-knowleadge-disease";
	}
	
	@PostMapping(value = "/postDrugDetail")
	public Drug postDrugDetail(@RequestBody Drug drug) {
		drug.setCreateTime(new Date());
		return drug;
	}
	
	@GetMapping(value = "/getDrugDetail")
	public Drug getDrugDetail(Drug drug) {
		drug.setCreateTime(new Date());
		return drug;
	}
}
