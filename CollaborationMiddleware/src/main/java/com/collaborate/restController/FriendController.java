package com.collaborate.restController;

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

import com.collaborate.model.Error;
import com.collaborate.model.Friend;
import com.collaborate.model.Users;
import com.collaborate.service.FriendService;
import com.collaborate.service.UserService;

@RestController
public class FriendController {
	@Autowired
	FriendService friendService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/getSuggestedUsers")
	public ResponseEntity<?> getSuggestedUsers(HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Users> suggestedUsers=friendService.listOfSuggestedUsers(userid);
		return new ResponseEntity<List<Users>>(suggestedUsers,HttpStatus.OK);
	}
	
	@GetMapping(value="/friendRequest/{toId}")
	public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session)
	{
		System.out.println("in fnd req controller.........");
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Friend friend=new Friend();
		friend.setFromId(userid);
		friend.setToId(toId);
		friend.setStatus('P');
		friendService.friendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/pendingRequests")
	public ResponseEntity<?> pendingRequests(HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> pendingRequests=friendService.pendingRequests(userid);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	
	
	@PutMapping(value="/updatePendingRequests")
	public ResponseEntity<?> updatePendingRequests(@RequestBody Friend friend,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(friend.getFriendId());
		friendService.updatePendingRequests(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/listOfFriends")
	public ResponseEntity<?> listOfFriends(HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			Error error=new Error(5,"Unauthorized access....");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<String> friends=friendService.listOfFriends(userid);
		return new ResponseEntity<List<String>>(friends,HttpStatus.OK);
	}
		
}
