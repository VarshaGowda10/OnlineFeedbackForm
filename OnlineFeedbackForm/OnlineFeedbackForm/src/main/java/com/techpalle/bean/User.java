package com.techpalle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="User_Name")
	private String uname;
	private String email;
	private String comments;
	
	public User()
	{
		super();
	}

	public User(int id, String uname, String email, String comments) {
		super();
		this.id = id;
		this.uname = uname;
		this.email = email;
		this.comments = comments;
	}

	public User(String uname, String email, String comments) {
		super();
		this.uname = uname;
		this.email = email;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	

}
