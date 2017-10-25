package com.ebm.gmws.smartImate.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

	
	@Scheduled(fixedRate = 5000)
	public void testSchedule() {
		System.out.println("invoke schedule task");
	}
}
