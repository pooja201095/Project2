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
	@Override
	public boolean isUserIdValid(String userid) {
		System.out.println("service is user valid"+userid);
		return userDAO.isUserIdValid(userid);
	}

	@Override
	public boolean createUsers(Users users) {
		return userDAO.createUsers(users);
	}

	@Override
	public Users getUser(String userid) {
		return userDAO.getUser(userid);
	}

	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveUsers(Users users) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editUsers(Users users) {
		System.out.println(users.getUserid());
		userDAO.editUsers(users);
	}

	@Override
	public boolean deleteUsers(String userid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Users> getApprovedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmailValid(String email) {
		
		return userDAO.isEmailValid(email);
	}

	@Override
	public Users login(Users users) {
		return userDAO.login(users);
	}

	@Override
	public boolean isUpdatedEmailValid(String email, String userid) {
		
		return userDAO.isUpdatedEmailValid(email, userid);
	}

}
