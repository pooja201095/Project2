package com.collaborate.dao;

import java.util.List;

import com.collaborate.model.BlogComment;

public interface BlogCommentDAO {
	public void addBlogComment(BlogComment blogComment);
	
	public List<BlogComment> getBlogComments(int blogCommentId);
	
}
