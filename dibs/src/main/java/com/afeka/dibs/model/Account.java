package com.afeka.dibs.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="ACCOUNTS")
public class Account {
	
	@Id
	private Long id;
	private String name;
	private Date birthdate;
	private String password;
	private String email;

	@Override
	public String toString() {
		return this.id + " " + this.name;
	}
	
	public Account() {
		super();
	}

	public Account(Long id, String name, Date birthdate,String password, String email) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.password = password;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

}
