package com.ebm.gmws.smartImate.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebm.gmws.pojo.diagnosis.MainSymptom;
import com.ebm.gmws.smartImate.mapper.MainSymptomMapper;
import com.ebm.gmws.smartImate.service.MainSymptomService;

@Service
public class MainSymptomServiceImpl implements MainSymptomService {
	
	@Resource
	private MainSymptomMapper mapper;

	@Override
	public List<MainSymptom> list(Map<String, Object> map) {
		return mapper.listMainSymptom(map);
	}

}
