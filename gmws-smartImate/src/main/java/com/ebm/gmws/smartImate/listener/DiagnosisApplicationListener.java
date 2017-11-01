package com.ebm.gmws.smartImate.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebm.gmws.common.config.RedisKeys;
import com.ebm.gmws.common.config.service.IRedisService;
import com.ebm.gmws.pojo.diagnosis.MainSymptom;
import com.ebm.gmws.smartImate.service.MainSymptomService;

@WebListener
public class DiagnosisApplicationListener implements ServletContextListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private MainSymptomService mainSymptomService;
	@Resource
	private IRedisService redisService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("DiagnosisApplicationListener initialized start");
		loadMainSymptomToRedis();
		logger.info("DiagnosisApplicationListener initialized end");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	private void loadMainSymptomToRedis() {
		logger.info("loadMainSymptomToRedis start.");
		Map<String, Object> map = new HashMap<>();
		List<MainSymptom> mainList = mainSymptomService.list(map);
		redisService.setList(RedisKeys.MAIN_SYMPTOM_ALL.getValue(), mainList);
		logger.info("load {} MainSymptom to Redis complete", mainList.size());
	}
	
}
