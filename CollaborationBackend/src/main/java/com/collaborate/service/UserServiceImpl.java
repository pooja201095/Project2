package com.collaborate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaborate.dao.UsersDAO;
import com.collaborate.model.Users;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersDAO userDAO;
	public boolean isUserIdValid(String userid) {
		System.out.println("service is user valid"+userid);
		return userDAO.isUserIdValid(userid);
	}

	public boolean createUsers(Users users) {
		return userDAO.createUsers(users);
	}

	public Users getUser(String userid) {
		return userDAO.getUser(userid);
	}

	public void editUsers(Users users) {
		System.out.println(users.getUserid());
		userDAO.editUsers(users);
	}
	public boolean isEmailValid(String email) {
		
		return userDAO.isEmailValid(email);
	}

	public Users login(Users users) {
		return userDAO.login(users);
	}

	public boolean isUpdatedEmailValid(String email, String userid) {
		
		return userDAO.isUpdatedEmailValid(email, userid);
	}

}
