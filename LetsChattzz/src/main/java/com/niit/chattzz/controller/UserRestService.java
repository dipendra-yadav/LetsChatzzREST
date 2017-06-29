package com.niit.chattzz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.chattzz.dao.UserDao;
import com.niit.chattzz.domain.User;
import com.niit.chattzz.domain.Error;

@RestController
public class UserRestService {

	@Autowired
	UserDao udao;

	@Autowired
	User user;

	// register
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		try {

			User savedUser = udao.save(user);
			if (savedUser.getId() == 0) {
				Error error = new Error(2, "Couldnt insert user details ");
				return new ResponseEntity<Error>(error, HttpStatus.CONFLICT);
			} else
				return new ResponseEntity<User>(savedUser, HttpStatus.OK);

		}

		catch (Exception e) {
			e.printStackTrace();
			Error error = new Error(2,
					"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {

		User validUser = udao.authenticate(user.getName(), user.getPassword());
		if (validUser == null) {
			// logger.debug("validUser is null");
			Error error = new Error(1, "Username and password doesn't exists...");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			session.setAttribute("user", validUser);

			validUser.setIsOnline("true");

			udao.update(validUser);

			// logger.debug("validUser is not null");

			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}

	}

	// logout
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			user.setIsOnline("false");
			udao.update(user);
		}
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
