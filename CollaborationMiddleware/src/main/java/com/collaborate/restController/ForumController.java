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

import com.collaborate.dao.ForumDAO;
import com.collaborate.model.Blog;
import com.collaborate.model.Forum;

@RestController
public class ForumController {
	@Autowired
	ForumDAO forumDAO;
	
	@PostMapping(value="/createForum")
	public ResponseEntity<String>createForum(@RequestBody Forum forum)
	{
		forum.setCreateDate(new Date());
		forum.setStatus("NA");
		if(forumDAO.createForum(forum))
		{
			return new ResponseEntity<String>("Forum created successfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Forum not created",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/getAllForums")
	public ResponseEntity<List<Forum>> getAllForums()
	{
		ArrayList<Forum> listForums= new ArrayList<Forum>();
		listForums=(ArrayList<Forum>) forumDAO.getForums();
		System.out.println("GetForums method successfully called.......");
		return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
	}
	
	@GetMapping(value="/getApprovedForums")
	public ResponseEntity<List<Forum>> getApprovedForums()
	{
		ArrayList<Forum> listForums= new ArrayList<Forum>();
		listForums=(ArrayList<Forum>) forumDAO.getApprovedForums();
		System.out.println("GetForums method successfully called.......");
		return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
	}
	
	@GetMapping(value="/approveForum/{forumId}")
	public ResponseEntity<String> approveForums(@PathVariable("forumId") int forumId)
	{
		Forum forum=forumDAO.getForum(forumId);
		if(forumDAO.approveForum(forum))
		{
			return new ResponseEntity<String>("Forum approved successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Forum not approved successfully",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/editForum/{forumId}")
	public ResponseEntity<String> editForums(@PathVariable("forumId") int forumId)
	{
		if(forumDAO.editForum(forumId))
		{
			return new ResponseEntity<String>("Forum edited succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Forum not edited succesfully",HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@GetMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForums(@PathVariable("forumId") int forumId)
	{
		if(forumDAO.deleteForum(forumId))
		{
			return new ResponseEntity<String>("Forum deleted succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Forum not deleted succesfully",HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	@GetMapping(value="/getForum/{forumId}")
	public ResponseEntity<String>getForum(@PathVariable("forumId") int forumId)
	{
		
		if(forumDAO.getForum(forumId) != null)
		{
			return new ResponseEntity<String>("Forum returned ",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in getting forum",HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
