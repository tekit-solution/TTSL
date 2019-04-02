package com.cpw.dao;

import javax.sql.DataSource;

import com.cpw.model.SyncAllDetailRequest;

public interface SyncAllDetailDao {
	public void setDataSource(DataSource ds);
	
	public String insertAccount(SyncAllDetailRequest syncAll);
	
	public String insertLead(SyncAllDetailRequest syncAll);
	
	public String insertContact(SyncAllDetailRequest syncAll);
	
	public String insertDeal(SyncAllDetailRequest syncAll);
	
	public String insertTask(SyncAllDetailRequest syncAll);
	
	public String insertEvent(SyncAllDetailRequest syncAll);
	
	public String insertCall(SyncAllDetailRequest syncAll);
	
	public String insertCustChallenge(SyncAllDetailRequest syncAll);
	
	public String insertSalesBudget(SyncAllDetailRequest syncAll);
	
	public String insertImage(SyncAllDetailRequest syncAll);
	
	

	
	
	
	

}
