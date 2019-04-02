package com.cpw.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.model.SailingScheduleRequest;
import com.cpw.model.SailinhScheduleResponse;
import com.cpw.services.SailingScheduleImpl;

@RestController
public class SailingSchedulController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/sailSchedual", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends SailinhScheduleResponse>> sailingSchedule(
			@RequestBody SailingScheduleRequest request,
			@RequestParam(value = "FromETD", required = false) String fromETD,
			@RequestParam(value = "ToETD", required = false) String toETD,
			@RequestParam(value = "FromETA", required = false) String fromETA,
			@RequestParam(value = "ToETA", required = false) String toETA) {
		logger.info("Entering into SailingScheduleController");
		logger.debug("SailingSceduleRequest" +request);
		List<SailinhScheduleResponse> scheduleResponseList = Collections.emptyList();
		try {
			if (request == null) {
				return new ResponseEntity<List<? extends SailinhScheduleResponse>>(scheduleResponseList,
						HttpStatus.NOT_FOUND);
			} else {
				request.setFrom_Eta(fromETA);
				request.setTo_Eta(toETA);
				request.setFrom_Etd(fromETD);
				request.setTo_Etd(toETD);
				

				logger.debug("port_Of_Loading" +request.getPort_Of_Loading());
				SailingScheduleImpl sailingScheduleImpl = new SailingScheduleImpl();
				scheduleResponseList = sailingScheduleImpl.insertSailingSchedule(request);
				System.out.println("REsponseList" +scheduleResponseList);
				if (!scheduleResponseList.isEmpty()) {
					return new ResponseEntity<List<? extends SailinhScheduleResponse>>(scheduleResponseList,
							HttpStatus.OK);
				}
				return new ResponseEntity<List<? extends SailinhScheduleResponse>>(scheduleResponseList,
						HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends SailinhScheduleResponse>>(scheduleResponseList,
					HttpStatus.NOT_FOUND);
		}
	}
	
	

}
