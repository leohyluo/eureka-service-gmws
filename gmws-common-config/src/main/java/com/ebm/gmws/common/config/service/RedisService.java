package com.ebm.gmws.common.config.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {  
    
    Boolean setString(String key, String value);  
      
    String getString(String key);  
      
    Boolean expire(String key,long expire);  
      
    <T> boolean setList(String key, List<T> list);
          
    <T> List<T> lrange(String key, int start, int end);
      
    long lpush(String key,Object obj);  
      
    long rpush(String key,Object obj);  
      
    <T> T lpop(String key);  
      
    Boolean hset(String key, String field, String value);
    
    String hget(String key, String field);
    
    Map<String, String> hgetAll(String key);
    
    long hlen(String key);
    
    long hdel(String key, String... fields);
    
    long sadd(String key, String member);
    
    <T> Set<T> smembers(String key);
    
    long srem(String key, String member);
    
    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     * @param key
     * @param score
     * @param member
     * @return
     */
    long zadd(String key, double score, String member);
    
    /**
     * 通过分数返回有序集合中指定区间的成员数量
     * @param key
     * @param min
     * @param max
     * @return
     */
    int zcount(String key, double min, double max);
    
    /**
     * 通过索引返回有序集合指定区间内的成员
     * @param key
     * @param firstIndex 区间开始索引
     * @param lastIndex 区间结束索引
     * @return
     */
    <T> List<T> zrange(String key, int firstIndex, int lastIndex);
    
    /**
     * 通过分数返回有序集合指定区间内的成员
     * @param key
     * @param min 分数区间起始值
     * @param max 分数区间结束值
     * @return
     */
    <T> List<T> zrangebyscore(String key, double min, double max);
    
    /**
     * 返回有序集合中指定成员的索引
     * @param key
     * @param member
     * @return
     */
    String zrank(String key, String member);
    
    /**
     * 移除有序集合中一个或多个成员
     * @param key
     * @param member
     * @return
     */
    long zrem(String key, String member);
    
    /**
     * 移除有序区间中给定的排名区间的所有成员
     * @param key
     * @param firstIndex 排名区间起始值
     * @param lastIndex 排名区间结束值
     * @return
     */
    long zremrangebyrank(String key, int firstIndex, int lastIndex);
    
    /**
     * 移除有序集合中给定的分数区间的所有成员
     * @param key 
     * @param min 分数区间起始值
     * @param max 分数区间结束值
     * @return
     */
    long zremrangebyscore(String key, double min, double max);
    
    long removeCompanyData(String companyId);
    
    String putCompany(String companyId);
    
    void delCompany(String companyId);
}
