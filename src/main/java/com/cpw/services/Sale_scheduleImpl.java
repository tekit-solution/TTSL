package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.Sale_scheduleDAOImpl;
import com.cpw.jdbc.model.Sail_schedule;
import com.cpw.model.Sail_ScheduleResponse;
import com.cpw.model.Sail_scheduleRequest;

public class Sale_scheduleImpl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext context  = new ClassPathXmlApplicationContext("Beans.xml");
	Sale_scheduleDAOImpl sailingScheduleDAOImpl = (Sale_scheduleDAOImpl) context.getBean("SailSchedulDaoImpl");

	public List<Sail_ScheduleResponse> insertSailingSchedule(Sail_scheduleRequest sailingRequest){
		logger.info("Enter into SailingSchedule ");
		List<Sail_schedule> sailRequest=sailingScheduleDAOImpl.sailingList(sailingRequest);
         System.out.println("Sailing Schedule" +sailingRequest);

		return map(sailRequest);
	}
	private List<Sail_ScheduleResponse> map(List<Sail_schedule> scheduleList) {
		List<Sail_ScheduleResponse> scheduleResponseList = Collections.emptyList();
		if (scheduleList != null && !scheduleList.isEmpty()) {
			scheduleResponseList = new ArrayList<Sail_ScheduleResponse>();
			for (Sail_schedule sailSchedule : scheduleList) {
				Sail_ScheduleResponse scheduleResponse = map(sailSchedule);
				scheduleResponseList.add(scheduleResponse);
			}
			scheduleList.clear();
		}
		return scheduleResponseList;

	}

	private Sail_ScheduleResponse map(Sail_schedule saillingSchedule) {
		Sail_ScheduleResponse scheduleResponse = new Sail_ScheduleResponse();
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
