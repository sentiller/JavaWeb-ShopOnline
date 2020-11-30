package com.shop.entity;

public class User {
	private String name;
	private String pwd;
	private Integer status;
	private String email;

	public User() {
		super();
	}

	public User(String name, String pwd, Integer status, String email) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.status = status;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", pwd=" + pwd + ", status=" + status + ", email=" + email + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
