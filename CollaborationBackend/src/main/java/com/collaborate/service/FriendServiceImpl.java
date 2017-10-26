package com.collaborate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaborate.dao.FriendDAO;
import com.collaborate.model.Friend;
import com.collaborate.model.Users;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDAO friendDAO;
	
	@Override
	public List<Users> listOfSuggestedUsers(String userid) {
		return friendDAO.listOfSuggestedUsers(userid);
	}

	@Override
	public void friendRequest(Friend friend) {
		friendDAO.friendRequest(friend);
		
	}

	@Override
	public List<Friend> pendingRequests(String toId) {
		return friendDAO.pendingRequests(toId);
	}

	@Override
	public void updatePendingRequests(Friend friend) {
		friendDAO.updatePendingRequests(friend);
		
	}

	@Override
	public List<String> listOfFriends(String userid) {
		return friendDAO.listOfFriends(userid);
	}

}
