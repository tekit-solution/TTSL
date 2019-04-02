package com.cpw.controller;

import java.io.ByteArrayInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.services.PdfCreationImpl;

@RestController
public class PdfCreationController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*@RequestMapping(value = "/pdf/{bookingId}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends PdfCreationResponse>> leads(@PathVariable("bookingId") long bookingId) {
		logger.info("Entering into pdf list");
		try {
			logger.debug("Booking Id" +bookingId);
			PdfCreationImpl pdfImpl = new PdfCreationImpl();
			List<PdfCreationResponse> pdfResponse = pdfImpl.pdfData(bookingId);
			if (pdfResponse == null || pdfResponse.isEmpty()) {
				return new ResponseEntity<List<? extends PdfCreationResponse>>(pdfResponse, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<? extends PdfCreationResponse>>(pdfResponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends PdfCreationResponse>>(Collections.emptyList(),
					HttpStatus.BAD_REQUEST);
		}*/
	@RequestMapping(value="/pdf/{bookingId}/{userId}", method=RequestMethod.GET,produces=MediaType.APPLICATION_PDF_VALUE)
	public  ResponseEntity<?> getPDF(@PathVariable(value="bookingId") long bookingId,@PathVariable(value="userId")long userId){
		logger.info("Entering into pdf list");
		try {
			PdfCreationImpl pdfImpl = new PdfCreationImpl();
		ByteArrayInputStream bis	=pdfImpl.pdfData(bookingId,userId);
		int emty =bis.available();
		/*HttpHeaders headers=new HttpHeaders();
		 headers.add("Content-Disposition", "attachment; filename=ttsplBL2.pdf");
       
   return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
		
		 return new ResponseEntity<InputStreamResource>(new InputStreamResource(bis),HttpStatus.OK);*/
         
		//ClassPathResource pdfFile = new ClassPathResource("ttsplBL2.pdf");
		HttpHeaders headers = new HttpHeaders();
		  
		String imageBase64	= java.util.Base64.getEncoder().encodeToString(IOUtils.toByteArray(bis));
		 headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		 if(emty!=0 && bis!=null){
		 ResponseEntity<Object> responseEntity = new ResponseEntity<>(imageBase64, headers, HttpStatus.OK);
		    return responseEntity;
		    }else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	
		
		
		
	
		
		
	}
	
	/*@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<?> download() throws IOException {
	    String filePath = "D:\\project\cpwttsplBL2.pdf";
	    java.io.InputStream inputStream = new FileInputStream(new File(filePath));
	    InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentLength(Files.size(Paths.get(filePath)));
	    return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
	}*/

}
