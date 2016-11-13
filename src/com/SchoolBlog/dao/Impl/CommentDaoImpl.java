package com.SchoolBlog.dao.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.SchoolBlog.dao.CommentDao;
import com.SchoolBlog.model.CommentBean;

public class CommentDaoImpl implements CommentDao {
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
	public boolean addComment(CommentBean comment) {
		// TODO Auto-generated method stub
		try{
		      String sql="insert into tb_comment(comment_userId,comment_articalId,comment_content,comment_time) "
				+ " values(?,?,?,?)";
		      Object[] params=new Object[]{comment.getUserId(),comment.getArticalId(),comment.getContent(),comment.getCommentTime()};
		      this.jdbcTemplate.update(sql,params);
		      return true;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getComments(int ArticalId, int nowFloor) {

		
		return null;
	}

}
