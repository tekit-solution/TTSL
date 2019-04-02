package com.cpw.model;

public class ImageStoreResponse {
       private long imageId;
       private String imageFile;
       
       private String type;
       
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	@Override
	public String toString() {
		return "ImageStoreResponse [imageId=" + imageId + ", imageFile=" + imageFile + "]";
	}
	
	
	

}
