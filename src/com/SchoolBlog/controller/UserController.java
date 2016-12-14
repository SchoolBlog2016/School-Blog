package com.SchoolBlog.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SchoolBlog.dao.LikeDao;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.model.UserBean;
import com.SchoolBlog.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private LikeDao likeDao;
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Map<String,Object> login(
			@RequestParam("stuNum") String xuehao,
			@RequestParam("idNum") String  password){
		return this.userService.login(xuehao,password.toUpperCase());
	}
	
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Map<String,Object>register(
			@RequestParam("stuNum") String xuehao,
			@RequestParam("idNum") String  password,
			@RequestParam("name") String realname,
			@RequestParam("gender")String sex,
			@RequestParam("grade")String grade){
		UserBean user=new UserBean();
		user.setXuehao(xuehao);
		user.setGrade(grade);
		user.setPassword(password.toUpperCase());
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
	
	@RequestMapping("/myLike")
	@ResponseBody
	public Map<String, Object> myLike(
			@RequestParam("userId") int userId,
			@RequestParam("page") int page){
		
		return this.userService.getMyLike(userId,page);
	}
	
	
	@RequestMapping("/updateInfo")
	@ResponseBody
	public Map<String, Object> updateInfo(
			@RequestParam("userId")int userId,
			@RequestParam("info")String info){
		return this.userService.updateInfo(userId, info);
	}
	
	@RequestMapping("/myArtical")
	@ResponseBody
	public Map<String, Object> myArtical(
			@RequestParam("userId") int userId){
		
		return this.userService.getMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.BLOG_TYPE);
	}
	
	@RequestMapping("/freshMyArtical")
	@ResponseBody
	public Map<String, Object> freshMyArtical(
			@RequestParam("userId") int userId,
			@RequestParam("articalId")int articalId){
		
		return this.userService.freshMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.BLOG_TYPE,articalId);
	}
	
	@RequestMapping("/myProblem")
	@ResponseBody
	public Map<String, Object> myProblem(
			@RequestParam("userId") int userId){
		
		return this.userService.getMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.PROBLEM_TYPE);
	}
	
	@RequestMapping("/freshMyProblem")
	@ResponseBody
	public Map<String, Object> freshMyProblem(
			@RequestParam("userId") int userId,
			@RequestParam("articalId")int articalId){
		
		return this.userService.freshMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.PROBLEM_TYPE,articalId);
	}
	
	@RequestMapping("/myPickup")
	@ResponseBody
	public Map<String, Object> myPickup(
			@RequestParam("userId") int userId){
		
		return this.userService.getMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.PICKUP_TYPE);
	}
	
	@RequestMapping("/freshMyPickup")
	@ResponseBody
	public Map<String, Object> freshMyPickup(
			@RequestParam("userId") int userId,
			@RequestParam("articalId")int articalId){
		
		return this.userService.freshMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.PICKUP_TYPE,articalId);
	}
	
	@RequestMapping("/myLose")
	@ResponseBody
	public Map<String, Object> myLose(
			@RequestParam("userId") int userId){
		
		return this.userService.getMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.LOSE_TYPE);
	}
	
	@RequestMapping("/freshMyLose")
	@ResponseBody
	public Map<String, Object> freshMyLose(
			@RequestParam("userId") int userId,
			@RequestParam("articalId")int articalId){
		
		return this.userService.freshMyArtical(userId,FinalModel.PUBLISH_ARTICAL,FinalModel.LOSE_TYPE,articalId);
	}
	
	@RequestMapping("/myAnswer")
	@ResponseBody
	public Map<String, Object> myAnswer(
			@RequestParam("userId") int userId){
		
		return this.userService.getMyAnswer(userId,FinalModel.PUBLISH_ARTICAL);
	}
	
	@RequestMapping("/freshMyAnswer")
	@ResponseBody
	public Map<String, Object> freshMyAnswer(
			@RequestParam("userId") int userId,
			@RequestParam("articalId")int articalId){
		
		return this.userService.freshMyAnswer(userId,FinalModel.PUBLISH_ARTICAL,articalId);
	}
	
	@RequestMapping("/E-Card")
	@ResponseBody
	public Map<String, Object> MyEcard(
			@RequestParam("userId") int userId){
		
		return this.userService.getMyEcard(userId);
	}
}
