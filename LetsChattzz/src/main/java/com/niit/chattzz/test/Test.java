package com.niit.chattzz.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.chattzz.dao.UserDao;
import com.niit.chattzz.domain.User;

public class Test {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.chattzz.*");
		System.out.println("about to refresh*********");
		context.refresh();
		System.out.println("after  refresh*********");

		User user = (User) context.getBean(User.class);
		UserDao udao = (UserDao) context.getBean("userDAO");

		System.out.println("user =" + user);
		System.out.println("UserDao=" + udao);

	}
}
