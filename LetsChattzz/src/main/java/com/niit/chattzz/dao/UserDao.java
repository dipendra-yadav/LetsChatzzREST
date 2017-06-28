package com.niit.chattzz.dao;

import java.util.List;

import com.niit.chattzz.domain.User;

public interface UserDao {

	public boolean save(User user);

	public boolean update(User user);

	public boolean delete(User user);

	public List<User> listUsers();

	public User get(String id);

	public User delete(String id);

	public User authenticate(String name, String Password);

	public void setOffLine(String userID);

	public void setOnline(String userID);

}
