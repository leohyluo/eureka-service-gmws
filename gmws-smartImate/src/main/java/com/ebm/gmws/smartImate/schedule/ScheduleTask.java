package com.ebm.gmws.smartImate.schedule;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ebm.gmws.smartImate.mapper.UserMapper;

@Component
public class ScheduleTask {
	
	@Resource
	private UserMapper userMapper;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Scheduled(fixedRate = 5000)
	public void testSchedule() {
		
		logger.info("invoke schedule task");
		System.out.println("invoke schedule task");
	}
}
