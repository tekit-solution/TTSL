package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cpw.dao.mapper.LeadMapper;
import com.cpw.jdbc.model.Lead;
import com.cpw.services.EventImpl;
import com.cpw.services.ImageStoreImpl;
import com.cpw.services.NotificationImpl;

public class LeadDAOImpl implements LeadDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Lead> leadList(long leadId) {
		logger.info("Entering into leadList " + leadId);
		if(leadId==-1){
			CpwTemplete<Lead> cpwTemplete = new CpwTempleteImpl<Lead>();
			final String trackingSql = "Select LEAD_ID, LEAD_OWNER, COMPANY, FNAME, LNAME, TITLE, EMAIL, PHONE, FAX, MOBILE, WEBSITE, "
					+ "LEAD_SOURCE, LEAD_STATUS, INDUSTRY, NO_OF_EMP, ANNUAL_REVENUE, RATING, EMAIL_OUTPUT, SKYPE_ID, ADDRESS_STREET, ADDRESS_CITY, "
					+ "ADDRESS_STATE, ADDRESS_ZIPCODE, ADDRESS_COUNTRY, DESCRIPTION, CR_DATE, MD_DATE, CR_BY,SALUTATION,"
					+ " SECONDARY_EMAIL, TWITTER,MD_BY from LEAD";
			List<Lead> leadList = cpwTemplete.getRecordList(trackingSql, jdbcTemplateObject,
					new LeadMapper());
			return leadList;

		}	else {
			logger.info("Entering Into LeadList" +leadId);
			final String trackingSql = "Select LEAD_ID, LEAD_OWNER, COMPANY, FNAME, LNAME, TITLE, EMAIL, PHONE, FAX, MOBILE, WEBSITE, "
					+ "LEAD_SOURCE, LEAD_STATUS, INDUSTRY, NO_OF_EMP, ANNUAL_REVENUE, RATING, EMAIL_OUTPUT, SKYPE_ID, ADDRESS_STREET, ADDRESS_CITY, "
					+ "ADDRESS_STATE, ADDRESS_ZIPCODE, ADDRESS_COUNTRY, DESCRIPTION, CR_DATE, MD_DATE, CR_BY,SALUTATION,"
					+ " SECONDARY_EMAIL, TWITTER,MD_BY from  LEAD  WHERE LEAD_ID>?";
			List<Lead> leadList = jdbcTemplateObject.query(trackingSql, new Object[] {leadId},
					new LeadMapper());
			return leadList	;
		}
	}


	@Override
	public int upsertLead(Lead lead,String userId) {
		logger.info("Entering into lead DAO");
		CpwTemplete<Lead> cpwTemplete = new CpwTempleteImpl<Lead>();

		String q1 = "SELECT * FROM LEAD WHERE LEAD_ID=?";
		Lead l = null;
		try {
			logger.debug("Enter into select statement");
			System.out.println(lead.getLeadId());
			l = jdbcTemplateObject.queryForObject(q1, new Object[]{lead.getLeadId()},new LeadMapper());

			System.out.println(l.toString());



		} catch (EmptyResultDataAccessException e) {
			l = null;
		}
		try {
			int count = -1;

			if (l != null && l.getLeadId() == lead.getLeadId()) {
				logger.debug("UPDATE leadId values" + lead.getLeadId());
				String updateSql = "UPDATE LEAD SET LEAD_OWNER=?, COMPANY=?, FNAME=?, LNAME=?, TITLE=?,"
						+ " EMAIL=?, PHONE=?, FAX=?, MOBILE=?, WEBSITE=?, LEAD_SOURCE=?, LEAD_STATUS=?, INDUSTRY=?, NO_OF_EMP=?,"
						+ " ANNUAL_REVENUE=?, RATING=?, EMAIL_OUTPUT=?, SKYPE_ID=?, ADDRESS_STREET=?, ADDRESS_CITY=?,"
						+ " ADDRESS_STATE=?, ADDRESS_ZIPCODE=?, ADDRESS_COUNTRY=?, DESCRIPTION=?, MD_DATE=?,"
						+ " SALUTATION=?,SECONDARY_EMAIL=?,TWITTER=?,MD_BY=? WHERE LEAD_ID=?";
				count= jdbcTemplateObject.update(updateSql, 
						lead.getLeadOwner(),lead.getCompany(),lead.getFirstName(),
						lead.getLastName(),lead.getTitle(),lead.getEmail(),
						lead.getPhone(),lead.getFax(),lead.getMobile(),
						lead.getWebsite(),lead.getLeadSource(),lead.getLeadStatus(),
						lead.getIndustry(),lead.getNoOfEmployees(),lead.getAnnualRevenue(),
						lead.getRating(),lead.isEmailOptOut(),lead.getSkypeId(),
						lead.getAddressStreet(),lead.getAddressCity(),lead.getAddressState(),
						lead.getAddressZipCode(),lead.getAddressCounty(),lead.getDescription(),
						lead.getModifyDate(),lead.getSalutation(),
						lead.getSecondaryEmailId(),lead.getTwitter(),
						lead.getModifyBy(),lead.getLeadId());	}
			else {

				logger.info("INSERT LEAD values" );
				String insertSql = "INSERT INTO LEAD (LEAD_ID, LEAD_OWNER, COMPANY, FNAME, LNAME, TITLE,"
						+ " EMAIL, PHONE, FAX, MOBILE, WEBSITE, LEAD_SOURCE, LEAD_STATUS, INDUSTRY, NO_OF_EMP, ANNUAL_REVENUE,"
						+ " RATING, EMAIL_OUTPUT, SKYPE_ID, ADDRESS_STREET, ADDRESS_CITY, ADDRESS_STATE, ADDRESS_ZIPCODE,"
						+ " ADDRESS_COUNTRY, DESCRIPTION, CR_DATE, MD_DATE, CR_BY,SALUTATION,SECONDARY_EMAIL,TWITTER,MD_BY)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";
				Object[] values = new Object[32];

				values[0] = lead.getLeadId();
				values[1] = lead.getLeadOwner();
				values[2] = lead.getCompany();
				values[3] = lead.getFirstName();
				values[4] = lead.getLastName();
				values[5] = lead.getTitle();
				values[6] = lead.getEmail();
				values[7] = lead.getPhone();
				values[8] = lead.getFax();
				values[9] = lead.getMobile();
				values[10] = lead.getWebsite();
				values[11] = lead.getLeadSource();
				values[12] = lead.getLeadStatus();
				values[13] = lead.getIndustry();
				values[14] = lead.getNoOfEmployees();
				values[15] = lead.getAnnualRevenue();
				values[16] = lead.getRating();
				values[17] = lead.isEmailOptOut();
				values[18] = lead.getSkypeId();
				values[19] = lead.getAddressStreet();
				values[20] = lead.getAddressCity();
				values[21] = lead.getAddressState();
				values[22] = lead.getAddressZipCode();
				values[23] = lead.getAddressCounty();
				values[24] = lead.getDescription();
				values[25] = lead.getCreateDate();
				values[26] = lead.getModifyDate();
				values[27] = lead.getCreatedBy();
				values[28]=lead.getSalutation();
				values[29]=lead.getSecondaryEmailId();
				values[30]=lead.getTwitter();
				values[31]=lead.getModifyBy();

				logger.debug("Insert values" +values[0]);

				count = cpwTemplete.upsert(insertSql, values, jdbcTemplateObject);
				logger.debug("Record creation status: " + count);
			}
			logger.debug("Before Calling upsert");
			logger.debug("User Id" +userId);
			NotificationImpl notificationDAO=new NotificationImpl();
      notificationDAO.tokenList(userId,lead.getLeadId());
			return count;
		} catch (DataAccessException e) {
			// logger.error("Exception at time of creation" + e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeLead(long leadId,String userId) {
		logger.debug("Entering into remove Lead" +leadId);
		final String trackingSql = "DELETE FROM LEAD WHERE LEAD_ID = ?";
		try {
			
			int count=jdbcTemplateObject.update(trackingSql, leadId);
			ImageStoreImpl imageImpl=new ImageStoreImpl();
			imageImpl.removeImage(leadId);
			EventImpl eventImpl=new EventImpl();
			eventImpl.removeType(leadId);
			
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,leadId);
			return count;
		} catch (DataAccessException e) {
			logger.error("No Lead available in system coresponding to lead id: " + leadId);
			return 0;
		}

	}


}
