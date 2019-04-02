package com.cpw.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.CustomerChallenge;

public class CustomerChallengeMapper implements RowMapper<CustomerChallenge> {

	public CustomerChallenge mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		final CustomerChallenge customerChallenge = new CustomerChallenge();

	customerChallenge.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
	customerChallenge.setContactId(resultSet.getLong("CONTACT_ID"));
	customerChallenge.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
	customerChallenge.setContactName(resultSet.getString("CONTACT_NAME"));
	customerChallenge.setLeadId(resultSet.getLong("LEAD_ID"));
	customerChallenge.setLeadName(resultSet.getString("LEAD_NAME"));
	customerChallenge.setLogDate(resultSet.getLong("LOG_DATE"));
	customerChallenge.setPriority(resultSet.getString("PRIORITY"));
	customerChallenge.setOrigin(resultSet.getString("ORIGIN"));
	customerChallenge.setReason(resultSet.getString("REASON"));
	customerChallenge.setDueDate(resultSet.getLong("DUE_DATE"));
	customerChallenge.setCloseddate(resultSet.getLong("CLOSED_DATE"));
	customerChallenge.setIncharge(resultSet.getString("INCHARGE"));
	customerChallenge.setCcMailId(resultSet.getString("CC_MAIL_ID"));
	customerChallenge.setStatus(resultSet.getString("STATUS"));
	customerChallenge.setSubject(resultSet.getString("SUBJECT"));
	customerChallenge.setNotes(resultSet.getString("NOTES"));
	customerChallenge.setDescription(resultSet.getString("DESCRIPTION"));
	customerChallenge.setInternalNote(resultSet.getString("INTERNAL_NOTE"));
	customerChallenge.setCustomerFeedback(resultSet.getString("CUSTOMER_FEEDBACK"));
	customerChallenge.setCreatedBy(resultSet.getString("CREATED_BY"));
	customerChallenge.setCreatedDate(resultSet.getLong("CREATED_DATE"));
	customerChallenge.setCreatedTime(resultSet.getLong("CREATED_TIME"));
	customerChallenge.setModifyBy(resultSet.getString("MODIFY_BY"));
	customerChallenge.setModifyDate(resultSet.getLong("MODIFY_DATE"));
	customerChallenge.setModifyTime(resultSet.getLong("MODIFY_TIME"));
	
			
		
		return customerChallenge;
	}

}
