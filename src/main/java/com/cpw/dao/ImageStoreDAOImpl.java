package com.cpw.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import com.cpw.jdbc.model.ImageStore;

public class ImageStoreDAOImpl implements ImageStoreDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);

	}

	@Override
	public String writeImage(MultipartFile file,long imageId,String type)  {
		logger.info("Entering into ImageStore DAO");
		CpwTemplete<ImageStore> cpwTemplete = new CpwTempleteImpl<ImageStore>();

		String sql = "INSERT INTO IMAGE_PATHSTORE(IMAGE_ID,IMAGE_URL,TYPE)VALUES(?,?,?)";
   

    
		Object[] values = new Object[3];
		values[0] = imageId;
	byte[] decodedBytes;
	try {
		decodedBytes = file.getBytes();
	
		
		ResourceBundle rb = ResourceBundle.getBundle("path");
		String filePath =rb.getString("filepath");
	//String filePath="C:\\tomcat8\\Photos\\";
		String fileName = System.currentTimeMillis() + "";
		//String fileName=file.getOriginalFilename();
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			if (image == null) {
				logger.error("Buffered Image is null");
			}
			
			File imageFile = new File(filePath + fileName+".jpg");

			// write the image
			ImageIO.write(image, "jpg",imageFile);
			values[1] = imageFile.getPath();
			values[2]=type;
			cpwTemplete.upsert(sql,values, jdbcTemplateObject);
			return imageFile.getPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	    }


	

	@Override
	public List<ImageStore> readImage(long imageId) {
		logger.info("Entering into ImagePathList");
		CpwTemplete<ImageStore> cpwTemplete = new CpwTempleteImpl<ImageStore>();
		if(imageId==-1){
			String sql="SELECT * FROM IMAGE_PATHSTORE";
			List<ImageStore> imageStore=cpwTemplete.getRecordList(sql, jdbcTemplateObject, new RowMapper<ImageStore>() {

				@Override
				public ImageStore mapRow(ResultSet rs, int rowNum) throws SQLException {
					ImageStore image=new ImageStore();
					image.setImageId(rs.getLong("IMAGE_ID"));
					String url	=rs.getString("IMAGE_URL");
					
					logger.debug("Image Url :" +url);
					FileInputStream fis = null;
				

					try{


						File file = new File(url);




						fis = new FileInputStream(file);

					} catch (FileNotFoundException e) {
						logger.error("No file in the system" +e);
						e.printStackTrace();
						return null;
					}
					// create FileInputStream which obtains input bytes from a file in a
					// file system
					// FileInputStream is meant for reading streams of raw bytes such as
					// image data. For reading streams of characters, consider using
					// FileReader.

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					try {
						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							// Writes to this byte array output stream
							bos.write(buf, 0, readNum);
							System.out.println("read " + readNum + " bytes,");
						}
						
					} catch (IOException ex) {
						logger.debug("no path in the system" +ex);
						return null;

					}
		

					String imageBase64	= java.util.Base64.getEncoder().encodeToString(bos.toByteArray());
					//lead.setUploadedInputStream(bos.toByteArray());
				image.setImageUrl(imageBase64);
				image.setType(rs.getString("TYPE"));
	
					try {
						
						bos.close();
						fis.close();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


					return image;
				}
			});
			
			return imageStore;

		}else{




			String sql = "SELECT IMAGE_ID,IMAGE_URL FROM IMAGE_PATHSTORE WHERE IMAGE_ID>?";

			List<ImageStore> urlList=jdbcTemplateObject.query(sql, new Object[]{imageId},new RowMapper<ImageStore>() {

				@Override
				public ImageStore mapRow(ResultSet rs, int rowNum) throws SQLException {
					ImageStore imageStore=new ImageStore();
					imageStore.setImageId(rs.getLong("IMAGE_ID"));
					String url	=rs.getString("IMAGE_URL");
					logger.debug("Image Url :" +url);
					FileInputStream fis = null;

					try{


						File file = new File(url);




						fis = new FileInputStream(file);

					} catch (FileNotFoundException e) {
						logger.error("No file in the system" +e);
						e.printStackTrace();
						return null;
					}
					// create FileInputStream which obtains input bytes from a file in a
					// file system
					// FileInputStream is meant for reading streams of raw bytes such as
					// image data. For reading streams of characters, consider using
					// FileReader.

					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					try {
						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							// Writes to this byte array output stream
							bos.write(buf, 0, readNum);
							System.out.println("read " + readNum + " bytes,");
						}
					} catch (IOException ex) {
						logger.debug("no path in the system" +ex);
						return null;

					}

					String imageBase64	= java.util.Base64.getEncoder().encodeToString(bos.toByteArray());
					//lead.setUploadedInputStream(bos.toByteArray());
					imageStore.setImageUrl(imageBase64);


					return imageStore;
				}
			});    

			return urlList;
		}

	}

	@Override
	public int removeImage(long imageId) {
		logger.info("Entering into delete image");
		try{
		String sql="DELETE FROM IMAGE_PATHSTORE WHERE IMAGE_ID=?";
		
		
		return jdbcTemplateObject.update(sql, imageId);
		}catch(Exception e){
			logger.error("NO imageList corresponding to imageId" +e);
			e.printStackTrace();
			return 0;
		}
	}




}
