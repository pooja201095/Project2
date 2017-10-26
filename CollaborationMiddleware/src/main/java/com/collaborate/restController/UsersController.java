package com.collaborate.restController;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaborate.dao.UsersDAO;
import com.collaborate.model.Users;
import com.collaborate.model.Error;
import com.collaborate.service.UserService;


@RestController
public class UsersController {

	@Autowired
	UsersDAO userDAO;
	@Autowired
	private UserService userService;
	

	
	@PostMapping(value="/createUsers")
	public ResponseEntity<?>createUsers(@RequestBody Users users)
	{
		System.out.println(users.getUserid());
		if(!userService.isUserIdValid(users.getUserid()))
		{
			System.out.println("Error 2");
			Error error=new Error(2,"Username already exists");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		if(!userService.isEmailValid(users.getEmail()))
		{
			System.out.println("Error 3");
			Error error=new Error(3,"Email Id already exists!");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		boolean result= userService.createUsers(users);
		System.out.println(result);
		if(result)
		{
			System.out.println("Success");
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		}
		else{
			System.out.println("Error 1");
			Error error=new Error(1,"Unable to register user details!");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping(value="/login")
	//Each user unique httpsession will get created.
	public ResponseEntity<?>login(@RequestBody Users users,HttpSession session)
	{
		System.out.println(users.getUserid());
		Users vusers=userService.login(users);
		if(vusers==null)
		{
			Error error=new Error(4,"Invalid username/password....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println("Status before update"+vusers.getIsOnline());
		vusers.setIsOnline("yes");
		try{
			System.out.println(vusers.getUserid());
			userService.editUsers(vusers);
			
		}
		catch(Exception e)
		{
			Error error=new Error(6,"Unable to update online status..!");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Status after update"+vusers.getIsOnline());
		session.setAttribute("userid", vusers.getUserid());
		return new ResponseEntity<Users>(vusers,HttpStatus.OK);
	}
	
	@PutMapping(value="/logout")
	public ResponseEntity<?> logout(HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		System.out.println("USER ID is "+userid);
		
		if(userid==null)
		{
		
			Error error=new Error(5,"Unauthorized access...please login..!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Users users= userService.getUser(userid);
		users.setIsOnline("NO");
		userService.editUsers(users);
		session.removeAttribute("userid");
		session.invalidate();
		return new ResponseEntity<Users>(users,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/editUser")
	public ResponseEntity<?>editUser(@RequestBody Users users,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		
		if(userid==null)
		{
			Error error=new Error(4,"Unthorized access.....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		if(!userService.isEmailValid(users.getEmail()))
		{
			System.out.println("Error 3");
			Error error=new Error(3,"Email Id already exists!");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		try{
			userService.editUsers(users);
			return new ResponseEntity<String>("Update successfull",HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error 6");
			Error error=new Error(6,"Unable to update user!");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		}
	
	@PutMapping(value="/updateUsers")
	public ResponseEntity<?> updateUsers(@RequestBody Users users,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
				if(userid==null){
					Error error=new Error(5,"Unauthorized access...please login..!");
					return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
				}
				if(!userService.isUpdatedEmailValid(users.getEmail(), users.getUserid()))
				{
					System.out.println("Error 3");
					Error error=new Error(3,"Email Id already exists!");
					return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
				}
				try{
					userService.editUsers(users);
					return new ResponseEntity<Users>(users,HttpStatus.OK);
				}
					catch(Exception e){
						Error error=new Error(6,"Unable to update user!");
						return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
					}
				
				
	}
	
	@GetMapping(value="/getUser")
	public ResponseEntity<?>getUser(HttpSession session)
	{
		String userid= (String) session.getAttribute("userid");
		System.out.println(userid);
		if(userid== null)
		{
			Error error=new Error(5,"Unauthorized access...please login..!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Users users=userService.getUser(userid);
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		
	}
	
}
