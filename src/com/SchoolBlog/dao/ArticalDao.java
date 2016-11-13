package com.SchoolBlog.dao;

import java.util.List;
import java.util.Map;

import com.SchoolBlog.model.ArticalBean;

public interface ArticalDao {
	public boolean addArtical(ArticalBean artical);
	public List<ArticalBean> getArticals();
	public List<Map<String, Object>> freshArticals(int ArticalId);
	public ArticalBean getArtical(int articalId); 
	public boolean alterArtical(int ArticalId, String addContent);
	public boolean addCommentNum(int articalId);
	public boolean updateLikeNum(int articalId,boolean isAddLike); 
	public boolean delArtical(int articalId);
	public List<ArticalBean> getMyArticals(int userId);
	boolean saveArtical(ArticalBean artical);//artical����������Ҫ������userId��content����
	boolean reSaveArtical(ArticalBean artical);//artical����������ҪarticalId��content
	public boolean addLookNum(int articalId);
	
}
