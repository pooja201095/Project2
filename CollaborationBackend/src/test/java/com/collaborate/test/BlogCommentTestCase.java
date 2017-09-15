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
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.collaborate");
		annotationConfigApplicationContext.refresh();
		blogCommentDAO=(BlogCommentDAO)annotationConfigApplicationContext.getBean("BlogCommentDAO");
	}
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}
*/
	BlogDAO blogDAO;
	UsersDAO usersDAO;
	
	@Test
	public void createBlogCommentTest()
	{
		BlogComment blogComment= new BlogComment();
		blogComment.setId(1001);
		blogComment.setComment("That is a very inspirational piece of work. Well done...KeepGoing!!");
		blogComment.setCreateDate(new Date());
		blogComment.setUserName("Pooja");
		Blog blog= new Blog();
		blog=blogDAO.getBlog(1001);
		blogComment.setBlog(blog);
		Users users= new Users();
		users=usersDAO.getUser("pooja@gmail.com");
		assertTrue("Problem in blog comment creation", blogCommentDAO.createBlogComment(blogComment));
		
	
	}
}
