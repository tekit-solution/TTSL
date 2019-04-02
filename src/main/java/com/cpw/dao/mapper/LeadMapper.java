package com.cpw.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.Lead;

public class LeadMapper implements RowMapper<Lead> {

	public Lead mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final Lead lead = new Lead();
		
		lead.setLeadId(resultSet.getLong("LEAD_ID"));
		lead.setLeadOwner(resultSet.getString("LEAD_OWNER"));
		lead.setCompany(resultSet.getString("COMPANY"));
		lead.setFirstName(resultSet.getString("FNAME"));
		lead.setLastName(resultSet.getString("LNAME"));
		lead.setTitle(resultSet.getString("TITLE"));
		lead.setEmail(resultSet.getString("EMAIL"));
		lead.setPhone(resultSet.getString("PHONE"));
		lead.setFax(resultSet.getString("FAX"));
		lead.setMobile(resultSet.getString("MOBILE"));
		lead.setWebsite(resultSet.getString("WEBSITE"));
		lead.setLeadSource(resultSet.getString("LEAD_SOURCE"));
		lead.setLeadStatus(resultSet.getString("LEAD_STATUS"));
		lead.setIndustry(resultSet.getString("INDUSTRY"));
		lead.setNoOfEmployees(resultSet.getString("NO_OF_EMP"));
		lead.setAnnualRevenue(resultSet.getString("ANNUAL_REVENUE"));
		lead.setRating(resultSet.getString("RATING"));
		lead.setEmailOptOut(resultSet.getBoolean("EMAIL_OUTPUT"));
		lead.setSkypeId(resultSet.getString("SKYPE_ID"));
		lead.setAddressStreet(resultSet.getString("ADDRESS_STREET"));
		lead.setAddressCity(resultSet.getString("ADDRESS_CITY"));
		lead.setAddressState(resultSet.getString("ADDRESS_STATE"));
		lead.setAddressZipCode(resultSet.getString("ADDRESS_ZIPCODE"));
		lead.setAddressCounty(resultSet.getString("ADDRESS_COUNTRY"));
		lead.setDescription(resultSet.getString("DESCRIPTION"));
		lead.setCreatedBy(resultSet.getString("CR_BY"));
		lead.setCreateDate(resultSet.getDate("CR_DATE"));
		lead.setModifyDate(resultSet.getDate("MD_DATE"));	
		lead.setSalutation(resultSet.getString("SALUTATION"));
		lead.setSecondaryEmailId(resultSet.getString("SECONDARY_EMAIL"));
		lead.setTwitter(resultSet.getString("TWITTER"));
		lead.setModifyBy(resultSet.getString("MD_BY"));
		//lead.setUploadedInputStream(resultSet.getString("IMAGE_PATH"));
		
		return lead;
	}

}
