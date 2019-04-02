package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.Lead;

public interface LeadDAO {

	public void setDataSource(DataSource ds);

	public int upsertLead(Lead lead,String userId);

	public List<Lead> leadList(long leadId);
	
	public int removeLead(long leadId,String userId);
}
