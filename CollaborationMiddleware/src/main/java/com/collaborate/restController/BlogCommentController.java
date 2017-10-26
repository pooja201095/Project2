package com.collaborate.restController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaborate.dao.BlogCommentDAO;
import com.collaborate.model.BlogComment;
import com.collaborate.model.Error;
import com.collaborate.model.Users;
import com.collaborate.service.BlogCommentService;
import com.collaborate.service.UserService;

@RestController
public class BlogCommentController {
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@Autowired
	BlogCommentService blogCommentService;

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/addComment")
	public ResponseEntity<?> addComment(@RequestBody BlogComment blogComment,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Users users=userService.getUser(userid);
		blogComment.setCommentedBy(users);
		blogComment.setCommentedOn(new Date());
		try{
			blogCommentService.addBlogComment(blogComment);
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(7,"Unable to post Blog comment..!");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value="/getComments/{id}")
	public ResponseEntity<?> getComments(@PathVariable int id,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComment=blogCommentService.getBlogComments(id);
		return new ResponseEntity<List<BlogComment>>(blogComment,HttpStatus.OK);
	}

		
}
