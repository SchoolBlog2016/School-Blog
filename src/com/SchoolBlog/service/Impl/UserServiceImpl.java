package com.SchoolBlog.service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.SchoolBlog.dao.ArticalDao;
import com.SchoolBlog.dao.LikeDao;
import com.SchoolBlog.dao.LostDao;
import com.SchoolBlog.dao.UserDao;
import com.SchoolBlog.model.ArticalBean;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.model.LostBean;
import com.SchoolBlog.model.UserBean;
import com.SchoolBlog.service.UserService;
import com.SchoolBlog.util.ResultHandler;

public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private ArticalDao articalDao;
	@Resource
	private LikeDao likeDao;
	@Resource
	private LostDao lostDao;
	
	@Override
	public Map<String,Object> login(String xuehao,String password){
		String realpassword=this.userDao.ensureAccount(xuehao);
		int code=FinalModel.INTERNET_ERREO;
		int id=0;
		if(password.equals(realpassword)){
			code=FinalModel.INTERNET_SUCCEED;
			id=this.userDao.getUserId(xuehao);
		}
		return ResultHandler.handleJson("login", id, code);
	}
	
	@Override
	public Map<String,Object> register(UserBean user){
		int userid=this.userDao.adduser(user);
		int code=userid>0?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("login", userid, code);
	}
	
	@Override
	public Map<String, Object> getInfo(int userId) {
		UserBean user=this.userDao.getAllInfo(userId);
		int code=(user!=null)?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("userInfo", user, code);
	}

	@Override
	public Map<String, Object> getMyLike(int userId,int page) {
		if(page<=0){
			return ResultHandler.handleJson("info", "Ò³Âë´íÎó", FinalModel.INTERNET_ERREO);
		}
		List<Integer> articalsId=this.likeDao.mylike(userId,page);
		Iterator<Integer> it=articalsId.iterator();
		List<ArticalBean> myLikes=new ArrayList<>();
		while(it.hasNext()){
			myLikes.add(this.articalDao.getArtical(it.next()));
		}
		int code=myLikes.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("myLikes", myLikes, code);
	}

	@Override
	public Map<String, Object> updateInfo(int userId, String info) {
		UserBean user= new UserBean();
		user.setId(userId);
		user.setInfo(info);
		int code=this.userDao.alertUserInfo(user)>0?
				FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("updateInfo", null, code);
	}

	@Override
	public Map<String, Object> getMyArtical(int userId,int status,int type) {
		
		List<ArticalBean> list=this.articalDao.getMyArticals(userId, status, type);
		int code= list.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("myArticals", list, code);
	}

	@Override
	public Map<String, Object> getMyAnswer(int userId, int status) {
		
		List<Map<String,ArticalBean>> list=this.articalDao.getMyAnswers(userId, status);
		int code= list.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("myArticals", list, code);
	}
	
	@Override
	public Map<String, Object> freshMyArtical(int userId,int status,int type,
			int articalId) {
		List<ArticalBean> myArticals=
				this.articalDao.freshMyArticals(userId,status,type,articalId);
		int code=myArticals.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("myArticals", myArticals, code);
	}

	@Override
	public Map<String, Object> freshMyAnswer(int userId, int status,
			int articalId) {
		List<Map<String, ArticalBean>> myArticals=
				this.articalDao.freshMyAnswers(userId,status,articalId);
		int code=myArticals.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("myArticals", myArticals, code);
	}

	@Override
	public Map<String, Object> getMyEcard(int userId) {
		List<LostBean> list=this.lostDao.getLostList(userId);
		int code=list.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("Ecard", list, code);
	}
	

}
