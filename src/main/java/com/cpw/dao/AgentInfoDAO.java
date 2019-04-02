package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.AgentDetail;

public interface AgentInfoDAO {
	public void setDataSource(DataSource ds);
	
	public List<AgentDetail> agentList(String port);


}
