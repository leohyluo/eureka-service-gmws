package com.ebm.gmws.smartImate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.fw.common.core.web.utils.ResponseMessage;
import com.ebm.gmws.fw.common.core.web.utils.WebUtils;
import com.ebm.gmws.pojo.domain.User;
import com.ebm.gmws.smartImate.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

	@GetMapping("/update")
	public String update() {
		List<User> userList = new ArrayList<>();
		User user1 = userService.queryById(5L);
		User user2 = userService.queryById(6L);
		user1.setSex("男");
		user2.setSex("女");
		
		userList.add(user1);
		userList.add(user2);
		userService.update(userList);
		return "update success";
	}
	
	
	@GetMapping("/update3")
	public String update3() {
		List<User> userList = new ArrayList<>();
		User user1 = userService.queryById(5L);
		User user2 = userService.queryById(6L);
		user1.setSex("男");
		user2.setSex("女");
		
		userList.add(user1);
		userList.add(user2);
		userService.updateWihtoutTx(userList);
		return "update success";
	}
	
	@PostMapping("/save")
	public ResponseMessage save(User user) {
		System.out.println(user.getName());
		ResponseMessage result = WebUtils.buildSuccessResponseMessage(user.getName());
		return result;
	}
}
