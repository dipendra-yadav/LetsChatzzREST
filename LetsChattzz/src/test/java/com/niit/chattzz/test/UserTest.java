package com.niit.chattzz.test;

import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.chattzz.dao.UserDao;
import com.niit.chattzz.domain.User;

public class UserTest {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static User user;
	@Autowired
	private static UserDao userDAO;

	@BeforeClass
	public static void initialize() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.chattzz.*");
		context.refresh();
		user = (User) context.getBean(User.class);
		System.out.println(user);

		userDAO = (UserDao) context.getBean("userDAO");
		System.out.println(userDAO);

	}

	// create User

	@Test
	public void createUserTestCase() {
		user.setId("1");
		user.setName("dipendra");
		user.setEmail("dipendra.techie@gmail.com");

		user.setMob_no("9742758996");
		user.setPassword("1231");
		user.setRole("ROLE_USER");
		user.setAddress("BTM");
		user.setGender("Male");
		user.setStatus('A');
		user.setIsOnline('y');

		boolean flag = userDAO.save(user);
		Assert.assertEquals("createUserTestCase", true, flag);
	}

}
