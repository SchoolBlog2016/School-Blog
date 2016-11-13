package com.SchoolBlog.dao.Impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.SchoolBlog.dao.LikeDao;

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
			String sql="delete tb_like where like_articalId=? and like_userId=?";
			this.jdbcTemplate.update(sql,new Object[]{articalId,userId});
			return false;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean isLike(int articalId, int userId) {
		// TODO Auto-generated method stub
		String sql="select like_id from tb_like where like_articalId=? and like_userId= ?";
		this.jdbcTemplate.queryForObject(sql, new Object[]{articalId,userId}, int.class);
		return true;
	}

	
}
