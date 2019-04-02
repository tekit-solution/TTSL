package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.web.multipart.MultipartFile;

import com.cpw.jdbc.model.ImageStore;

public interface ImageStoreDAO {
	public void setDataSource(DataSource ds);
	
	public List<ImageStore> readImage(long imageId);
	
	public String writeImage(MultipartFile file,long imageId,String type);
	
	public int removeImage(long imageId);

}
