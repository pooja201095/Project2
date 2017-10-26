package com.collaborate.service;

import java.util.List;

import com.collaborate.model.Users;

public interface UserService {
	boolean isUserIdValid(String userid);
	boolean isEmailValid(String email);
	public Users login(Users users);
	public boolean createUsers(Users users);
	public boolean isUpdatedEmailValid(String email,String userid);
	public Users getUser(String userid);
	public void editUsers(Users users);

}
