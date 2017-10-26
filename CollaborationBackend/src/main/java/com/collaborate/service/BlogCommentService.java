package com.collaborate.service;

import java.util.List;

import com.collaborate.model.BlogComment;

public interface BlogCommentService {
public void addBlogComment(BlogComment blogComment);
	
	public List<BlogComment> getBlogComments(int blogCommentId);
}
