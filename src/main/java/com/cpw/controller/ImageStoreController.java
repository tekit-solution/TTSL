package com.cpw.controller;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cpw.model.ImageStoreResponse;
import com.cpw.services.ImageStoreImpl;

@RestController
@MultipartConfig
public class ImageStoreController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="imagePathStore", method = RequestMethod.POST,consumes="multipart/form-data" )

	public ResponseEntity<?> writeImage(@RequestHeader("imageid")long imageId,@RequestHeader("type")String type ,@RequestParam(value="file",required=false) MultipartFile file)
	{
		logger.info("Entering into controller");
		try {


			ImageStoreImpl imageStoreImpl = new ImageStoreImpl();
			String response=imageStoreImpl.writeImage(file,imageId,type);
			
			if(response==null){
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);

			}else {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

		}
		

	}

	@RequestMapping(value="imagePath/{imageId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> readPath(@PathVariable("imageId")long imageId)
	{
		logger.info("Entering into ImagePath List");
		try {
			logger.debug("ID corresponding to Image" +imageId);

			ImageStoreImpl imageStoreImpl=new ImageStoreImpl();
			List<ImageStoreResponse> imageResponse=imageStoreImpl.imagePathList(imageId);

		   
			if(imageResponse!=null && !imageResponse.isEmpty())
			{
				return new ResponseEntity<Object>(imageResponse,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Object>(imageResponse,HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			logger.error("NO ImagePath List in the system" +e);
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		

	}



}
