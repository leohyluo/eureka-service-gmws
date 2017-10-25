package com.ebm.gmws.smartImate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ebm.gmws.pojo.domain.User;
import com.ebm.gmws.smartImate.service.UserService;
import com.ebm.gmws.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public User queryById(Long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		List<User> userList = userMapper.listUser(param);
		User user = null;
		if(CollectionUtils.isNotEmpty(userList)) {
			user = userList.get(0);
		}
		return user;
	}

	@Override
	public void update(List<User> userList) {
		userList.forEach(this::update);
	}
}
