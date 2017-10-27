package com.ebm.gmws.smartImate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebm.gmws.pojo.domain.User;
import com.ebm.gmws.smartImate.service.UserService;
import com.ebm.gmws.smartImate.service.impl.UserService3;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	@Resource
	private UserService3 userService3;

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
	
	@GetMapping("/update2")
	public String updates() {
		List<User> userList = new ArrayList<>();
		User user1 = userService.queryById(5L);
		User user2 = userService.queryById(6L);
		user1.setSex("男");
		user2.setSex("女");
		
		userList.add(user1);
		userList.add(user2);
		userService3.update(userList);
		return "update success";
	}
}
