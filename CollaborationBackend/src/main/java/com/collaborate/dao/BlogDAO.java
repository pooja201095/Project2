package com.collaborate.dao;

import java.util.List;

import com.collaborate.model.Blog;

public interface BlogDAO {
	
	public void addBlog(Blog blog);
	public List<Blog> getBlogs(int approved);
	public Blog getBlogById(int id);
	public void updateBlog(Blog blog);
	
}
