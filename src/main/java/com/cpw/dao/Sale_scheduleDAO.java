package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.Sail_schedule;
import com.cpw.model.Sail_scheduleRequest;

public interface Sale_scheduleDAO {

	public void setDataSource(DataSource dataSource);

	
	public List<Sail_schedule> sailingList(Sail_scheduleRequest sailingRequest);

}
