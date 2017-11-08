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
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.ebm.gmws.common.config.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired  
    private RedisTemplate<String, ?> redisTemplate;  
      
    @Override  
    public Boolean setString(final String key, final String value) {  
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
    public Boolean expire(final String key, long expire) {  
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
	public <T> List<T> lrange(String key, int start, int end) {
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
	
	@Override
	public Boolean hset(String key, String field, String value) {
		return redisTemplate.execute(connection->{
			RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
			return connection.hSet(stringSerializer.serialize(key), stringSerializer.serialize(field), stringSerializer.serialize(value));
		});
	}
	
	@Override
	public String hget(String key, String field) {
		return redisTemplate.execute(connection->{
			RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
			byte[] data = connection.hGet(stringSerializer.serialize(key), stringSerializer.serialize(field));
			String value = stringSerializer.deserialize(data);
			return value;
		});
	}
	
	@Override
	public Map<String, String> hgetAll(String key) {
		return redisTemplate.execute(connection->{
			RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
			Map<byte[], byte[]> map = connection.hGetAll(stringSerializer.serialize(key));
			Map<String, String> result = new HashMap<>();
			map.forEach((k,v)->{
				result.put(stringSerializer.deserialize(k), stringSerializer.deserialize(v));
			});
			return result;
		});
	}

	@Override
	public long hlen(String key) {
		return redisTemplate.execute(connection->{
			RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
			return connection.hLen(stringSerializer.serialize(key));
		});
	}

	@Override
	public long hdel(String key, String... fields) {
		return redisTemplate.execute(connection->{			
			Object[] objArr = Stream.of(fields).map(SerializationUtils::serialize).collect(Collectors.toList()).toArray();
			byte[][] fieldList = (byte[][]) objArr;
			RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
			return connection.hDel(stringSerializer.serialize(key), fieldList);
		});
	}

	@Override
	public long sadd(String key, String member) {
		return redisTemplate.execute(connection->{
			RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
			return connection.sAdd(stringSerializer.serialize(key), stringSerializer.serialize(member));
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Set<T> smembers(String key) {
		return redisTemplate.execute(connection->{
			Set<byte[]> set = connection.sMembers(SerializationUtils.serialize(key));
			Set<Object> stringSet = set.stream().map(SerializationUtils::deserialize).collect(Collectors.toSet());
			Set<T> tset = stringSet.stream().map(e->{
				T t = (T) e;
				return t;
			}).collect(Collectors.toSet());
			return tset;
		});
	}

	@Override
	public long srem(String key, String member) {
		return redisTemplate.execute(connection->{
			return connection.sRem(SerializationUtils.serialize(key), SerializationUtils.serialize(member));
		});
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
		String key = "company-" + companyId + "*";
		logger.info("key is {}", key);
		RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		Set<String> companyKeys = redisTemplate.execute(connection->{
			Set<byte[]> set = connection.keys(key.getBytes());
			Set<Object> companyallKey4Obj = set.stream().map(e->serializer.deserialize(e)).collect(Collectors.toSet());
			Set<String> companyallKey = companyallKey4Obj.stream().map(e->e.toString()).collect(Collectors.toSet());
			return companyallKey;
		});
		Map<DataType, List<String>> map = redisTemplate.execute(connection->{
			Map<DataType, List<String>> result = new HashMap<>();
			companyKeys.forEach(e->{
				DataType type = connection.type(e.getBytes());
				if(result.containsKey(type)) {
					result.get(type).add(e);
				} else {
					List<String> list = new ArrayList<>();
					list.add(e);
					result.put(type, list);
				}
			});
			return result;
		});
		map.forEach((k,v)->{
			redisTemplate.execute(connection->{
				byte[][] arr = new byte[0][v.size()];
				arr = v.stream().map(SerializationUtils::serialize).collect(Collectors.toList()).toArray(arr);
				if(k == DataType.STRING) {
					connection.del(arr);
				} else if(k == DataType.LIST) {
					//connection.lRem(key, count, value);
				} 
				return null;
			});
		});
		return 0;
	}
	
	@Cacheable(value = "'company'+#companyId")
	public String putCompany(String companyId) {
		System.out.println("put company start");
		return "ok";
	}
	
	@CacheEvict(value = "'company'+#companyId", allEntries = true)
	public void delCompany(String companyId) {
		System.out.println("del company start");
	}
}
