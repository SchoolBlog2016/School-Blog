package com.SchoolBlog.dao.Impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.SchoolBlog.dao.ArticalDao;
import com.SchoolBlog.dao.UserDao;
import com.SchoolBlog.model.ArticalBean;
import com.SchoolBlog.model.FinalModel;

public class ArticalDaoImpl implements ArticalDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private UserDao userDao;

	final int limit=com.SchoolBlog.model.FinalModel.ARTICAL_LIMIT;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean addArtical(ArticalBean artical) {
		// TODO Auto-generated method stub
		//try{
		      String sql="insert into tb_artical"
		      		+ "(artical_userId,artical_title,"
		      		+ "artical_content,artical_type,artical_status,"
		      		+ "artical_creatTime,artical_updateTime) "
				+ " values(?,?,?,?,?,NOW(),NOW())";
		      Object[] params=new Object[]{
		    		  artical.getUserId(),
		    		  artical.getTitle(),
		    		  artical.getContent(),
		    		  artical.getType(),
		    		  artical.getStatus()};
		      this.jdbcTemplate.update(sql,params);
		      return true;
		//}catch(Exception e){
		//	e.getMessage();
		//	return false;
		//}
	}

	@Override
	public List<ArticalBean> getArticals(int type) {
		final List<ArticalBean> articals = new ArrayList<>();
		String sql="select artical_id,artical_userId,artical_title,"
				+ "artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime,artical_updateTime from tb_artical"
				+ " where artical_status=1 and artical_type=?"
				+ " order by artical_id desc limit 0 , ?";
	    this.jdbcTemplate.query(sql,new Object[]{type, FinalModel.ARTICAL_LIMIT},
	    new RowCallbackHandler(){
	    	
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ArticalBean artical = new ArticalBean();
				artical.setId(rs.getInt("artical_id"));
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				String s=rs.getString("artical_content");
				if(s.length()>FinalModel.INDEX_WORDS_LIMIT){
					s=s.substring(0,FinalModel.INDEX_WORDS_LIMIT);
				}
				artical.setContent(s);
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatDate(rs.getDate("artical_creatTime"));
				artical.setCreatTime(rs.getTime("artical_creatTime"));
				artical.setUpdateDate(rs.getDate("artical_updateTime"));
				artical.setUpdateTime(rs.getTime("artical_updateTime"));
				
				artical.setStatus(FinalModel.PUBLISH_ARTICAL);
				artical.setType(type);
				artical.setUser(userDao.getAllInfo(artical.getUserId()));
				articals.add(artical);
			}
			
		});
	    return articals;
	}

	@Override
	public List<ArticalBean> freshArticals(int id,int type) {
		final List<ArticalBean> articals = new ArrayList<>();
		String sql="select artical_id,artical_userId,artical_title,artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime,artical_updateTime from tb_artical"
				+ " where artical_status=1 and artical_type=? and artical_id<?"
				+ " order by artical_id desc limit 0 , ?";
	    this.jdbcTemplate.query(sql,new Object[]{type,id,FinalModel.ARTICAL_LIMIT},
	    		new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ArticalBean artical = new ArticalBean();
				artical.setId(rs.getInt("artical_id"));
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				String s=rs.getString("artical_content");
				if(s.length()>FinalModel.INDEX_WORDS_LIMIT){
					s=s.substring(0,FinalModel.INDEX_WORDS_LIMIT);
				}
				artical.setContent(s);
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatDate(rs.getDate("artical_creatTime"));
				artical.setCreatTime(rs.getTime("artical_creatTime"));
				artical.setUpdateDate(rs.getDate("artical_updateTime"));
				artical.setUpdateTime(rs.getTime("artical_updateTime"));
				
				artical.setStatus(FinalModel.PUBLISH_ARTICAL);
				artical.setType(type);
				artical.setUser(userDao.getAllInfo(artical.getUserId()));
				articals.add(artical);
			}
			
		});
	    return articals;
	}
	@Override
	public ArticalBean getArtical(final int articalId) {
		// TODO Auto-generated method stub
		String sql="select * from tb_artical where artical_id=?";
		final ArticalBean artical=new ArticalBean();
		artical.setId(articalId);
		
		this.jdbcTemplate.query(sql, new Object[]{articalId}, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				artical.setId(rs.getInt("artical_id"));
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				artical.setContent(rs.getString("artical_content"));
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatDate(rs.getDate("artical_creatTime"));
				artical.setCreatTime(rs.getTime("artical_creatTime"));
				artical.setUpdateDate(rs.getDate("artical_updateTime"));
				artical.setUpdateTime(rs.getTime("artical_updateTime"));
				
				artical.setStatus(rs.getInt("artical_status"));
				artical.setType(rs.getInt("artical_type"));
				artical.setUser(userDao.getAllInfo(artical.getUserId()));
			}
			
		});
		return artical;
	}

	@Override
	public boolean alterArtical(int ArticalId, String addContent) {
		// TODO Auto-generated method stub
		/*String sql="select artical_content from tb_artical where artical_id=?";
		String content= this.jdbcTemplate.queryForObject(sql,new Object[]{ArticalId},String.class);
		if(content==null)
			return false;
		String result=content+addContent;
		String resultSql="update tb_artical set artical_content=? where artical_id=?";
		try{
			this.jdbcTemplate.update(resultSql, new Object[]{result,ArticalId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;*/
		try{
			String sql="update tb_artical set "
					+ "artical_content=concat(artical_content,?),artical_updateTime=NOW() "
					+ "where artical_id=?";
		    this.jdbcTemplate.update(sql, new Object[]{addContent,ArticalId});
		    return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean addCommentNum(int articalId) {
		// TODO Auto-generated method stub
		String sql="select artical_commentNum from tb_artical where artical_id=?";
		int num = this.jdbcTemplate.queryForObject(sql, new Object[]{articalId}, Integer.class)+1;
		String resultSql="update tb_artical set artical_commentNum = ? where artical_id=?";
		try{
			this.jdbcTemplate.update(resultSql, new Object[]{num,articalId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
		
	}

	@Override
	public boolean updateLikeNum(int articalId, boolean isAddLike) {
		// TODO Auto-generated method stub
		String sql="select artical_likeNum from tb_artical where artical_id=?";
		int num=(isAddLike==true?1:-1);
		int result = this.jdbcTemplate.queryForObject(sql, new Object[]{articalId}, Integer.class)+num;
		String resultSql="update tb_artical set artical_likeNum = ? where artical_id=?";
		try{
			this.jdbcTemplate.update(resultSql, new Object[]{result,articalId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean reSaveArtical(ArticalBean artical){
		// TODO Auto-generated method stub
		try{
			String sql="update tb_artical set artical_content=?,"
					+ " artical_updateTime=NOW() where artical_id=?";
		    this.jdbcTemplate.update(sql,new Object[]{artical.getContent(),artical.getId()});
		    return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean delArtical(int articalId) {
		// TODO Auto-generated method stub
		try{
			String sql="delete from tb_artical where artical_id="+articalId;
			this.jdbcTemplate.update(sql);
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
		
	}

	@Override
	public boolean saveArtical(ArticalBean artical) {
		// TODO Auto-generated method stub
		try{
			String sql="insert into tb_artical(artical_userId,artical_content,artical_status) values(?,?,?,?)";
		    this.jdbcTemplate.update(sql, new Object[]{artical.getUserId(),artical.getContent(),0});
		    return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public List<ArticalBean> getMyArticals(int userId ,int status,int type) {
		
		final List<ArticalBean> articals = new ArrayList<>();
		String sql="select artical_id,artical_userId,artical_title,"
				+ "artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime,artical_updateTime from tb_artical"
				+ " where artical_userId=? and artical_status=? and artical_type=?"
				+ " order by artical_id desc limit 0 , ?";
	    this.jdbcTemplate.query(sql,new Object[]{userId,status,type,FinalModel.ARTICAL_LIMIT},
	    		new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ArticalBean artical = new ArticalBean();
				artical.setId(rs.getInt("artical_id"));
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				String s=rs.getString("artical_content");
				if(s.length()>FinalModel.INDEX_WORDS_LIMIT){
					s=s.substring(0,FinalModel.INDEX_WORDS_LIMIT);
				}
				artical.setContent(s);
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatDate(rs.getDate("artical_creatTime"));
				artical.setCreatTime(rs.getTime("artical_creatTime"));
				artical.setUpdateDate(rs.getDate("artical_updateTime"));
				artical.setUpdateTime(rs.getTime("artical_updateTime"));
				
				artical.setStatus(FinalModel.PUBLISH_ARTICAL);
				artical.setType(type);
				artical.setUser(userDao.getAllInfo(artical.getUserId()));
				articals.add(artical);
			}
			
		});
	    return articals;
	}

	@Override
	public boolean addLookNum(int articalId) {
		// TODO Auto-generated method stub
		String sql="select artical_lookNum from tb_artical where artical_id=?";
		Integer result = this.jdbcTemplate.queryForObject(sql, new Object[]{articalId}, Integer.class)+1;
		String resultSql="update tb_artical set artical_lookNum = ? where artical_id=?";
		try{
			this.jdbcTemplate.update(resultSql, new Object[]{result,articalId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public List<Map<String,ArticalBean>> getMyAnswers(int userId, int status) {
		final List<Map<String,ArticalBean>> articals = new ArrayList<>();
		String sql="select artical_id,artical_userId,artical_title,"
				+ "artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime,artical_updateTime,artical_type from tb_artical"
				+ " where artical_userId=? and artical_status=? and artical_type>0"
				+ " order by artical_id desc limit 0,?";
	    this.jdbcTemplate.query(sql,new Object[]{userId,status,FinalModel.ARTICAL_LIMIT},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Map<String ,ArticalBean> map=new HashMap<>();
				ArticalBean answer = new ArticalBean();
				answer.setId(rs.getInt("artical_id"));
				answer.setUserId(rs.getInt("artical_userId"));
				answer.setTitle(rs.getString("artical_title"));
				String s=rs.getString("artical_content");
				if(s.length()>FinalModel.INDEX_WORDS_LIMIT){
					s=s.substring(0,FinalModel.INDEX_WORDS_LIMIT);
				}
				answer.setContent(s);
				answer.setCommentNum(rs.getInt("artical_commentNum"));
				answer.setLookNum(rs.getInt("artical_lookNum"));
				answer.setLikeNum(rs.getInt("artical_likeNum"));
				answer.setCreatDate(rs.getDate("artical_creatTime"));
				answer.setCreatTime(rs.getTime("artical_creatTime"));
				answer.setUpdateDate(rs.getDate("artical_updateTime"));
				answer.setUpdateTime(rs.getTime("artical_updateTime"));
				
				answer.setStatus(FinalModel.PUBLISH_ARTICAL);
				answer.setType(rs.getInt("artical_type"));
				answer.setUser(userDao.getAllInfo(answer.getUserId()));
				map.put("problem", getArtical(answer.getType()));
				map.put("answer", answer);
				articals.add(map);
			}
			
		});
	    return articals;
	}

	@Override
	public List<ArticalBean> freshMyArticals(int userId, int status, int type,
			int articalId) {

		final List<ArticalBean> articals = new ArrayList<>();
		String sql="select artical_id,artical_userId,artical_title,"
				+ "artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime,artical_updateTime from tb_artical"
				+ " where artical_id<? and artical_userId=?"
				+ " and artical_status=? and artical_type=?"
				+ " order by artical_id desc limit 0 , ?";
	    this.jdbcTemplate.query(sql,new Object[]{articalId,userId,status,type,FinalModel.ARTICAL_LIMIT},
	    		new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ArticalBean artical = new ArticalBean();
				artical.setId(rs.getInt("artical_id"));
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				String s=rs.getString("artical_content");
				if(s.length()>FinalModel.INDEX_WORDS_LIMIT){
					s=s.substring(0,FinalModel.INDEX_WORDS_LIMIT);
				}
				artical.setContent(s);
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatDate(rs.getDate("artical_creatTime"));
				artical.setCreatTime(rs.getTime("artical_creatTime"));
				artical.setUpdateDate(rs.getDate("artical_updateTime"));
				artical.setUpdateTime(rs.getTime("artical_updateTime"));
				
				artical.setStatus(FinalModel.PUBLISH_ARTICAL);
				artical.setType(type);
				artical.setUser(userDao.getAllInfo(artical.getUserId()));
				articals.add(artical);
			}
			
		});
	    return articals;
	}

	@Override
	public List<Map<String,ArticalBean>> freshMyAnswers(int userId, int status,int articalId) {
		final List<Map<String,ArticalBean>> articals = new ArrayList<>();
		String sql="select artical_id,artical_userId,artical_title,"
				+ "artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime,artical_updateTime,artical_type from tb_artical"
				+ " where  artical_type>0 and artical_id<? and artical_userId=? and artical_status=?"
				+ " order by artical_id desc limit 0, ?";
	    this.jdbcTemplate.query(sql,new Object[]{articalId,userId,status,FinalModel.ARTICAL_LIMIT},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Map<String ,ArticalBean> map=new HashMap<>();
				ArticalBean answer = new ArticalBean();
				answer.setId(rs.getInt("artical_id"));
				answer.setUserId(rs.getInt("artical_userId"));
				answer.setTitle(rs.getString("artical_title"));
				String s=rs.getString("artical_content");
				if(s.length()>FinalModel.INDEX_WORDS_LIMIT){
					s=s.substring(0,FinalModel.INDEX_WORDS_LIMIT);
				}
				answer.setContent(s);
				answer.setCommentNum(rs.getInt("artical_commentNum"));
				answer.setLookNum(rs.getInt("artical_lookNum"));
				answer.setLikeNum(rs.getInt("artical_likeNum"));
				answer.setCreatDate(rs.getDate("artical_creatTime"));
				answer.setCreatTime(rs.getTime("artical_creatTime"));
				answer.setUpdateDate(rs.getDate("artical_updateTime"));
				answer.setUpdateTime(rs.getTime("artical_updateTime"));
				
				answer.setStatus(FinalModel.PUBLISH_ARTICAL);
				answer.setType(rs.getInt("artical_type"));
				answer.setUser(userDao.getAllInfo(answer.getUserId()));
				map.put("problem", getArtical(answer.getType()));
				map.put("answer", answer);
				articals.add(map);
			}
			
		});
	    return articals;
	}


	

}
