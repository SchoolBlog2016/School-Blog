package com.SchoolBlog.service.Impl;

import java.util.Map;

import javax.annotation.Resource;

import com.SchoolBlog.dao.ArticalDao;
import com.SchoolBlog.dao.LikeDao;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.service.LikeService;
import com.SchoolBlog.util.ResultHandler;

public class LikeServiceImpl implements LikeService {
	@Resource
	private ArticalDao articalDao;
	@Resource
	private LikeDao likeDao;

	@Override
	public Map<String, Object> updateLike(int articalId, int userId, boolean isAddLike) {
		boolean flag=false;
		if(isAddLike){
			flag=this.likeDao.addLike(articalId, userId);
		}else{
			flag=this.likeDao.delLike(articalId, userId);
		}
		if(!flag){
			return ResultHandler.handleJson("info", "Ê§°Ü", FinalModel.INTERNET_ERREO);
		}
		int code=this.articalDao.updateLikeNum(articalId, isAddLike)?
				FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("like", isAddLike, code);
	}

	@Override
	public boolean isLike(int articalId, int userId) {
		
		return this.likeDao.isLike(articalId, userId);
	}

}
