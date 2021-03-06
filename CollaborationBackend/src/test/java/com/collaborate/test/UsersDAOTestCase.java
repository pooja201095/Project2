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
	

	
	/*@Test
	public void createUserTest()
	{
		Users users= new Users();
		users.setUserid("admin@gmail.com");
		users.setFirstName("admin");
		users.setLastName("admin");
		users.setPassword("admin");
		users.setRole("Admin");
		users.setStatus("A");
		users.setIsOnline("No");
		users.setEmail("admin@gmail.com");
		users.setPhoneNo("09999999999");
		assertTrue("Problem in user creation",usersDAO.createUsers(users));
	}
	
	
	@Ignore
	@Test
	public void approveUserTest()
	{
		Users users= new Users();
		users.setUserid("admin@gmail.com");
		users.setFirstName("admin");
		users.setLastName("admin");
		users.setPassword("admin");
		users.setRole("Admin");
		users.setStatus("A");
		users.setIsOnline("No");
		users.setEmail("admin@gmail.com");
		users.setPhoneNo("09999999999");
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
		
		Users users=null;
		assertTrue("Problem in getting the list",usersDAO.editUsers(users));
		
	}


	@Ignore
	@Test
	public void deleteUserTestCase()
	{
		assertTrue("Problem in deleting user",usersDAO.deleteUsers("Sohan@gmail.com"));
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
