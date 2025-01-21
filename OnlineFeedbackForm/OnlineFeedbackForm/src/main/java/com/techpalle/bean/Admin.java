package com.techpalle.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String aname;
	private String email;
	private String pasword;
	public Admin()
	{
		
	}
	public Admin(int id, String aname, String email, String pasword) {
		super();
		this.id = id;
		this.aname = aname;
		this.email = email;
		this.pasword = pasword;
	}
	public Admin(String aname, String email, String pasword) {
		super();
		this.aname = aname;
		this.email = email;
		this.pasword = pasword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	
	

}
