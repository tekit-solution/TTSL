package com.cpw.jdbc.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageStore {

	private long imageId;
	private String imageUrl;
	private MultipartFile imageFile;
	private String type;
	


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	

	

	

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "ImageStore [imageId=" + imageId + ", imageUrl=" + imageUrl + ", imageFile=" + imageFile + "]";
	}





}
