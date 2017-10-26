package com.collaborate.service;

import java.util.List;

import com.collaborate.model.Friend;
import com.collaborate.model.Users;

public interface FriendService {
	List<Users> listOfSuggestedUsers(String userid);
	void friendRequest(Friend friend);
	List<Friend> pendingRequests(String toId);
	void updatePendingRequests(Friend friend);
	List<String> listOfFriends(String userid);
}
