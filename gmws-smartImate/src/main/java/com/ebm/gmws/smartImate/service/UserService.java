package com.ebm.gmws.smartImate.service;

import java.util.List;

import com.ebm.gmws.fw.common.core.service.IService;
import com.ebm.gmws.pojo.domain.User;

public interface UserService extends IService {

	void updateSingle(User user);
	
	User queryById(Long id);
	
	void update(List<User> userList);
}
