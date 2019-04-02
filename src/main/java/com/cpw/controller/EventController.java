package com.cpw.controller;

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

import com.cpw.model.EventRequest;
import com.cpw.model.EventResponse;
import com.cpw.services.EventImpl;

@RestController
public class EventController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "addEvent", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)

	public  ResponseEntity<EventRequest> upsertEvent(@RequestHeader("userId")String userId,@RequestBody EventRequest  request) {
	

		logger.info("Entering into Event");
		try {
			logger.debug("Event Request:"+request);
			EventImpl eventImpl = new EventImpl();

			int response = eventImpl.upsertEvent(request,userId);
			
			if (response>0) {
				return new ResponseEntity<EventRequest>(HttpStatus.CREATED);
			} else { 
				return new ResponseEntity<EventRequest>(HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Data is not added " +e);
			return new ResponseEntity<EventRequest>(HttpStatus.BAD_REQUEST);
			
			
		}
	
	}


	@RequestMapping(value = "events/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends EventResponse>> Event(@PathVariable("id") long eventId) {
		logger.info("Entering into Event list");
		try {
			logger.debug("Retrieve Event List corresponding to Event Id:" +eventId);
			EventImpl eventImpl = new EventImpl();
		List<EventResponse> response=eventImpl.eventList(eventId);
		if (response != null && !response.isEmpty()) {
			return new ResponseEntity<List<? extends EventResponse>>(response,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<? extends EventResponse>>(response,HttpStatus.NO_CONTENT);
		}
	


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "removeEvent/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> removeEvent(@RequestHeader("userId")String userId,@PathVariable("id") long eventId) {
		logger.info("Entering into remove Event");
		try {
			logger.debug("Delete Event List corresponding to Event Id:" +eventId);
			EventImpl eventImpl = new EventImpl();
			int response = eventImpl.removeDeatil(eventId,userId);
		
			if (response > 0) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			
		}

	}



}
