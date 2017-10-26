package com.collaborate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaborate.dao.ProfilePictureDAO;
import com.collaborate.model.ProfilePicture;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService{

	
	@Autowired
	ProfilePictureDAO profilePictureDAO;
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		profilePictureDAO.uploadProfilePicture(profilePicture);
		
	}

	@Override
	public ProfilePicture getProfilePicture(String username) {
		return profilePictureDAO.getProfilePicture(username);
	}

}
