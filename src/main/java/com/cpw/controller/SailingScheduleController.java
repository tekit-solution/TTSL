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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.model.ScheduleSearchRequest;
import com.cpw.model.ScheduleSearchResponse;
import com.cpw.services.SailingScheduleSearchImpl;

/**
 * @author Unknown
 *
 */
@RestController
public class SailingScheduleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/sailingSchedule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends ScheduleSearchResponse>> sailingScheduleSearch(
			@RequestBody ScheduleSearchRequest request,
			@RequestParam(value = "FromETD", required = false) String fromETD,
			@RequestParam(value = "ToETD", required = false) String toETD,
			@RequestParam(value = "FromETA", required = false) String fromETA,
			@RequestParam(value = "ToETA", required = false) String toETA) {
		logger.info("Entering into SailingScheduleController");
		List<ScheduleSearchResponse> scheduleSearchResponseList = Collections.emptyList();
		try {
			if (request == null) {
				return new ResponseEntity<List<? extends ScheduleSearchResponse>>(scheduleSearchResponseList,
						HttpStatus.NOT_FOUND);
			} else {
				/*request.setFromETA(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime
						.ofInstant(fromETA.toInstant(), ZoneId.systemDefault())));
				request.setToETA(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime
						.ofInstant(toETA.toInstant(), ZoneId.systemDefault())));
				request.setFromETD(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime
						.ofInstant(fromETD.toInstant(), ZoneId.systemDefault())));
				request.setToETD(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime
						.ofInstant(toETD.toInstant(), ZoneId.systemDefault())));*/
				request.setFromETA(fromETA);
				request.setToETA(toETA);
				request.setFromETD(fromETD);
				request.setToETD(toETD);

				logger.debug("PodId: " + request.getPodId());
				SailingScheduleSearchImpl sailingScheduleSearchImpl = new SailingScheduleSearchImpl();
				scheduleSearchResponseList = sailingScheduleSearchImpl.sailingSearchSchedule(request);
				if (!scheduleSearchResponseList.isEmpty()) {
					return new ResponseEntity<List<? extends ScheduleSearchResponse>>(scheduleSearchResponseList,
							HttpStatus.OK);
				}
				return new ResponseEntity<List<? extends ScheduleSearchResponse>>(scheduleSearchResponseList,
						HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends ScheduleSearchResponse>>(scheduleSearchResponseList,
					HttpStatus.NOT_FOUND);
		}
	}

}
