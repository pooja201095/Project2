package com.collaborate.dao;

import java.util.List;

import com.collaborate.model.Users;
public interface UsersDAO {
	boolean isUserIdValid(String userid);
	boolean isEmailValid(String email);
	public Users login(Users users);
	public boolean createUsers(Users users);
	public Users getUser(String userid);
	public void editUsers(Users users);
	public boolean isUpdatedEmailValid(String email,String userid);
	public List<Users> getUsers();
	public boolean approveUsers(Users users);
	public boolean deleteUsers(String userid);
	public List<Users> getApprovedUsers();

}
