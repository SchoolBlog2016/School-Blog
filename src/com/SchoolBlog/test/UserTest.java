package com.SchoolBlog.test;

import org.junit.Test;

import com.SchoolBlog.model.UserBean;
import com.SchoolBlog.service.Impl.UserServiceImpl;

public class UserTest {

	@Test
	private void registerTest(){
		String xuehao="12345678";
		String password="0123456789";
		String realname="张三李四王五";
		UserBean user=new UserBean();
		user.setXuehao(xuehao);
		user.setPassword(password);
		user.setRealname(realname);
		new UserServiceImpl().register(user);
		
	}
}
