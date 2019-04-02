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

import com.cpw.model.AddAccountRequest;
import com.cpw.model.AddAccountResponse;
import com.cpw.services.AddAccountImpl;

@RestController
public class AddAccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "accounts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends AddAccountResponse>> accounts(@PathVariable("id") long id) {
		logger.info("Entering into Account list");
		try {
			logger.debug("Account Id:" +id);
			AddAccountImpl accountImpl = new AddAccountImpl();
			List<AddAccountResponse> addAccountResponse = accountImpl.accountList(id);
			if (addAccountResponse != null && !addAccountResponse.isEmpty()) {
				return new ResponseEntity<List<? extends AddAccountResponse>>(addAccountResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<? extends AddAccountResponse>>(addAccountResponse, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends AddAccountResponse>>(Collections.emptyList(),
					HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/removeAccount/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> removeAccount(@RequestHeader("userId") String userId,@PathVariable("id") long id) {
		logger.info("Entering into removeAccount");
		try {
			logger.debug("Delete Account corresponding to Account Id:" +id);
			AddAccountImpl accountImpl = new AddAccountImpl();
			int response = accountImpl.removeAccount(id,userId);
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

	@RequestMapping(value = "/addAccount", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> upsertAccount(@RequestHeader("userId") String userId,@RequestBody AddAccountRequest request) {
		System.out.println(request);
		System.out.println(userId);
		logger.info("Entering into addAccount");
		try {
			if (request.getAccountName().isEmpty()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			logger.debug("Accountname : " + request.getAccountName());
			AddAccountImpl accountImpl = new AddAccountImpl();
			int response = accountImpl.upsertAccount(request,userId);
			System.out.println("response is" +response);
			if (response > 0) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
