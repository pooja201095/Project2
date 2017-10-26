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

import com.collaborate.model.Error;
import com.collaborate.model.Job;
import com.collaborate.model.Users;
import com.collaborate.service.JobService;
import com.collaborate.service.UserService;


@RestController
public class JobController {

	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/addJob")
	public ResponseEntity<?> addJob(@RequestBody Job job,HttpSession session)
	{
		System.out.println("adding job...........................");
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Users users=userService.getUser(userid);
		if(!users.getRole().equals("ADMIN")){
			Error error=new Error(6,"Access denied....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			job.setPostedOn(new Date());
			System.out.println("before add job.....");
			jobService.addJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error=new Error(7,"Unable to insert job details....");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping(value="/getAllJobs")
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobService.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@GetMapping(value="/getJob/{jobId}")
	public ResponseEntity<?> getJob(@PathVariable int jobId,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobService.getJob(jobId);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
}
