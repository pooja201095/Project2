package com.collaborate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaborate.dao.BlogCommentDAO;
import com.collaborate.model.BlogComment;
@Service
public class BlogCommentServiceImpl implements BlogCommentService{
	
@Autowired
BlogCommentDAO blogCommentDAO;
	public void addBlogComment(BlogComment blogComment) {
		blogCommentDAO.addBlogComment(blogComment);
		
	}

	public List<BlogComment> getBlogComments(int blogCommentId) {
	
		return blogCommentDAO.getBlogComments(blogCommentId);
	}

}
