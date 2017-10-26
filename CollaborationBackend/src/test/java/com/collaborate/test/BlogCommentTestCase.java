package com.collaborate.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaborate.dao.BlogCommentDAO;
import com.collaborate.dao.BlogDAO;
import com.collaborate.dao.UsersDAO;
import com.collaborate.model.Blog;
import com.collaborate.model.BlogComment;
import com.collaborate.model.Users;

public class BlogCommentTestCase {

	static BlogCommentDAO blogCommentDAO; 

	static BlogDAO blogDAO;
	static UsersDAO usersDAO;
	
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.collaborate");
		annotationConfigApplicationContext.refresh();
		blogCommentDAO=(BlogCommentDAO)annotationConfigApplicationContext.getBean("BlogCommentDAO");
		blogDAO=(BlogDAO) annotationConfigApplicationContext.getBean("BlogDAO");
		usersDAO=(UsersDAO) annotationConfigApplicationContext.getBean("UsersDAO");
	}
	
	/*@Test
	public void createBlogCommentTest()
	{
		BlogComment blogComment= new BlogComment();
		blogComment.setId(1002);
		blogComment.setComments("That is a very inspirational piece of work. Well done...KeepGoing!!");
		blogComment.setCreateDate(new Date());
		blogComment.setUserName("pooja11");
		Blog blog= blogDAO.getBlog(1002);
		blogComment.setBlog(blog);
		Users users= new Users();
		users=usersDAO.getUser("Sohan@gmail.com");
		blogComment.setUsers(users);
		assertTrue("Problem in blog comment creation", blogCommentDAO.createBlogComment(blogComment));
		
	
	}*/
}
