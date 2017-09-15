package com.collaborate.restController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaborate.dao.BlogDAO;
import com.collaborate.model.Blog;

@RestController
public class BlogController {
	@Autowired
	BlogDAO BlogDAO;
	
	@GetMapping("/test")
	public ResponseEntity<String> testMethod()
	{
		return new ResponseEntity<String> ("Test RestController",HttpStatus.OK);
	}
	
	@GetMapping(value="/getAllBlogs")
		public ResponseEntity<List<Blog>> getAllBlogs()
		{
			ArrayList<Blog> listBlogs= new ArrayList<Blog>();
			listBlogs=(ArrayList<Blog>) BlogDAO.getBlogs();
			System.out.println("GetBlogs method successfully called.......");
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
	
	@PostMapping(value="/createBlog")
	public ResponseEntity<String>createBlog(@RequestBody Blog blog)
	{
		blog.setStatus("A");
		blog.setLikes(0);
		blog.setCreateDate(new Date());
		if(BlogDAO.createBlog(blog))
		{
			return new ResponseEntity<String>("Blog created successfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in blog creation",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/approveBlog/{blogId}")
	public ResponseEntity<String>approveBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=BlogDAO.getBlog(blogId);
		if(BlogDAO.approveBlog(blog))
		{
			return new ResponseEntity<String>("Blog approved",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in blog approval",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@GetMapping(value="/getApprovedBlogs")
	public ResponseEntity<List<Blog>> getApprovedBlogs()
	{
		ArrayList<Blog> listBlogs= new ArrayList<Blog>();
		listBlogs=(ArrayList<Blog>) BlogDAO.getApprovedBlogs();
		System.out.println("Get Approve blogs method successfully called.......");
		return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
	}
	
	@GetMapping(value="/deleteBlog/{blogId}")
	public ResponseEntity<String>deleteBlog(@PathVariable("blogId") int blogId)
	{
		
		if(BlogDAO.deleteBlog(blogId))
		{
			return new ResponseEntity<String>("Blog deleted",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in blog deletion",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/editBlog/{blogId}")
	public ResponseEntity<String>editBlog(@PathVariable("blogId") int blogId)
	{
		
		if(BlogDAO.editBlog(blogId))
		{
			return new ResponseEntity<String>("Blog edited",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in blog editing",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<String>getBlog(@PathVariable("blogId") int blogId)
	{
		
		if(BlogDAO.getBlog(blogId) != null)
		{
			return new ResponseEntity<String>("Blog returned ",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in getting blog",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
