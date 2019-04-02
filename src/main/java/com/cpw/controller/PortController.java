/**
 * 
 */
package com.cpw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.services.PortImpl;

/**
 * @author Unknown
 *
 */
@RestController
public class PortController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/ports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> PortData() {
		logger.info("Entering into PortData");
		try {
			PortImpl portImpl = new PortImpl();
			String portResponses = portImpl.getAllPort();
			if (portResponses != null && !portResponses.isEmpty()) {
				return new ResponseEntity<Object>(portResponses, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(portResponses, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
