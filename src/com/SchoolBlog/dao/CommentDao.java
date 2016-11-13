package com.SchoolBlog.dao;

import java.util.List;
import java.util.Map;

import com.SchoolBlog.model.CommentBean;

public interface CommentDao {

	public boolean addComment(CommentBean comment);
	
	public List<Map<String, Object>> getComments(int ArticalId, int nowFloor);
	
	
}
