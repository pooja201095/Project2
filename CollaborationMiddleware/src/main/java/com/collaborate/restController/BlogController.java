package com.collaborate.restController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaborate.dao.BlogDAO;
import com.collaborate.model.Blog;
import com.collaborate.model.Error;
import com.collaborate.model.Users;
import com.collaborate.service.BlogService;
import com.collaborate.service.UserService;

@RestController
public class BlogController {
	@Autowired
	BlogDAO BlogDAO;
	@Autowired
	BlogService blogService;
	@Autowired
	UserService userService;
	
	
	
	@PostMapping(value="/AddBlog")
	public ResponseEntity<?>addBlog(@RequestBody Blog blog,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			Error error=new Error(5,"Unauthorized access...please login..!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			blog.setPostedOn(new Date());
			Users postedBy=userService.getUser(userid);
			blog.setPostedBy(postedBy);
			try{
				blogService.addBlog(blog);
				return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			}
			catch(Exception e)
			{
				Error error=new Error(6,"Unable to post Blog.......!");
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	
	@GetMapping(value="/getBlogs/{approved}")
	public ResponseEntity<?> getBlogs(@PathVariable int approved,HttpSession session)
	{
		String userid= (String) session.getAttribute("userid");
		if(userid==null)
		{
			Error error=new Error(5,"Unauthorized access...please login..!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Blog> blogs=blogService.getBlogs(approved);
		
		return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
	}
	
	@GetMapping(value="/getBlogById/{id}")
	public ResponseEntity<?>getBlogById(@PathVariable("id") int id,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		
		if(userid==null)
		{
			Error error=new Error(5,"Unauthorized access...please login..!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Blog blog=blogService.getBlogById(id);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<?>updateBlog(@RequestBody Blog blog, HttpSession session){
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			Error error=new Error(5,"Unauthorized access...please login..!");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		if(!blog.isApproved() && blog.getRejectionReason()==null)
		{
			blog.setRejectionReason("Not Mentioned");
		}
		blogService.updateBlog(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
	}
	
	
}
