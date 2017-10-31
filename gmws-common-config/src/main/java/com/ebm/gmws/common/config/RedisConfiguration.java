package com.ebm.gmws.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
public class RedisConfiguration extends CachingConfigurerSupport {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		return poolConfig;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig poolConfig = getRedisConfig();
		factory.setPoolConfig(poolConfig);
		logger.info("JedisConnectionFactory init success");
		return factory;
	}
	
	@Bean
	public RedisTemplate<?, ?> getRedisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new StringRedisTemplate(getConnectionFactory());
		return redisTemplate;
	}
}
