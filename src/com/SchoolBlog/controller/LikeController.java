package com.SchoolBlog.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SchoolBlog.service.LikeService;

@Controller
@RequestMapping("/api/like")
public class LikeController {
	
	@Resource
	private LikeService likeService;
	
	@RequestMapping("/addLike")
	@ResponseBody
	public Map<String, Object> addLike(@RequestParam int articalId,@RequestParam int userId){
		
		return this.likeService.updateLike(articalId, userId, true);
	}
	
	@RequestMapping("/delLike")
	@ResponseBody
	public Map<String, Object> delLike(@RequestParam int articalId,@RequestParam int userId){
		
		return this.likeService.updateLike(articalId, userId, false);
	}
	
}
