package com.cpw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationRequest {
    private String userId;
	private String tokenId;
   private String message;
   private String title;
   
   public NotificationRequest(@JsonProperty("userId")String userId,@JsonProperty("tokenId")String tokenId,@JsonProperty("message")
   String message,@JsonProperty("title")String title){
	   this.userId=userId;
	   this.tokenId=tokenId;
	   this.message=message;
	   this.title=title;
   }
   




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
	return "NotificationRequest [userId=" + userId + ", tokenId=" + tokenId + ", message=" + message + ", title="
			+ title + "]";
}
   
   
   

}
