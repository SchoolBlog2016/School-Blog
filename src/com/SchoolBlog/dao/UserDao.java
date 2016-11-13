package com.SchoolBlog.dao;

import com.SchoolBlog.model.UserBean;

public interface UserDao {
	public Integer adduser(UserBean user);
	public String ensureAccount(String user_xuehao);
	public Integer alertUserInfo(UserBean user);
    public Integer addUserLike(int user_id,int num);
	public Integer addUserScore(int user_id, int score);
	public String getRealnameById(int userId);
	public UserBean getAllInfo(int userId);
}
