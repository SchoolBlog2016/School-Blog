package com.SchoolBlog.service;

public interface LikeService {
	
	public boolean isLike(int articald, int userId);

	public boolean updateLike(int articalId, int userId, boolean isAddLike);
}
