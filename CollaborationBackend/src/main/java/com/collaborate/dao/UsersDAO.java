package com.collaborate.dao;

import java.util.List;

import com.collaborate.model.Users;
public interface UsersDAO {
	public boolean createUsers(Users users);
	public Users getUser(String userid);
	public List<Users> getUsers();
	public boolean approveUsers(Users users);
	public boolean editUsers(String userid);
	public boolean deleteUsers(String userid);

}
