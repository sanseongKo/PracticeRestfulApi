package com.test.mypage.domain;

public class AuthInfo {


	private String email;
	private int uid;


	
	public AuthInfo(String email, int uid) {
		this.email = email;
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getEmail() {
		return email;
	}
	
	
	
}
