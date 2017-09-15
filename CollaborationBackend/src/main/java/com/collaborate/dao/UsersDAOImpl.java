package com.collaborate.dao;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.Users;

@Repository("UsersDAO")
public class UsersDAOImpl implements UsersDAO {

	SessionFactory sessionFactory;
	public UsersDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	@Override
	public boolean createUsers(Users users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		System.out.println("User created....");
		return true;
	}

	@Override
	public Users getUser(String userid) {
		try{
			Session session=sessionFactory.openSession();
			Users users=(Users) session.get(Users.class,userid);
			System.out.println("Got the user.....");
			return users;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		return null;
		}
	}

	@Transactional
	@Override
	public List<Users> getUsers() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Users where status='A'");
		List<Users> listuser=query.list();
		session.close();
		return listuser;
	}

	
	@Transactional
	@Override
	public boolean approveUsers(Users users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		System.out.println("User approved..........");
		return true;
	}

	@Override
	public boolean editUsers(String userid) {
		try{
			Session session=sessionFactory.openSession();
			Users users= (Users) session.get(Users.class,userid);
			users.setIsOnline("yes");
			session.flush();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		return false;
		}
	}

	@Override
	public boolean deleteUsers(String userid) {
		try
		{
		Session session= sessionFactory.openSession();
		Users users=(Users) session.get(Users.class,userid);
		session.delete(users);
		session.flush();
		session.close();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
