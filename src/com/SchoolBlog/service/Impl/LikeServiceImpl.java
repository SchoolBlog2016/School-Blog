package com.SchoolBlog.service.Impl;

import javax.annotation.Resource;

import com.SchoolBlog.dao.ArticalDao;
import com.SchoolBlog.dao.LikeDao;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.service.LikeService;

public class LikeServiceImpl implements LikeService {
	@Resource
	private ArticalDao articalDao;
	@Resource
	private LikeDao likeDao;

	@Override
	public boolean updateLike(int articalId, int userId, boolean isAddLike) {
		int code=this.articalDao.updateLikeNum(articalId, isAddLike)?
				FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		boolean updateSucceed=false;
		if(FinalModel.INTERNET_SUCCEED==code){
			if(isAddLike){
				updateSucceed=this.likeDao.addLike(articalId, userId);
			}else{
				updateSucceed=this.likeDao.delLike(articalId, userId);
			}
		}
		return updateSucceed;
	}

	@Override
	public boolean isLike(int articalId, int userId) {
		
		return this.likeDao.isLike(articalId, userId);
	}

}
