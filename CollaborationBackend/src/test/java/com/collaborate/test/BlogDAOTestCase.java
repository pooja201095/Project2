package com.collaborate.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaborate.dao.BlogDAO;
import com.collaborate.model.Blog;

public class BlogDAOTestCase {

	static BlogDAO blogDAO;

	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.collaborate");
		annotationConfigApplicationContext.refresh();
		blogDAO=(BlogDAO)annotationConfigApplicationContext.getBean("BlogDAO");
	}

	
	@Test
	public void createBlogTest() 
	{
		Blog blog = new Blog();
		blog.setBlogId(1002);
		blog.setBlogName("Unit Testing");
		blog.setBlogContent("Testing Tool");
		blog.setUserId("Sohan@gmail.com");
		blog.setCreateDate(new java.util.Date());
		blog.setStatus("NA");
		blog.setLikes(0);
		assertTrue("Problem in blog creation", blogDAO.createBlog(blog));
	}
	
	@Ignore
	@Test
	public void ApproveBlogTest() 
	{
		Blog blog = new Blog();
		blog.setBlogId(1001);
		blog.setBlogName("Selenium");
		blog.setBlogContent("Testing Tool");
		blog.setUserId("Pooja@gmail.com");
		blog.setCreateDate(new java.util.Date());
		blog.setStatus("A");
		blog.setLikes(0);
		assertTrue("Problem in blog approval", blogDAO.approveBlog(blog));
	}
	
	@Ignore
	@Test
	public void getAllApprovedBlogs()
	{
		List<Blog> listblog=blogDAO.getBlogs();
		System.out.println(listblog.size());
		assertTrue("Problem in getting the list",listblog.size()>0);
	}
	
	
	@Ignore
	@Test
	public void getBlogByIdTest()
	{
		boolean res=false;
		if(blogDAO.getBlog(1001)!=null)
		{
			res=true;
		}
		assertTrue("Problem in getting Blog by id", res);
	}
	
	@Ignore
	@Test
	public void editBlogTest()
	{
		
		assertTrue("Problem in editing Blog", blogDAO.editBlog(1001));
	}
	
	@Ignore
	@Test
	public void deleteBlogTest()
	{
		assertTrue("Problem in deleting Blog", blogDAO.deleteBlog(1001));
	}


}
