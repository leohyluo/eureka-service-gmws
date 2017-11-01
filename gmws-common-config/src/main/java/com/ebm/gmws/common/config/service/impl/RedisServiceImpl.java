package com.ebm.gmws.common.config.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.ebm.gmws.common.config.service.IRedisService;

@Service
public class RedisServiceImpl implements IRedisService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired  
    private RedisTemplate<String, ?> redisTemplate;  
      
    @Override  
    public boolean setString(final String key, final String value) {  
        boolean result = redisTemplate.execute(connection->{
        	RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        	connection.set(stringSerializer.serialize(key), stringSerializer.serialize(value));
        	return true;
        });
        return result;  
    }  
    
    @Override
    public String getString(final String key){  
    	 String result = redisTemplate.execute(connection->{
         	RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
         	byte[] data = connection.get(stringSerializer.serialize(key));
         	return stringSerializer.deserialize(data);
         });
         return result; 
    }  
  
    @Override  
    public boolean expire(final String key, long expire) {  
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);  
    }  
      
    @Override  
    public long lpush(final String key, Object obj) {  
        long result = redisTemplate.execute(connection->{
        	RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        	long count = connection.lPushX(stringSerializer.serialize(key), SerializationUtils.serialize(obj));
        	return count;
        });
        return result;  
    }  
  
    @Override  
    public long rpush(final String key, Object obj) {  
        long result = redisTemplate.execute(connection->{
        	RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        	return connection.rPushX(stringSerializer.serialize(key), SerializationUtils.serialize(obj));
        });  
        return result;  
    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public <T> T lpop(final String key) {  
        return redisTemplate.execute(connection->{
        	RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        	byte[] data = connection.lPop(stringSerializer.serialize(key));
        	Object obj = SerializationUtils.deserialize(data);
        	T t = (T) obj;
        	return t;
        });
    }

	@Override
	public <T> boolean setList(String key, List<T> list) {
		logger.info("set {} data to redis", list.size());
		return redisTemplate.execute(connection->{
			list.forEach(e->{
				byte[] data = SerializationUtils.serialize(e);
				connection.lPush(key.getBytes(), data);
			});
			return true;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(String key, int start, int end) {
		List<T> result = redisTemplate.execute(connection -> {
			List<byte[]> list = connection.lRange(key.getBytes(), start, end);
			return list.stream().map(SerializationUtils::deserialize).collect(Collectors.toList())
					.stream().map(e -> {
						T t = (T) e;
						return t;
					}).collect(Collectors.toList());
		});
		return result;
	}
}
