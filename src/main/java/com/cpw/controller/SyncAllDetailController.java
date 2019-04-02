package com.cpw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.model.AddAccountRequest;
import com.cpw.model.ContactData;
import com.cpw.model.LeadData;
import com.cpw.model.SyncAllDetailRequest;
import com.cpw.services.SyncAllDeatilImpl;

@RestController
public class SyncAllDetailController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value= "/addDetail", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.ALL_VALUE)
	public ResponseEntity<?> insertDetail(@RequestHeader("userId")String userId,@RequestBody SyncAllDetailRequest request)
	{
		
		try {
			System.out.println(request);
			logger.info("Enter into controller");
		
		
		List<AddAccountRequest> accountList	=request.getAccount();
		for(AddAccountRequest account :accountList){
	
		if(account.getAccountName()==null){
			logger.debug("AccountName can't be null" +account.getAccountName());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
		}
			
		}
		List<ContactData> contactList=request.getContact();
		for(ContactData contact:contactList){
			if (contact.getLastName().isEmpty()) {
				logger.debug("contactLastName can't be empty"+contact.getLastName().isEmpty());
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
		List<LeadData> leadList=request.getLead();
		for(LeadData lead:leadList){
			if (lead.getCompany().isEmpty() || lead.getLastName().isEmpty()) {
				logger.debug("Data of lead can't be empty" +lead.getCompany().isEmpty()+","+lead.getLastName().isEmpty());
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
			
			SyncAllDeatilImpl syncImpl=new SyncAllDeatilImpl();
			
			String sync=syncImpl.syncAllDetail(request);
			
			System.out.println("sync value"+sync);
			switch(sync){
			case  "NotMatched":
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
				
			case  "Matched":
				return new ResponseEntity<Object>(HttpStatus.CREATED);
				
			case  "null":
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
				
			default: 
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
				
			}
			/*if(sync!=null  )
{
				return new ResponseEntity<Object>(HttpStatus.CREATED);	
			}else {
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);		
			}*/
			
			

		} catch (Exception e) {
			logger.error("no sync detail created"+e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		




	}

}
