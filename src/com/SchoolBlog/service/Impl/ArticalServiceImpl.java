package com.SchoolBlog.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.SchoolBlog.dao.ArticalDao;
import com.SchoolBlog.model.ArticalBean;
import com.SchoolBlog.model.FinalModel;
import com.SchoolBlog.service.ArticalService;
import com.SchoolBlog.util.ResultHandler;

public class ArticalServiceImpl implements ArticalService {
	
	@Resource
	private ArticalDao articalDao;
	
	
	@Override
	public ArticalBean getArtical(int articalId) {
		
		this.articalDao.addLookNum(articalId);
		return this.articalDao.getArtical(articalId);
	}

	@Override
	public Map<String, Object> pulishOrSaveArtical(ArticalBean artical) {
		if(artical.getContent().isEmpty()||artical.getTitle().isEmpty()){
			return ResultHandler.handleJson("info", "NullTitleOrNullContent",FinalModel.INTERNET_ERREO);
		}
		if(artical.getId()>0){
			this.articalDao.delArtical(artical.getId());
		}
		int code=articalDao.addArtical(artical)?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("Artical", null, code);
	}

	@Override
	public Map<String, Object> getNextArticalListBytime(int articalId) {

		List<Map<String,Object>> articalList=articalDao.freshArticals(articalId);
		int code=articalList.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		Iterator<Map<String, Object>> it =articalList.iterator();
		while(it.hasNext()){
			
		}
		
		return ResultHandler.handleJson("ArticalList", articalList, code);
	}

	@Override
	public Map<String, List<ArticalBean>> getArticalListByLike(int page,
			Date time) {

		return null;
	}

	@Override
	public Map<String, List<ArticalBean>> getArticalListByLook(int page,
			Date time) {

		return null;
	}

	@Override
	public Map<String, List<ArticalBean>> searchArticalList(int page,
			String keyWord, Date time) {

		return null;
	}

	@Override
	public Map<String, Object> deleteArtical(int articleId) {

		return null;
	}

	@Override
	public Map<String, Object> addContent(int articalId, String addString) {

		int code=FinalModel.INTERNET_SUCCEED;
		if(addString.isEmpty()){
			code=FinalModel.INTERNET_ERREO;
			return ResultHandler.handleJson("Artical", null, code);
		}
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String s = sdf.format(date);

		addString+="\n\n以下内容更新于"+s+"：\n";
		code=this.articalDao.alterArtical(articalId,addString)?
				FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;;
		return ResultHandler.handleJson("addContent", null, code);
	}

	@Override
	public Map<String, Object> refrashArticalList() {
		List<ArticalBean> list=this.articalDao.getArticals();
		int code=(list.isEmpty())?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		Map<String ,Object> request=new HashMap<String, Object>();
		
		request.put("articals", list);
		return ResultHandler.handleJson("articals", request, code);
	}

	@Override
	public Map<String, Object> reSaveArtical(ArticalBean artical) {
		if(artical.getContent().isEmpty()||artical.getTitle().isEmpty()){
			return ResultHandler.handleJson("info", "NullTitleOrNullContent",FinalModel.INTERNET_ERREO);
		}
		int code=articalDao.reSaveArtical(artical)?FinalModel.INTERNET_SUCCEED:FinalModel.INTERNET_ERREO;
		return ResultHandler.handleJson("Artical", null, code);
	}

	@Override
	public Map<String, Object> getMyArtical(int userId) {
		
		List<ArticalBean> myArticals=
				this.articalDao.getMyArticals(userId);
		int code=myArticals.isEmpty()?FinalModel.INTERNET_ERREO:FinalModel.INTERNET_SUCCEED;
		return ResultHandler.handleJson("myArticals", myArticals, code);
	}

}
