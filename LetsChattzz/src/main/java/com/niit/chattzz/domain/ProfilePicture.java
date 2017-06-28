package com.niit.chattzz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "C_PROFILEPICTURE")
public class ProfilePicture {

	@Id
	private String username;

	@Lob
	private byte[] image;

	public ProfilePicture() {
	}

	// getters +setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
