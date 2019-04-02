package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.SailingSchedulDAOImpl;
import com.cpw.jdbc.model.SailingSchedule;
import com.cpw.model.SailingScheduleRequest;
import com.cpw.model.SailinhScheduleResponse;

public class SailingScheduleImpl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext context  = new ClassPathXmlApplicationContext("Beans.xml");
	SailingSchedulDAOImpl sailingScheduleDAOImpl = (SailingSchedulDAOImpl) context.getBean("SailingSchedulDaoImpl");

	public List<SailinhScheduleResponse> insertSailingSchedule(SailingScheduleRequest sailingRequest){
		logger.info("Enter into SailingSchedule ");
		List<SailingSchedule> sailRequest=sailingScheduleDAOImpl.sailingList(sailingRequest);
         System.out.println("Sailing Schedule" +sailingRequest);

		return map(sailRequest);
	}
	private List<SailinhScheduleResponse> map(List<SailingSchedule> scheduleList) {
		List<SailinhScheduleResponse> scheduleResponseList = Collections.emptyList();
		if (scheduleList != null && !scheduleList.isEmpty()) {
			scheduleResponseList = new ArrayList<SailinhScheduleResponse>();
			for (SailingSchedule sailSchedule : scheduleList) {
				SailinhScheduleResponse scheduleResponse = map(sailSchedule);
				scheduleResponseList.add(scheduleResponse);
			}
			scheduleList.clear();
		}
		return scheduleResponseList;

	}

	private SailinhScheduleResponse map(SailingSchedule saillingSchedule) {
		SailinhScheduleResponse scheduleResponse = new SailinhScheduleResponse();
		if (saillingSchedule != null) {
			scheduleResponse.setOrigin_Location(saillingSchedule.getOrigin_Location());
			scheduleResponse.setPort_Of_Loading(saillingSchedule.getPort_Of_Loading());
			scheduleResponse.setTerminal(saillingSchedule.getTerminal());
			scheduleResponse.setPort_Of_Discharge(saillingSchedule.getPort_Of_Discharge());
			scheduleResponse.setSector(saillingSchedule.getSector());
			scheduleResponse.setService(saillingSchedule.getService());
			scheduleResponse.setVessel(saillingSchedule.getVessel());
			scheduleResponse.setVoy(saillingSchedule.getVoy());
			scheduleResponse.setFrom_Eta(saillingSchedule.getFrom_Eta());
			scheduleResponse.setFrom_Etd(saillingSchedule.getFrom_Etd());
			scheduleResponse.setCfs_Cut_off(saillingSchedule.getCfs_Cut_off());
			scheduleResponse.setTime(saillingSchedule.getTime());
			scheduleResponse.setLast_Stuffing(saillingSchedule.getLast_Stuffing());
			scheduleResponse.setDay(saillingSchedule.getDay());
			scheduleResponse.setTransit(saillingSchedule.getTransit());
			scheduleResponse.setRemark(saillingSchedule.getRemark());
			scheduleResponse.setM_Vessel(saillingSchedule.getM_Vessel());
			scheduleResponse.setVoyy(saillingSchedule.getVoyy());
			scheduleResponse.setTo_Eta(saillingSchedule.getTo_Eta());
			scheduleResponse.setTo_Etd(saillingSchedule.getTo_Etd());
			scheduleResponse.setImportExport(saillingSchedule.getImportExport());
		}
		return scheduleResponse;

	}


}
