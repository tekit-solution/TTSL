/**
 * 
 */
package com.cpw.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.model.ContactData;
import com.cpw.model.ContactDataResponse;
import com.cpw.services.ContactImpl;

/**
 * @author Unknown
 *
 */
@RestController
public class ContactController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/contact/{contactId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends ContactDataResponse>> contacts(@PathVariable("contactId") long contactId) {
		logger.info("Entering into contact list");
		try {
			logger.debug("Retrieve Contact List corresponding to Contact Id" +contactId);
			ContactImpl contactImpl = new ContactImpl();
			List<ContactDataResponse> contactDateResponse = contactImpl.contactList(contactId);
			if (contactDateResponse != null && !contactDateResponse.isEmpty()) {
				return new ResponseEntity<List<? extends ContactDataResponse>>(contactDateResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<? extends ContactDataResponse>>(contactDateResponse, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends ContactDataResponse>>(Collections.emptyList(),
					HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/removeContact/{contactId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> removeContact(@RequestHeader("userId")String userId,@PathVariable("contactId") long contactId) {
		logger.info("Entering into removeContact");
		try {
			logger.debug("Delete Contact List corresponding to Contact Id" +contactId);
			ContactImpl contactImpl = new ContactImpl();
			int response = contactImpl.removeContact(contactId,userId);
			if (response > 0) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertContact(@RequestHeader("userId")String userId,@RequestBody ContactData request) {

		logger.info("Entering into addContact");
		try {
			if (request.getLastName().isEmpty()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			logger.debug("FirstName : " + request.getFirstName());
			ContactImpl contactImpl = new ContactImpl();
			int response = contactImpl.upsertContact(request,userId);
			if (response > 0) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

}
