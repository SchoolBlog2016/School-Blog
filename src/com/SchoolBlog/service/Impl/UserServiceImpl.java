package com.SchoolBlog.service.Impl;

import java.util.Map;

import javax.annotation.Resource;

import com.SchoolBlog.dao.UserDao;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.model.UserBean;
import com.SchoolBlog.service.UserService;
import com.SchoolBlog.util.ResultHandler;

public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Override
	public Map<String,Object> login(String xuehao,String password){
		String realpassword=this.userDao.ensureAccount(xuehao);
		int code=FinalModel.INTERNET_SUCCEED;
		if(realpassword.isEmpty()){
			code=FinalModel.INTERNET_ERREO;
			return ResultHandler.handleJson("login", null, code);
		}
		boolean status=(password.equals(realpassword)?true:false);	
		return ResultHandler.handleJson("login", status, code);
	}
	
	@Override
	public Map<String,Object> register(UserBean user){
		int userid=this.userDao.adduser(user);
		int code=userid>0?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("login", null, code);
	}
	
	@Override
	public Map<String, Object> getInfo(int userId) {
		UserBean user=this.userDao.getAllInfo(userId);
		int code=(user!=null)?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("login", null, code);
	}
	

}
