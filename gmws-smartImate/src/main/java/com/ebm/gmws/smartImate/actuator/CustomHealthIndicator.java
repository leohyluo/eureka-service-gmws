package com.ebm.gmws.smartImate.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
	
	@Value(value = "${eureka.client.registry-fetch-interval-seconds}")
	private String fetchInterval;

	@Override
	public Health health() {
		return Health.up().withDetail("registry-fetch-interval-seconds", fetchInterval).withDetail("自定义", "leohyluo").build();
	}

}
