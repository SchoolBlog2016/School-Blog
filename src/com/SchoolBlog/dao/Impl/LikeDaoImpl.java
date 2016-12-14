package com.SchoolBlog.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.SchoolBlog.dao.LikeDao;
import com.SchoolBlog.model.FinalModel;

public class LikeDaoImpl implements LikeDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean addLike(int articalId, int userId) {
		// TODO Auto-generated method stub
		try{
			
			String sql="insert into tb_like(like_articalId,like_userId) values(?,?)";
			this.jdbcTemplate.update(sql, new Object[]{articalId,userId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean delLike(int articalId, int userId) {
		// TODO Auto-generated method stub
		try{
			String sql="delete from tb_like where like_articalId=? and like_userId=?";
			this.jdbcTemplate.update(sql,new Object[]{articalId,userId});
			return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean isLike(int articalId, int userId) {
		// TODO Auto-generated method stub
		String sql="select count(*) from tb_like where like_articalId=? and like_userId= ?";
		int isLike=this.jdbcTemplate.queryForObject(sql, new Object[]{articalId,userId}, Integer.class);
		return 1==isLike?true:false;
	}

	@Override
	public List<Integer> mylike(int userId,int page) {
		String sql="select like_articalId as id from tb_like"
				+ " where like_userId= ? order by like_userId desc limit ?,?";
		List<Integer> articalsId=new ArrayList<>();
		this.jdbcTemplate.query(sql, new Object[]{userId,
				FinalModel.ARTICAL_LIMIT*(page-1),
				FinalModel.ARTICAL_LIMIT*page
				},new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				articalsId.add(rs.getInt("id"));
			}
		});
		return articalsId;
	}

	
}
