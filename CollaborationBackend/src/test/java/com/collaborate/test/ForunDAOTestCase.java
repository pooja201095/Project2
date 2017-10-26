package com.collaborate.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaborate.dao.ForumDAO;
import com.collaborate.dao.UsersDAO;
import com.collaborate.model.Blog;
import com.collaborate.model.Forum;
import com.collaborate.model.Users;

public class ForunDAOTestCase {
	
	static ForumDAO forumDAO;
	static UsersDAO usersDAO;

	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.collaborate");
		annotationConfigApplicationContext.refresh();
		forumDAO=(ForumDAO)annotationConfigApplicationContext.getBean("ForumDAO");
	}
	
	
	/*@Ignore
	@Test
	public void createForumTest() 
	{
		Forum forum = new Forum();
		forum.setForumId(1001);
		forum.setForumName("C");
		forum.setForumContent("Programming language");
		Users users=usersDAO.getUser("Pooja@gmail.com");
		forum.setUsers(users);
		forum.setCreateDate(new java.util.Date());
		forum.setStatus("NA");
		assertTrue("Problem in forum creation", forumDAO.createForum(forum));
	}
	
	@Ignore
	@Test
	public void ApproveForumTest() 
	{
		Forum forum = new Forum();
		forum.setForumId(1001);
		forum.setForumName("C");
		forum.setForumContent("programming language");
		Users users=usersDAO.getUser("Pooja@gmail.com");
		forum.setUsers(users);
		forum.setCreateDate(new java.util.Date());
		forum.setStatus("A");
		assertTrue("Problem in forum approval", forumDAO.approveForum(forum));
	}

	@Ignore
	@Test
	public void getAllApprovedForums()
	{
		List<Forum> listforum=forumDAO.getForums();
		System.out.println(listforum.size());
		assertTrue("Problem in getting the list",listforum.size()>0);
	}
	
	@Ignore
	@Test
	public void getForumByIdTest()
	{
		boolean res=false;
		if(forumDAO.getForum(1001)!=null)
		{
			res=true;
		}
		assertTrue("Problem in getting Forum by id", res);
	}
	
	@Ignore
	@Test
	public void editForumTest()
	{
		
		assertTrue("Problem in editing Forum", forumDAO.editForum(1001));
	}
	
	
	@Ignore
	@Test
	public void deleteForumTest()
	{
		assertTrue("Problem in deleting Forum", forumDAO.deleteForum(1001));
	}

*/
}
