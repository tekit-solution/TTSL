package com.cpw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ImageStoreRequest {

	private long imageId;
	private String imageFile;


	@JsonCreator
	public ImageStoreRequest(@JsonProperty("imageId")long imageId)
	{

		this.imageId=imageId;
		//this.imageFile=imageFile;
	}

	

	


	public String getImageFile() {
		return imageFile;
	}






	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}






	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}







}
