package com.cpw.jdbc.model;

public class Login {
 private String userId;
 private String password;
 private String tokenId;
 public String getUserName() {
		return userId;
	}
	public void setUserName(String userName) {
		this.userId = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	@Override
	public String toString() {
		return "Login [userId=" + userId + ", password=" + password + ", tokenId=" + tokenId + "]";
	}
	
 

}
