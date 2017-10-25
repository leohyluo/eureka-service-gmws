package com.ebm.gmws.user.mapper;

import java.util.List;
import java.util.Map;

import com.ebm.gmws.pojo.domain.User;

public interface UserMapper {

	void update(User user);
	
	List<User> listUser(Map<String, Object> param);
}
