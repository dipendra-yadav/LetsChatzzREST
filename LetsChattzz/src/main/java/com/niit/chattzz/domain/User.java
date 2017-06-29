package com.niit.chattzz.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "C_USER_DETAILS")
@Component
public class User extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	// role can be STUDENT, ALUMINI, EMPLOYEE, ADMIN
	private String role;

	// enabled - true or false - active or inactive user
	// true - authenticated
	// false - user cannot login

	@Column(name = "enabled")
	private String enabled;

	// inOnline - true, false
	// user login - make this isOnline as true
	// user logout - make this isOnline as false
	@Column(name = "isonline")
	private String isOnline;

	// private String dob;
	// private String mob_no;

	// private String gender;

	// private String Address;
	// private char status;

	// constructor
	public User() {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd
		// HH:mm:ss");
		// Date date = new Date();

		// dob = dateFormat.format(date);
		// System.out.println("dateofbirth : " + dob);
	}

	// getters +setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", enabled=" + enabled + ", isOnline=" + isOnline + "]";
	}
	
	

	/*public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
*/
	
	
	
	
}
