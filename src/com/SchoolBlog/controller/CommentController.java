package com.SchoolBlog.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SchoolBlog.model.CommentBean;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.service.CommentService;
import com.SchoolBlog.util.ResultHandler;

@Controller
@RequestMapping("/api/comment")
public class CommentController {

	@Resource
	private CommentService commentService;
	
	
	@RequestMapping("/publishComment")
	@ResponseBody
	public Map<String, Object> publishComment(
			@RequestParam("userId") int userId,
			@RequestParam("articalId") int articalId,
			@RequestParam("content") String content){
		if(content.isEmpty()){
			return ResultHandler.handleJson("info", "评论内容不能为空", FinalModel.INTERNET_ERREO);
		}
		CommentBean comment=new CommentBean();
		comment.setArticalId(articalId);
		comment.setUserId(userId);
		comment.setContent(content);
		
		return this.commentService.publishComment(comment);
	}
	
	@RequestMapping("/getComments")
	@ResponseBody
	public Map<String, Object> getComments(
			@RequestParam("articalId") int articalId,
			@RequestParam("lastFloor") int lastFloor){
		
		return this.commentService.getComments(articalId, lastFloor);
	}
}
