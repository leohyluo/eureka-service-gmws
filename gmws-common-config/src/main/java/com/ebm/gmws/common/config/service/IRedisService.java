package com.ebm.gmws.common.config.service;

import java.util.List;

public interface IRedisService {  
    
    public boolean setString(String key, String value);  
      
    public String getString(String key);  
      
    public boolean expire(String key,long expire);  
      
    public <T> boolean setList(String key, List<T> list);
          
    public <T> List<T> getList(String key, int start, int end);
      
    public long lpush(String key,Object obj);  
      
    public long rpush(String key,Object obj);  
      
    public <T> T lpop(String key);  
      
}
