package com.SchoolBlog.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SchoolBlog.model.UserBean;
import com.SchoolBlog.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Map<String,Object> login(@RequestParam("StuNum") String xuehao,@RequestParam("idNum") String  password){
		return this.userService.login(xuehao,password);
	}
	
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Map<String,Object>register(
			@RequestParam("StuNum") String xuehao,
			@RequestParam("idNum") String  password,
			@RequestParam("name") String realname,
			@RequestParam("gender")String sex,
			@RequestParam("grade")String grade){
		UserBean user=new UserBean();
		user.setXuehao(xuehao);
		user.setGrade(grade);
		user.setPassword(password);
		user.setRealname(realname);
		user.setSex(sex);
		
		return this.userService.register(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/info",method=RequestMethod.POST)
	public Map<String, Object> myInfo (
			@RequestParam("userId") int userId){
		return this.userService.getInfo(userId);
	}

}
