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

import com.cpw.model.DealRequest;
import com.cpw.model.DealResponse;
import com.cpw.services.DealImpl;


@RestController
public class DealController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="addDeals" , method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.ALL_VALUE)
	public ResponseEntity<?> upsertDeal(@RequestHeader("userId")String userId ,@RequestBody DealRequest Request){

		logger.info("Entering into Deals");

		try {

			DealImpl dealImpl=new DealImpl();
			int response=dealImpl.upsertDeal(Request,userId);

			if(response>0)
			{
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@RequestMapping(value="deals/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<? extends DealResponse>> Deals(@PathVariable("id")long dealId)
	{
		logger.info("Entering into deal list");
		try {
			logger.debug("Retrieve Deal List corresponding to deal Id:" +dealId);
			DealImpl dealImpl=new DealImpl();
			List<DealResponse> dealResponse=dealImpl.dealList(dealId);
			if(dealResponse!= null && !dealResponse.isEmpty())
			{
				return new ResponseEntity<List<? extends DealResponse>>(dealResponse,HttpStatus.OK);
			}else {
				return new ResponseEntity<List<? extends DealResponse>>(dealResponse, HttpStatus.NO_CONTENT);

			}


		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;

	}
	@RequestMapping(value="removeDeal/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Object> removeList(@RequestHeader("userId")String userId,@PathVariable("id") long dealId)
	{
		logger.info("Entering into Remove");
		
		try {
			logger.debug("Delete Deal List corresponding to deal Id:" +dealId);
			DealImpl dealImpl=new DealImpl();
			int response=dealImpl.removeList(dealId,userId);
			if (response>0) {
				
				return new ResponseEntity<Object>(HttpStatus.OK);
				
			} else {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}



}
