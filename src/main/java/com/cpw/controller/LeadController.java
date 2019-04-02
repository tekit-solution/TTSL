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

import com.cpw.model.LeadData;
import com.cpw.model.LeadDataResponse;
import com.cpw.services.LeadImpl;

/**
 * @author Unknown
 *
 */
@RestController
public class LeadController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/leads/{leadId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<? extends LeadDataResponse>> leads(@PathVariable("leadId") long leadId) {
		logger.info("Entering into lead list");
		try {
			logger.debug("Retrieve Lead List corresponding to Lead Id" +leadId);
			LeadImpl leadImpl = new LeadImpl();
			List<LeadDataResponse> leadDateResponse = leadImpl.leadList(leadId);
			if (leadDateResponse == null || leadDateResponse.isEmpty()) {
				return new ResponseEntity<List<? extends LeadDataResponse>>(leadDateResponse, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<? extends LeadDataResponse>>(leadDateResponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends LeadDataResponse>>(Collections.emptyList(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/removeLead/{leadId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> removeLead(@RequestHeader("userId")String userId,@PathVariable("leadId") long leadId) {
		logger.info("Entering into removeLead");
		try {
			logger.debug("Delete Lead List corresponding to Lead Id" +leadId);
			LeadImpl leadImpl = new LeadImpl();
			int response = leadImpl.removeLead(leadId,userId);
			if (response > 0) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/addLead", method = RequestMethod.PUT, produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> upsertLead(@RequestHeader("userId")String userId,@RequestBody LeadData request) {
 
		logger.info("Entering into addLead");
		try {
			logger.debug("Lead Request data :" +request);
			if (request.getCompany().isEmpty() || request.getLastName().isEmpty()) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			logger.debug("Company : " + request.getCompany());
			LeadImpl leadImpl = new LeadImpl();
			int response = leadImpl.upsertLead(request,userId);
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
