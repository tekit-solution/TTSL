package com.cpw.jdbc.model;

public class Notification {
	  private String userId;
		private String tokenId;
	   private String message;
	   private String title;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Notification [userId=" + userId + ", tokenId=" + tokenId + ", message=" + message + ", title=" + title
				+ "]";
	}
	   

}
