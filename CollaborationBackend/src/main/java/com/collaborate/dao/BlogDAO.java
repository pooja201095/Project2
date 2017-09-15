package com.collaborate.dao;

import java.util.List;

import com.collaborate.model.Blog;

public interface BlogDAO {
	
	public boolean createBlog(Blog blog);
	public Blog getBlog(int blogid);
	public List<Blog> getBlogs();
	public boolean approveBlog(Blog blog);
	public boolean editBlog(int blogid);
	public boolean deleteBlog(int blogid);
	public List<Blog> getApprovedBlogs();

}
