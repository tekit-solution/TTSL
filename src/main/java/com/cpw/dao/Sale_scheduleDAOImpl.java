package com.cpw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.Sail_schedule;
import com.cpw.model.Sail_scheduleRequest;

public class Sale_scheduleDAOImpl implements Sale_scheduleDAO{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		
	}

	@Override
	public List<Sail_schedule> sailingList(Sail_scheduleRequest sailingRequest) {
		logger.info("Entering into ScheduleDao");
		StringBuilder sb = new StringBuilder();
		List<String> value = new ArrayList<>();
		sb.append("SELECT ORIGIN_LOCATION,PORT_OF_LOADING,TERMINAL,PORT_OF_DISCHARGE,SECTOR,"
				+ "SERVICE,VESSEL,VOY,FROM_ETA,FROM_ETD,CFS_CUT_OFF,TIME,LAST_STUFFING,DAY,"
				+ "TRANSIT,REMARK,M_VESSEL,VOYY,TO_ETA,TO_ETD,IMPORT_OR_EXPORT FROM SAIL_SCHEDULE_DETAIL WHERE");
		if(sailingRequest.getPort_Of_Loading()!=null && sailingRequest.getPort_Of_Discharge()!=null){
		sb.append(" PORT_OF_LOADING =? ");
		value.add(sailingRequest.getPort_Of_Loading());
		sb.append(" AND PORT_OF_DISCHARGE=? ");
		value.add(sailingRequest.getPort_Of_Discharge());
		
		}
		if(sailingRequest.getFrom_Etd()!=0 ){    
			sb.append(" AND FROM_ETD >=? ");
			value.add(String.valueOf(sailingRequest.getFrom_Etd()));
		}
		if(sailingRequest.getFrom_Eta()!=0){
			sb.append(" AND FROM_ETA<=? ");
			value.add(String.valueOf(sailingRequest.getFrom_Eta()));
		}
		/*if(sailingRequest.getTo_Eta()!=0){
			sb.append(" AND TO_ETA=? ");
			value.add(String.valueOf(sailingRequest.getTo_Eta()));
		}
		
		if( sailingRequest.getTo_Etd()!=0){
			sb.append(" AND TO_ETD=? ");
			value.add(String.valueOf(sailingRequest.getTo_Etd()));

		}*/
		System.out.println("string" +sb.toString());
		try {
			
		List<Sail_schedule> sailingList= jdbcTemplateObject.query(sb.toString(),value.toArray(), new RowMapper<Sail_schedule>() {

				@Override
				public Sail_schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
					Sail_schedule sailingSchedule =new Sail_schedule();
					sailingSchedule.setOrigin_Location(rs.getString("ORIGIN_LOCATION"));
					sailingSchedule.setPort_Of_Loading(rs.getString("PORT_OF_LOADING"));
					sailingSchedule.setTerminal(rs.getString("TERMINAL"));
					sailingSchedule.setPort_Of_Discharge(rs.getString("PORT_OF_DISCHARGE"));
					sailingSchedule.setSector(rs.getString("SECTOR"));
					sailingSchedule.setService(rs.getString("SERVICE"));
					sailingSchedule.setVessel(rs.getString("VESSEL"));
					sailingSchedule.setVoy(rs.getString("VOY"));
					sailingSchedule.setFrom_Eta(rs.getLong("FROM_ETA"));
					sailingSchedule.setFrom_Etd(rs.getLong("FROM_ETD"));
					sailingSchedule.setCfs_Cut_off(rs.getString("CFS_CUT_OFF"));
					sailingSchedule.setTime(rs.getString("TIME"));
					sailingSchedule.setLast_Stuffing(rs.getString("LAST_STUFFING"));
					sailingSchedule.setDay(rs.getString("DAY"));
					sailingSchedule.setTransit(rs.getString("TRANSIT"));
					sailingSchedule.setRemark(rs.getString("REMARK"));
					sailingSchedule.setM_Vessel(rs.getString("M_VESSEL"));
					sailingSchedule.setVoyy(rs.getString("VOYY"));
					sailingSchedule.setTo_Eta(rs.getLong("TO_ETA"));
					sailingSchedule.setTo_Etd(rs.getLong("TO_ETD"));
					sailingSchedule.setImportExport(rs.getString("IMPORT_OR_EXPORT"));
					return sailingSchedule;
				}
			});
		System.out.println("SailList" +sailingList);
			return sailingList;
		} catch (Exception e) {
            logger.error("No Sailing Schedule detail in system" +e);
			return null;
		}
	
	}
	
	
}
