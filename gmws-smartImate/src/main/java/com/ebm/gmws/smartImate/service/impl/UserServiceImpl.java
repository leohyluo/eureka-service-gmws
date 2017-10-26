package com.ebm.gmws.smartImate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ebm.gmws.pojo.domain.User;
import com.ebm.gmws.smartImate.mapper.UserMapper;
import com.ebm.gmws.smartImate.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void update(User user) {
		if(user.getId().longValue() == 6) {
			System.out.println(1/0);
		}
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
	@Transactional
	public void update(List<User> userList) {
		userList.forEach(this::update);
	}
}
