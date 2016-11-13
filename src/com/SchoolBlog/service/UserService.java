package com.SchoolBlog.service;

import java.util.Map;

import com.SchoolBlog.model.UserBean;

public interface UserService {

	public Map<String , Object> login(String xuehao,String password);
	
	public Map<String , Object> register(UserBean user);
	
	public Map<String , Object> getInfo(int userId);
}
