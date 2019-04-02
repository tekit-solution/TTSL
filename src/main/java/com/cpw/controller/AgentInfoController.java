package com.cpw.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.model.AgentInfoResponse;
import com.cpw.services.AgentInfoImpl;

@RestController
public class AgentInfoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "agent/{port}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<? extends AgentInfoResponse>> agents(@PathVariable("port")String port) {
		logger.info("Entering into agentController list");
		try {
			logger.debug("Port:" +port);
			AgentInfoImpl agentImpl = new AgentInfoImpl();
			List<AgentInfoResponse> agentResponse = agentImpl.agentList(port);
			if (agentResponse != null && !agentResponse.isEmpty()) {
				return new ResponseEntity<List<? extends AgentInfoResponse>>(agentResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<? extends AgentInfoResponse>>(agentResponse, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<? extends AgentInfoResponse>>(Collections.emptyList(),
					HttpStatus.NO_CONTENT);
		}
}

}
