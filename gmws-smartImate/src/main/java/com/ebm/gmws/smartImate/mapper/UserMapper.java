package com.ebm.gmws.smartImate.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ebm.gmws.pojo.domain.User;

public interface UserMapper {

	void update(User user);
	
	List<User> listUser(Map<String, Object> param);
}