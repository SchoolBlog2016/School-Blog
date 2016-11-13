package com.SchoolBlog.service;

import java.util.Map;

import com.SchoolBlog.model.CommentBean;

public interface CommentService {

	public Map<String, Object>  publishComment(CommentBean comment);
	public Map<String, Object>  delComment(int commentId);
	public Map<String, Object>  getComments(int articalId, int nowFloor);
	
}
