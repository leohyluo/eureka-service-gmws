package com.ebm.gmws.smartImate.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ebm.gmws.pojo.domain.User;
import com.ebm.gmws.smartImate.mapper.UserMapper;
import com.ebm.gmws.smartImate.service.UserService2;

@Service
public class UserServiceImpl2 implements UserService2 {

	@Resource
	private UserMapper userMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateSingle(User user) {
		if(user.getId().longValue() == 6) {
			System.out.println(1/0);
		}
		userMapper.update(user);
	}
}
