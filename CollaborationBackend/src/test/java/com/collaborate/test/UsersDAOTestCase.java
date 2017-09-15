package com.collaborate.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.dao.UsersDAO;
import com.collaborate.model.Forum;
import com.collaborate.model.Users;

public class UsersDAOTestCase {
	static UsersDAO usersDAO;
	
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext annotationConfigApplicationContext= new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.collaborate");
		annotationConfigApplicationContext.refresh();
		usersDAO=(UsersDAO)annotationConfigApplicationContext.getBean("UsersDAO");
		
	}
	
	
	@Test
	public void createUserTest()
	{
		Users users= new Users();
		users.setUserId("pooja@gmail.com");
		users.setFirstName("pooja");
		users.setLastName("Chavan");
		users.setPassword("abcd");
		users.setRole("User");
		users.setStatus("NA");
		users.setIsOnline("No");
		assertTrue("Problem in user creation",usersDAO.createUsers(users));
	}
	
	
	@Ignore
	@Test
	public void approveUserTest()
	{
		Users users= new Users();
		users.setUserId("pooja@gmail.com");
		users.setFirstName("pooja");
		users.setLastName("Chavan");
		users.setPassword("abcd");
		users.setRole("User");
		users.setStatus("A");
		users.setIsOnline("No");
		assertTrue("Problem in user approval",usersDAO.approveUsers(users));
	}
	
	
	@Ignore
	@Test 
	public void getUserTestCase()
	{
		boolean res=false;
		if(usersDAO.getUser("pooja@gmail.com")!=null){
			res=true;
		}
		assertTrue("Problem in getting user by id",res);
		
	}
	
	@Ignore
	@Test 
	public void getUserListTestCase()
	{
		List<Users> listusers=usersDAO.getUsers();
		System.out.println(listusers.size());
		assertTrue("Problem in getting the list",listusers.size()>0);
		
	}
	
	
	@Ignore
	@Test 
	public void editUserTestCase()
	{
		assertTrue("Problem in getting the list",usersDAO.editUsers("pooja@gmail.com"));
		
	}

	@Ignore
	@Test
	public void deleteUserTestCase()
	{
		assertTrue("Problem in deleting user",usersDAO.deleteUsers("pooja@gmail.com"));
	}
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
