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

import com.cpw.jdbc.model.SailingSchedule;
import com.cpw.model.SailingScheduleRequest;

public class SailingSchedulDAOImpl implements SailingScheduleDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		
	}
	@Override
	public List<SailingSchedule> sailingList(SailingScheduleRequest sailingRequest) {
		logger.info("Entering into ScheduleDao");
		StringBuilder sb = new StringBuilder();
		List<String> value = new ArrayList<>();
		sb.append("SELECT ORIGIN_LOCATION,PORT_OF_LOADING,TERMINAL,PORT_OF_DISCHARGE,SECTOR,"
				+ "SERVICE,VESSEL,VOY,FROM_ETA,FROM_ETD,CFS_CUT_OFF,TIME,LAST_STUFFING,DAY,"
				+ "TRANSIT,REMARK,M_VESSEL,VOYY,TO_ETA,TO_ETD,IMPORT_OR_EXPORT FROM SAILING_SCHEDULE WHERE");
		if(sailingRequest.getPort_Of_Loading()!=null && sailingRequest.getPort_Of_Discharge()!=null){
		sb.append(" PORT_OF_LOADING =? ");
		value.add(sailingRequest.getPort_Of_Loading());
		sb.append(" AND PORT_OF_DISCHARGE=? ");
		value.add(sailingRequest.getPort_Of_Discharge());
		
		}
		if(sailingRequest.getFrom_Eta()!=null && !sailingRequest.getFrom_Eta().isEmpty() &&  sailingRequest.getTo_Eta()!=null && !sailingRequest.getTo_Eta().isEmpty() && sailingRequest.getFrom_Etd()!=null && !sailingRequest.getFrom_Etd().isEmpty()&& sailingRequest.getTo_Etd()!=null && !sailingRequest.getTo_Etd().isEmpty()){
			sb.append(" AND FROM_ETA =? ");
			value.add(sailingRequest.getFrom_Eta());
			sb.append(" AND TO_ETA=? ");
			value.add(sailingRequest.getTo_Eta());
			sb.append(" AND FROM_ETD=? ");
			value.add(sailingRequest.getFrom_Etd());
			sb.append(" AND TO_ETD=? ");
			value.add(sailingRequest.getTo_Etd());

		}
		System.out.println("string" +sb.toString());
		try {
			
		List<SailingSchedule> sailingList= jdbcTemplateObject.query(sb.toString(),value.toArray(), new RowMapper<SailingSchedule>() {

				@Override
				public SailingSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
					SailingSchedule sailingSchedule =new SailingSchedule();
					sailingSchedule.setOrigin_Location(rs.getString("ORIGIN_LOCATION"));
					sailingSchedule.setPort_Of_Loading(rs.getString("PORT_OF_LOADING"));
					sailingSchedule.setTerminal(rs.getString("TERMINAL"));
					sailingSchedule.setPort_Of_Discharge(rs.getString("PORT_OF_DISCHARGE"));
					sailingSchedule.setSector(rs.getString("SECTOR"));
					sailingSchedule.setService(rs.getString("SERVICE"));
					sailingSchedule.setVessel(rs.getString("VESSEL"));
					sailingSchedule.setVoy(rs.getString("VOY"));
					sailingSchedule.setFrom_Eta(rs.getString("FROM_ETA"));
					sailingSchedule.setFrom_Etd(rs.getString("FROM_ETD"));
					sailingSchedule.setCfs_Cut_off(rs.getString("CFS_CUT_OFF"));
					sailingSchedule.setTime(rs.getString("TIME"));
					sailingSchedule.setLast_Stuffing(rs.getString("LAST_STUFFING"));
					sailingSchedule.setDay(rs.getString("DAY"));
					sailingSchedule.setTransit(rs.getString("TRANSIT"));
					sailingSchedule.setRemark(rs.getString("REMARK"));
					sailingSchedule.setM_Vessel(rs.getString("M_VESSEL"));
					sailingSchedule.setVoyy(rs.getString("VOYY"));
					sailingSchedule.setTo_Eta(rs.getString("TO_ETA"));
					sailingSchedule.setTo_Etd(rs.getString("TO_ETD"));
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
