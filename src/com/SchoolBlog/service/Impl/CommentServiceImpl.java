package com.SchoolBlog.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.SchoolBlog.dao.CommentDao;
import com.SchoolBlog.model.CommentBean;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.service.CommentService;
import com.SchoolBlog.util.ResultHandler;

public class CommentServiceImpl implements CommentService {

	//@Resource
	private CommentDao commentDao;
	
	
	@Override
	public Map<String, Object> publishComment(CommentBean comment) {
		int code=this.commentDao.addComment(comment)?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		
		return ResultHandler.handleJson("publishComment", null, code);
	}

	@Override
	public Map<String, Object> delComment(int commentId) {

		return null;
	}

	@Override
	public Map<String, Object> getComments(int articalId, int nowFloor) {
		List<Map<String,Object>> list=this.commentDao.getComments(articalId, nowFloor);
		int code=list.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("comments", list, code);
	}

}
