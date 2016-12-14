package com.SchoolBlog.dao;

import java.util.List;
import java.util.Map;

import com.SchoolBlog.model.ArticalBean;

public interface ArticalDao {
	public boolean addArtical(ArticalBean artical);
	public List<ArticalBean> getArticals(int type);
	public List<ArticalBean> freshArticals(int ArticalId, int type);
	public ArticalBean getArtical(int articalId); 
	public boolean alterArtical(int ArticalId, String addContent);
	public boolean addCommentNum(int articalId);
	public boolean updateLikeNum(int articalId,boolean isAddLike); 
	public boolean delArtical(int articalId);
	public List<ArticalBean> getMyArticals(int userId ,int state, int type);
	public List<ArticalBean> freshMyArticals(int userId, int state, int type, int articalId);
	boolean saveArtical(ArticalBean artical);
	boolean reSaveArtical(ArticalBean artical);
	public boolean addLookNum(int articalId);
	public List<Map<String,ArticalBean>> getMyAnswers(int userId, int status);
	public List<Map<String, ArticalBean>> freshMyAnswers(int userId, int status, int articalId);
	
}
