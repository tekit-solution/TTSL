package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.CustomerChallenge;

public interface CustomerChallengeDAO {

	public void setDataSource(DataSource ds);

	public int customerChallenge(CustomerChallenge customerChallenge,String userId);
	
	public List<CustomerChallenge> customerChallengeList(long id);
	
	public int removeCustomerChallenge(long id,String userId);

}
