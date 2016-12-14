package com.SchoolBlog.dao;

import java.util.List;
import com.SchoolBlog.model.CommentBean;

public interface CommentDao {

	public boolean addComment(CommentBean comment);
	
	public List<CommentBean> getComments(int ArticalId, int nowFloor);
	
	
}
