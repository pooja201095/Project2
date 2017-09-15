package com.collaborate.dao;

import java.util.List;

import com.collaborate.model.BlogComment;

public interface BlogCommentDAO {
	public boolean createBlogComment(BlogComment blogComment);
	public BlogComment getBlogComment(int id);
	public List<BlogComment> getBlogComments();
	public boolean editBlogComment(int id);
	public boolean deleteBlogComment(int id);

}
