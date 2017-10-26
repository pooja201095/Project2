package com.collaborate.dao;

import java.util.List;





import javax.jws.soap.SOAPBinding.Use;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.Users;

@Repository("UsersDAO")
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	SessionFactory sessionFactory;
	public UsersDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public boolean createUsers(Users users) {
		try{
			System.out.println("UserDaoImpl"+users.getUserid());
		Session session=sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(users);
		System.out.println("User created....");
		
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public Users getUser(String userid) {
		try{
			Session session=sessionFactory.getCurrentSession();
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
	public void editUsers(Users users) {
			Session session=sessionFactory.getCurrentSession();
			session.update(users);
		
	}

	
	@Transactional
	public boolean isUserIdValid(String userid) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println("dao Is User valid"+userid);
		Users users=(Users)session.get(Users.class,userid);
		
		if(users==null)
		{
			return true;
		}
		else{
		return false;
		}
	
	}
	
	@Transactional
	public boolean isEmailValid(String email) {
		Session session= sessionFactory.getCurrentSession();
	/*	session.beginTransaction();*/
		Query query=session.createQuery("from Users where email=:email");
		query.setString("email",email);
		Users users=(Users) query.uniqueResult();
		/*session.getTransaction().commit();*/
		if(users==null)
		{
			return true;
		}
		else{
		return false;
		}
	}

	@Transactional
	public Users login(Users users) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Users where userid=? and password=?");
		query.setString(0, users.getUserid());
		query.setString(1, users.getPassword());
		users=(Users) query.uniqueResult();
		return users;
	}

	@Transactional
	public boolean isUpdatedEmailValid(String email, String userid) {
		Session session=sessionFactory.getCurrentSession();
		Query query= session.createQuery("from Users where email=? and userid!=?");
		query.setString(0, email);
		query.setString(1, userid);
		Users users= (Users) query.uniqueResult();
		if(users==null)
		{
			return true;
		}
		else{
			return false;
		}
	}

}
