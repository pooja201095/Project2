package com.collaborate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaborate.dao.BlogDAO;
import com.collaborate.model.Blog;
@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDAO blogDAO;
	public void addBlog(Blog blog) {
		blogDAO.addBlog(blog);
		
	}
	public List<Blog> getBlogs(int approved) {
		
		return blogDAO.getBlogs(approved);
	}
	public Blog getBlogById(int id) {
		
		return blogDAO.getBlogById(id);
	}
	public void updateBlog(Blog blog) {
	 blogDAO.updateBlog(blog);
		
	}

}
