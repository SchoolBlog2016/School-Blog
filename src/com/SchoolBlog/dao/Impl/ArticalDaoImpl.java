package com.SchoolBlog.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.SchoolBlog.dao.ArticalDao;
import com.SchoolBlog.model.ArticalBean;

public class ArticalDaoImpl implements ArticalDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

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
		try{
		      String sql="insert into tb_artical"
		      		+ "(artical-userId,artical_title,"
		      		+ "artical_content,atical_creatTime) "
				+ " values(?,?,?,NOW())";
		      Object[] params=new Object[]{
		    		  artical.getUserId(),
		    		  artical.getTitle(),
		    		  artical.getContent()};
		      this.jdbcTemplate.update(sql,params);
		      return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public List<ArticalBean> getArticals() {
		// TODO Auto-generated method stub
		int firstParam = 0;
		int secondParam = 0;
		String countSql="seclet count(*)from tb_artical";
		int count=this.jdbcTemplate.queryForObject(countSql, int.class);
		
		//这里的处理后续如果需要应当考虑limit>count等多种情况（健壮性的角度），一般来说是不需要的
		if(count>0&&count<limit){
			firstParam=count-limit+1;
			secondParam=count;
		}else
			return null;
		
		final List<ArticalBean> articals = new ArrayList<>();
		String sql="select artical_userId,artical_title,artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime from tb_artical where artical_status=1 and artical_id between ? and ? order by artical_id desc";
	    this.jdbcTemplate.queryForList(sql,new Object[]{firstParam,secondParam},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ArticalBean artical = new ArticalBean();
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				artical.setContent(rs.getString("artical_content"));//后面将这里进行文章行截取
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatTime(rs.getString("artical_userId"));
				articals.add(artical);
			}
			
		});
	    return articals;
	}

	@Override
	public List<Map<String, Object>> freshArticals(int id) {
		// TODO Auto-generated method stub
		int firstParam = 0;
		int secondParam = 0;
		
		String countSql = "select count(*) from tb_artical where artical_id<? and artical_status=1 order by artical_id desc";
		int count = this.jdbcTemplate.queryForObject(countSql, int.class);
		if(count>limit) {
		    secondParam = count;
		    firstParam = count -limit+1;	    
		}else if(count>0 && count<limit){
			secondParam = id-1;
			firstParam = 1;
		}else {
		    return null;
		}
		String sql = "select * from tb_artical where artical_id between ? and ? order by artical_id desc ";	
		return this.jdbcTemplate.queryForList(sql,new Object[] {firstParam,secondParam});
	}
	@Override
	public ArticalBean getArtical(final int articalId) {
		// TODO Auto-generated method stub
		String sql="select artical_userId,artical_title,artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
				+ "artical_creatTime from tb_artical where artical_id=?";
		final ArticalBean artical=new ArticalBean();
		artical.setId(articalId);
		
		this.jdbcTemplate.query(sql, new Object[]{articalId}, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				artical.setUserId(rs.getInt("artical_userId"));
				artical.setTitle(rs.getString("artical_title"));
				artical.setContent(rs.getString("artical_content"));
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatTime(rs.getString("artical_userId"));
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
			String sql="update tb_artical set artical_content=concat(artical_content,?) where artical_id=?";
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
		int num = this.jdbcTemplate.queryForObject(sql, new Object[]{articalId}, int.class)+1;
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
		int num=(isAddLike==true?-1:1);
		int result = this.jdbcTemplate.queryForObject(sql, new Object[]{articalId}, int.class)+num;
		String resultSql="update tb_artical set artical_commentNum = ? where artical_id=?";
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
			String sql="update tb_artical set artical_content=? where artical_id=?";
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
	public List<ArticalBean> getMyArticals(int userId) {
		// TODO Auto-generated method stub
		int firstParam = 0;
		int secondParam = 0;
		String countSql="seclet count(*)from tb_artical where artical_userId="+userId;
		int count=this.jdbcTemplate.queryForObject(countSql, int.class);
		String sql=null;
		
		//这里的处理后续如果需要应当考虑limit>count等多种情况（健壮性的角度），一般来说是不需要的
		if(count>0&&count<limit){
			firstParam=count-limit+1;
			secondParam=count;
			//下面的这种情况的sql语句有bug，后续优化
			sql="select artical_id,artical_title,artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
					+ "artical_creatTime,artical_Status from tb_artical where artical_userId =? order by artical_id desc";
		}else if(count>0&&count<limit){
			sql="select artical_id,artical_title,artical_content,artical_commentNum,artical_lookNum,artical_likeNum,"
					+ "artical_creatTime,artical_Status from tb_artical where artical_UserID =? order by artical_id desc";
		}else{
			return null;
		}
		
		final List<ArticalBean> articals = new ArrayList<>();
		
	    this.jdbcTemplate.queryForList(sql,new Object[]{firstParam,secondParam},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ArticalBean artical = new ArticalBean();
				artical.setUserId(rs.getInt("artical_id"));
				artical.setTitle(rs.getString("artical_title"));
				artical.setContent(rs.getString("artical_content"));//后续优化将这里进行文章行截取
				artical.setCommentNum(rs.getInt("artical_commentNum"));
				artical.setLookNum(rs.getInt("artical_lookNum"));
				artical.setLikeNum(rs.getInt("artical_likeNum"));
				artical.setCreatTime(rs.getString("artical_status"));
				articals.add(artical);
			}
			
		});
	    return articals;
	}

	@Override
	public boolean addLookNum(int articalId) {
		// TODO Auto-generated method stub
		String sql="select artical_lookNum from tb_artical where artical_id=?";
		int result = this.jdbcTemplate.queryForObject(sql, new Object[]{articalId}, int.class)+1;
		String resultSql="update tb_artical set artical_commentNum = ? where artical_id=?";
		try{
			this.jdbcTemplate.update(resultSql, new Object[]{result,articalId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}
}
