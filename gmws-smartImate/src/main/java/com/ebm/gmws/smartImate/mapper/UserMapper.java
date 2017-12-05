package com.ebm.gmws.smartImate.mapper;

import java.util.List;
import java.util.Map;

import com.ebm.gmws.pojo.domain.User;

public interface UserMapper {

	void updateUser(User user);
	
	List<User> listUser(Map<String, Object> param);
}
