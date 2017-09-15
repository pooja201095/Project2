package com.collaborate.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.collaborate.dao.UsersDAO;


@RestController
public class UsersController {

	@Autowired
	UsersDAO userDAO;
	
	
}
