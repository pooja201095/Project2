package com.collaborate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.ProfilePicture;

@Repository
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(profilePicture);
		session.getTransaction().commit();
		System.out.println("Profile picture uploaded successfully.......!");
		
	}

	@Override
	public ProfilePicture getProfilePicture(String username) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture) session.get(ProfilePicture.class,username);
		return profilePicture;
	}

}
