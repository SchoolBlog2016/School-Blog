package com.SchoolBlog.dao;

public interface LikeDao {

	public boolean addLike( int articalId, int userId);
	
	public boolean delLike( int articalId, int userId);
	
	public boolean isLike (int articalId, int userId);
	
	
}
