package com.SchoolBlog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SchoolBlog.model.ArticalBean;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.service.ArticalService;
import com.SchoolBlog.service.LikeService;
import com.SchoolBlog.util.ResultHandler;

@Controller
@RequestMapping("/api/artical")
public class ArticalController {
	@Resource
	private ArticalService articalService;
	@Resource
	private LikeService likeService;
	
	@RequestMapping("/getArtical")
	@ResponseBody
	public Map<String, Object> getArtical(
			@RequestParam("articalId") int articalId,
			@RequestParam("userId") int userId){
		ArticalBean artical=this.articalService.getArtical(articalId);
		int code=(artical!=null)?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		Map<String, Object> request=new HashMap<String, Object>();
		if(code==200){
			request.put("isLike", likeService.isLike(articalId, userId));
			request.put("artical", artical);
		}
		return ResultHandler.handleJson("artical", request, code);
	}
	
	@RequestMapping("/refresh")
	@ResponseBody
	public Map<String, Object> refreshArticalList(){
		
		return articalService.refrashArticalList(FinalModel.BLOG_TYPE);
	}
	
	
	@RequestMapping("/fresh")
	@ResponseBody
	public Map<String, Object> freshArticalList(@RequestParam("articalId") int articalId){
		
		return articalService.getNextArticalListBytime(articalId,FinalModel.BLOG_TYPE);
	}
	
	@RequestMapping("/publish")
	@ResponseBody
	public Map<String, Object> publishArtical(
			@RequestParam("articalId") int articalId,
			@RequestParam("userId") int userId,
			@RequestParam("title") String title,
			@RequestParam("content") String content){
		ArticalBean newArtical=new ArticalBean();
		content=content.trim();
		if(title.isEmpty()||content.isEmpty()){
			return ResultHandler.handleJson("info", "NullTitleOrNullContent", FinalModel.INTERNET_ERREO);
		}
		newArtical.setId(articalId);
		newArtical.setUserId(userId);
		newArtical.setTitle(title);
		newArtical.setContent(content);
		newArtical.setStatus(FinalModel.PUBLISH_ARTICAL);
		newArtical.setType(FinalModel.BLOG_TYPE);
		
		return this.articalService.pulishOrSaveArtical(newArtical);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> saveArtical(
			@RequestParam("articalId") int articalId,
			@RequestParam("userId") int userId,
			@RequestParam("title") String title,
			@RequestParam("content") String content){
		if(title.isEmpty()||content.isEmpty()){
			return ResultHandler.handleJson("info", "NullTitleOrNullContent", FinalModel.INTERNET_ERREO);
		}
		ArticalBean artical=new ArticalBean();
		artical.setId(articalId);
		artical.setUserId(userId);
		artical.setTitle(title);
		artical.setContent(content);
		artical.setType(FinalModel.BLOG_TYPE);
		artical.setStatus(FinalModel.SAVE_ARTICAL);
		if(articalId<=0){
			return this.articalService.pulishOrSaveArtical(artical);
		}
		return this.articalService.reSaveArtical(artical);
		
	}
	
	@RequestMapping("/addContent")
	@ResponseBody
	public Map<String, Object> addContent(
			@RequestParam("articalId") int articalId,
			@RequestParam("addContent") String addString){
		
		return this.articalService.addContent(articalId, addString);
	}
	
}
