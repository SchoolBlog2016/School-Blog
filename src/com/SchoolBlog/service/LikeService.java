package com.SchoolBlog.service;

import java.util.Map;

public interface LikeService {
	
	public boolean isLike(int articald, int userId);

	public Map<String, Object> updateLike(int articalId, int userId, boolean isAddLike);
}
