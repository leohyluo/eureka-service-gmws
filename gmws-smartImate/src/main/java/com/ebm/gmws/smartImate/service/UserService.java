package com.ebm.gmws.smartImate.service;

import java.util.List;

import com.ebm.gmws.pojo.domain.User;

public interface UserService {

	void update(User user);
	
	User queryById(Long id);
	
	void update(List<User> userList);
}
