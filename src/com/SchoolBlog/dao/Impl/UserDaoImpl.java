package com.SchoolBlog.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.SchoolBlog.dao.UserDao;
import com.SchoolBlog.model.UserBean;

public class UserDaoImpl implements UserDao {
	
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Integer adduser(UserBean user) {
		try{
		      String sql="INSERT INTO tb_user(user_xuehao,user_password,user_realname,user_sex,user_info,user_grade) "
				+ " VALUES(?,?,?,?,?,?)";
		      Object[] params=new Object[]{Integer.parseInt(user.getXuehao()),user.getPassword(),user.getRealname(),user.getSex(),user.getInfo(),user.getGrade()};
		      this.jdbcTemplate.update(sql,params);
		      String resultSql="select user_id from tb_user where user_xuehao=?";
		      return (int)this.jdbcTemplate.queryForObject(resultSql,
		    		  new Object[]{Integer.parseInt(user.getXuehao())},Integer.class);
		      
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			return -1;
		}
	}
	
	@Override
	public String ensureAccount(String xuehao){
		String password=null;
		try{
			String sql="SELECT user_password FROM tb_user WHERE user_xuehao=?";
			password=this.jdbcTemplate.queryForObject(sql, new Object[]{xuehao}, String.class);
			return password;
		}catch(Exception e){
			e.getMessage();
			return password;
		}
	}

	@Override
	public Integer alertUserInfo(UserBean user) {
		// 修改用户信息，成功返回用户id，失败返回-1
		try{
			String sql="UPDATE user SET user_password=？,user_sex=？,user_info=？ WHERE user_id=?";
		    this.jdbcTemplate.update(sql, new Object[]{user.getPassword(),user.getSex(),user.getInfo(),user.getId()});
		    return (Integer)user.getId();
		}catch(Exception e){
			e.getMessage();
			new RuntimeException("修改用户失败!");
			return -1;
		}
	}

	@Override
	public 	Integer addUserLike(int user_id,int num) {
		// TODO Auto-generated method stub
		try{
			String sql="update tb_user SET user_like+"+num+" WHERE user_id=" + user_id;
			return this.jdbcTemplate.update(sql);
		}catch(Exception e){
			e.getMessage();
			return -1;
		}
		
	}

	@Override
	public Integer addUserScore(int user_id,int score){
		try{
			String sql="UPDATE TB_USER SET user_like+" +score +"WHERE user_id=" + user_id;
			return this.jdbcTemplate.update(sql);
		}catch(Exception e){
			e.getMessage();
			return -1;
		}
		
	}

	@Override
	public String getRealnameById(int userId) {
		// TODO Auto-generated method stub
		String sql="select user+realname from tb_user where user_id=?";
		String realname = this.jdbcTemplate.queryForObject(sql, String.class);
		return realname;
	}

	@Override
	public UserBean getAllInfo(int userId) {
		// TODO Auto-generated method stub
		String sql="seclect * from tb_user where user_id=?";
		UserBean user=new UserBean();
		user.setId(userId);
		this.jdbcTemplate.query(sql, new Object[]{userId}, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				user.setXuehao(rs.getString("user_xuehao"));
				user.setRealname(rs.getString("user_realname"));
				user.setSex(rs.getString("user_sex"));
				user.setInfo(rs.getString("user_info"));
				user.setLike(rs.getInt("user_like"));
				user.setScore(rs.getInt("user_score"));
			}
		});
		return user;
	}
}
