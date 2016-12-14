package com.SchoolBlog.service;

import java.util.Map;

import com.SchoolBlog.model.UserBean;

public interface UserService {

	public Map<String , Object> login(String xuehao,String password);
	
	public Map<String , Object> register(UserBean user);
	
	public Map<String , Object> getInfo(int userId);
	
	public Map<String , Object> updateInfo(int userId, String info);
	
	public Map<String, Object> getMyLike(int userId,int page);
	

	public Map<String, Object> getMyArtical(int userId,int status,int type);
	
	public Map<String, Object> freshMyArtical(int userId, int status,int type,int articalId);

	public Map<String, Object> getMyAnswer(int userId, int status);

	public Map<String, Object> freshMyAnswer(int userId, int status,
			int articalId);
	
	public Map<String, Object> getMyEcard(int userId);
}
