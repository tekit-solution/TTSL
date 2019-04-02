package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.SailingSchedule;
import com.cpw.model.SailingScheduleRequest;

public interface SailingScheduleDAO {
	
	public void setDataSource(DataSource dataSource);

	
	public List<SailingSchedule> sailingList(SailingScheduleRequest sailingRequest);

}
