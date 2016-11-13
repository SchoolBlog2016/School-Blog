package com.SchoolBlog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.SchoolBlog.model.ArticalBean;

public interface ArticalService {

	public ArticalBean getArtical(int articalId);
	
	public Map<String, Object>pulishOrSaveArtical(ArticalBean artical);
	
	public Map<String, Object>reSaveArtical(ArticalBean artical);
	
	public Map<String, Object> refrashArticalList();
	
	public Map<String, Object> getMyArtical(int userId);
	
	public Map<String, Object> getNextArticalListBytime(int articalId);
	
	public Map<String, List<ArticalBean>>getArticalListByLike(int page,Date time);
	
	public Map<String, List<ArticalBean>>getArticalListByLook(int page,Date time);
	
	public Map<String, List<ArticalBean>>searchArticalList(int page,String keyWord,Date time);
	
	public Map<String, Object>deleteArtical(int articleId);
	
	public Map<String, Object>addContent(int articalId,String addString);
	
	
	
	
}
