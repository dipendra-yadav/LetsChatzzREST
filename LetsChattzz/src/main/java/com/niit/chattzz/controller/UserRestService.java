package com.niit.chattzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.chattzz.dao.UserDao;
import com.niit.chattzz.domain.User;

@RestController
public class UserRestService {

	@Autowired
	UserDao udao;

	@Autowired
	User user;

	// register
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		User saveduser = udao.save(user);

		return new ResponseEntity<User>(saveduser, HttpStatus.OK);

	}

}
