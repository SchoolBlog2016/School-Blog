package com.SchoolBlog.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.SchoolBlog.dao.CommentDao;
import com.SchoolBlog.dao.UserDao;
import com.SchoolBlog.model.CommentBean;
import com.SchoolBlog.model.FinalModel;

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
	
	@Resource
	private UserDao userDao;
	
	@Override
	public boolean addComment(CommentBean comment) {
		
		try{
			  int articalId=comment.getArticalId();
		      String sql="select count(*) from tb_comment where comment_articalId="+articalId;
		      int nextFloor=this.jdbcTemplate.queryForObject(sql, Integer.class)+1;
		      sql="INSERT INTO tb_comment(comment_userId,comment_articalId,"
		      		+ "comment_content,comment_floor,comment_replyFloor,comment_time) "
		      		+ " VALUES(?,?,?,?,?,NOW())";
		      Object params[]=new Object[]{
		    		  comment.getUserId(),
		    		  comment.getArticalId(),
		    		  comment.getContent(),
		    		  nextFloor,
		    		  comment.getReplyFloor(),
		      };
		      boolean flag=(1==this.jdbcTemplate.update(sql,params))?true:false;
		      return flag;
		}catch(Exception e){
			e.getMessage();
			return false;
		}
	}

	@Override
	public List<CommentBean> getComments(int articalId, int nowFloor) {
		String sql="select * from tb_comment where comment_articalId=? and "
				+ "comment_floor> ? "
				+ "order by comment_floor limit 0,?";
		Object params[]=new Object[]{
				articalId,
				nowFloor,
				FinalModel.COMMENT_LIMIT
		};
		List<CommentBean> comments=new ArrayList<>();
		this.jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CommentBean comment=new CommentBean();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setArticalId(rs.getInt("comment_articalId"));
				comment.setUserId(rs.getInt("comment_userId"));
				comment.setContent(rs.getString("comment_content"));
				comment.setFloor(rs.getInt("comment_floor"));
				comment.setReplyFloor(rs.getInt("comment_replyFloor"));
				comment.setCommentTime(rs.getString("comment_time"));
				comment.setUserName(userDao.getRealnameById(rs.getInt("comment_userId")));
				comments.add(comment);
			}
		});
		return comments;
	}

}
