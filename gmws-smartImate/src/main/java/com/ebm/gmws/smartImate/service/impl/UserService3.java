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
import com.ebm.gmws.smartImate.service.UserService2;

@Service
public class UserService3  {

	@Resource
	private UserMapper userMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSingle(User user) {
		if(user.getId().longValue() == 6) {
			System.out.println(1/0);
		}
		userMapper.update(user);
	}

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

	@Transactional
	public void update(List<User> userList) {
		userList.forEach(this::updateSingle);
	}
}
