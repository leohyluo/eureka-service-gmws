package com.ebm.gmws.common.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.ebm.gmws.common.config.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Boolean setString(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean expire(String key, long expire) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean setList(String key, List<T> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> List<T> lrange(String key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long lpush(String key, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long rpush(String key, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T lpop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hset(String key, String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hget(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long hlen(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long hdel(String key, String... fields) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long sadd(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> Set<T> smembers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long srem(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long zadd(String key, double score, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int zcount(String key, double min, double max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> List<T> zrange(String key, int firstIndex, int lastIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> zrangebyscore(String key, double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String zrank(String key, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long zrem(String key, String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long zremrangebyrank(String key, int firstIndex, int lastIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long zremrangebyscore(String key, double min, double max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long removeCompanyData(String companyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String putCompany(Integer companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delCompany(Integer companyId) {
		// TODO Auto-generated method stub
		
	}

	
}
