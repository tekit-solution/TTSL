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

import com.cpw.model.Sail_ScheduleResponse;
import com.cpw.model.Sail_scheduleRequest;
import com.cpw.services.Sale_scheduleImpl;

@RestController
public class Sale_scheduleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/sailSchedualDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends Sail_ScheduleResponse>> sailingSchedule(
			@RequestBody Sail_scheduleRequest request,
			@RequestParam(value = "FromETD", required = false) long fromETD,
			@RequestParam(value = "ToETD", required = false) long toETD,
			@RequestParam(value = "FromETA", required = false) long fromETA,
			@RequestParam(value = "ToETA", required = false) long toETA) {
		logger.info("Entering into SailingScheduleController");
		logger.debug("SailingSceduleRequest" +request);
		List<Sail_ScheduleResponse> scheduleResponseList = Collections.emptyList();
		try {
			if (request == null) {
				return new ResponseEntity<List<? extends Sail_ScheduleResponse>>(scheduleResponseList,
						HttpStatus.NOT_FOUND);
			} else {
				request.setFrom_Eta(fromETA);
				request.setTo_Eta(toETA);
				request.setFrom_Etd(fromETD);
				request.setTo_Etd(toETD);
				

				logger.debug("port_Of_Loading" +request.getPort_Of_Loading());
				Sale_scheduleImpl sailingScheduleImpl = new Sale_scheduleImpl();
				scheduleResponseList = sailingScheduleImpl.insertSailingSchedule(request);
				System.out.println("REsponseList" +scheduleResponseList);
				if (!scheduleResponseList.isEmpty()) {
					return new ResponseEntity<List<? extends Sail_ScheduleResponse>>(scheduleResponseList,
							HttpStatus.OK);
				}
				return new ResponseEntity<List<? extends Sail_ScheduleResponse>>(scheduleResponseList,
						HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends Sail_ScheduleResponse>>(scheduleResponseList,
					HttpStatus.NOT_FOUND);
		}
	}
	
	

}
