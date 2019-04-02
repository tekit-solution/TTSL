package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.AgentInfoDAOImpl;
import com.cpw.jdbc.model.AgentDetail;
import com.cpw.model.AgentInfoResponse;

public class AgentInfoImpl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private final AgentInfoDAOImpl agentIngoDAO = (AgentInfoDAOImpl) context.getBean("agentInfoDaoImpl");
	
	public List<AgentInfoResponse> agentList(String port) {
		logger.info("Entering into Agent List");
		final List<AgentDetail> agentList = agentIngoDAO.agentList(port);
		return map(agentList);

	}

	private List<AgentInfoResponse> map(List<AgentDetail> agentList) {
		List<AgentInfoResponse> agentResponseList = Collections.emptyList();
		if (agentList != null && !agentList.isEmpty()) {
			agentResponseList = new ArrayList<AgentInfoResponse>();
			for (AgentDetail agentDetail : agentList) {
				AgentInfoResponse trackingResponse = map(agentDetail);
				agentResponseList.add(trackingResponse);
			}
			agentList.clear();
		}
		return agentResponseList;

	}

	private AgentInfoResponse map(AgentDetail agentDetail) {
		AgentInfoResponse agentResponse = new AgentInfoResponse();
		if (agentDetail != null) {
			agentResponse.setPort(agentDetail.getPort());
			agentResponse.setAgentAddress(agentDetail.getAgentAddress());
			agentResponse.setLclDetail(agentDetail.getLclDetail());
			agentResponse.setLalDetail1(agentDetail.getLalDetail1());
			agentResponse.setHbl(agentDetail.getHbl());
			agentResponse.setNotes(agentDetail.getNotes());
			



		}
		return agentResponse;

}
}
