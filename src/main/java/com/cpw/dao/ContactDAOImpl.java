package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cpw.dao.mapper.ContactMapper;
import com.cpw.jdbc.model.Contact;
import com.cpw.services.EventImpl;
import com.cpw.services.ImageStoreImpl;
import com.cpw.services.NotificationImpl;

public class ContactDAOImpl implements ContactDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Contact> contactList(long contactId) {
		logger.debug("Entering into contactList " + contactId);
		CpwTemplete<Contact> cpwTemplete = new CpwTempleteImpl<Contact>();
		if(contactId==-1){
			final String trackingSql ="Select CONTACT_ID, CONTACT_OWNER, LEAD_SOURCE, FIRST_NAME, LAST_NAME, ACCOUNT_NAME, EMAIL,TITLE,DEPARTMENT, PHONE,HOME_PHONE,OTHER_PHONE, FAX, MOBILE, DATE_OF_BIRTH,ASSISTANT,ASST_PHONE,REPORTS_TO, "
					+ " EMAIL_OPT_OUT,CREATED_BY,CREATED_DATE, MODIFY_BY,MODIFY_DATE,SKYPE_ID,SECONDARY_EMAIL,LAST_ACTIVITY_TIME,TWITTER, "
					+ "MAILING_ADDRESS_STREET,MAILING_ADDRESS_CITY,MAILING_ADDRESS_STATE, MAILING_ADDRESS_ZIP, MAILING_ADDRESS_COUNTRY,"
					+ "OTHER_ADDRESS_STREET,OTHER_ADDRESS_CITY,OTHER_ADDRESS_STATE, OTHER_ADDRESS_ZIP, OTHER_ADDRESS_COUNTRY,"
					+ " DESCRIPTION,PARENT_ID,SALUTATION,ACCOUNT_ID from CONTACT";
			List<Contact> contactList = cpwTemplete.getRecordList(trackingSql, jdbcTemplateObject,
					new ContactMapper());
			return contactList;

		}else {
			logger.debug("Entering into contactList corresponding to ContactId" +contactId );
			final String trackingSql = "Select CONTACT_ID, CONTACT_OWNER, LEAD_SOURCE, FIRST_NAME, LAST_NAME, ACCOUNT_NAME, EMAIL,TITLE,DEPARTMENT, PHONE,HOME_PHONE,OTHER_PHONE, FAX, MOBILE, DATE_OF_BIRTH,ASSISTANT,ASST_PHONE,REPORTS_TO, "
					+ " EMAIL_OPT_OUT,CREATED_BY,CREATED_DATE, MODIFY_BY,MODIFY_DATE,SKYPE_ID,SECONDARY_EMAIL,LAST_ACTIVITY_TIME,TWITTER, "
					+ "MAILING_ADDRESS_STREET,MAILING_ADDRESS_CITY,MAILING_ADDRESS_STATE, MAILING_ADDRESS_ZIP, MAILING_ADDRESS_COUNTRY,"
					+ "OTHER_ADDRESS_STREET,OTHER_ADDRESS_CITY,OTHER_ADDRESS_STATE, OTHER_ADDRESS_ZIP, OTHER_ADDRESS_COUNTRY,"
					+ " DESCRIPTION,PARENT_ID,SALUTATION,ACCOUNT_ID" + " from  CONTACT  WHERE CONTACT_ID>?";

			List<Contact> contactList = jdbcTemplateObject.query(trackingSql, new Object[] {contactId },
					new ContactMapper());
			return contactList;

		}
	}

	@Override
	public int upsertContact(Contact contactRequest,String userId) {
		logger.info("Entering into contact DAO");
		CpwTemplete<Contact> cpwTemplete = new CpwTempleteImpl<Contact>();
		String q1 = "select * from contact where CONTACT_ID=?";
		Contact contactInSystem = null;
		try {
			contactInSystem = jdbcTemplateObject.queryForObject(q1, new Object[] { contactRequest.getContactId() },
					new ContactMapper());
		} catch (EmptyResultDataAccessException e) {
			contactInSystem = null;
		}
		try {
			int count = -1;
			if (contactInSystem != null && contactInSystem.getContactId() == contactRequest.getContactId()) {
				logger.debug("UPDATE values" + contactRequest.getContactId());
				String updateSql = "UPDATE CONTACT SET CONTACT_OWNER=?,LEAD_SOURCE=?, FIRST_NAME=?, LAST_NAME=?,ACCOUNT_NAME=?,"
						+ " EMAIL=?,TITLE=?,DEPARTMENT=?, PHONE=?,HOME_PHONE=?,OTHER_PHONE=?,FAX=?, MOBILE=?, DATE_OF_BIRTH=?, ASSISTANT=?,ASST_PHONE=?,REPORTS_TO=?,"
						+ " EMAIL_OPT_OUT=?,CREATED_BY=?,CREATED_DATE=?,MODIFY_BY=?,MODIFY_DATE=?, SKYPE_ID=?,SECONDARY_EMAIL=?,LAST_ACTIVITY_TIME=?,TWITTER=?,"
						+ "MAILING_ADDRESS_STREET=?, MAILING_ADDRESS_CITY=?, MAILING_ADDRESS_STATE=?, MAILING_ADDRESS_ZIP=?, MAILING_ADDRESS_COUNTRY=?,"
						+ "OTHER_ADDRESS_STREET=?, OTHER_ADDRESS_CITY=?, OTHER_ADDRESS_STATE=?, OTHER_ADDRESS_ZIP=?, OTHER_ADDRESS_COUNTRY=?,"
						+ " DESCRIPTION=?,PARENT_ID=?,SALUTATION=?,ACCOUNT_ID=?" + " WHERE CONTACT_ID=?";


				count= jdbcTemplateObject.update(updateSql,contactRequest.getContactOwner(),
						contactRequest.getLeadSource(),contactRequest.getFirstName(),
						contactRequest.getLastName(),contactRequest.getAccountName(),
						contactRequest.getEmail(),contactRequest.getTitle(),contactRequest.getDepartment(),
						contactRequest.getPhone(),contactRequest.getHomePhone(),contactRequest.getOtherPhone(),
						contactRequest.getFax(),contactRequest.getMobile(),
						contactRequest.getDateOfBirth(),contactRequest.getAssistant(),
						contactRequest.getAsstPhone(),contactRequest.getReportsTo(),
						contactRequest.isEmailOptOut(),contactRequest.getCreatedBy(),
						contactRequest.getCreatedDate(),contactRequest.getModifyBy(),
						contactRequest.getModifyDate(),contactRequest.getSkypeId(),
						contactRequest.getSecondaryEmail(),contactRequest.getLastActivityTime(),
						contactRequest.getTwitter(),contactRequest.getMailingAddressStreet(),
						contactRequest.getMailingAddressCity(),contactRequest.getMailingAddressState(),
						contactRequest.getMailingAddressZip(),contactRequest.getMailingAddressCountry(),
						contactRequest.getOtherAddressStreet(),contactRequest.getOtherAddressCity(),
						contactRequest.getOtherAddressState(),contactRequest.getOtherAddressZip(),
						contactRequest.getOtherAddressCountry(),contactRequest.getDescription(),
						contactRequest.getParentId(),contactRequest.getSalutation(),
						contactRequest.getAccountId(),contactRequest.getContactId());
			}


			else {


				String insertSql = "INSERT INTO CONTACT (CONTACT_ID, CONTACT_OWNER,LEAD_SOURCE,FIRST_NAME, LAST_NAME,ACCOUNT_NAME,EMAIL, TITLE,"
						+ "DEPARTMENT,PHONE,HOME_PHONE,OTHER_PHONE,FAX,MOBILE,DATE_OF_BIRTH,ASSISTANT,ASST_PHONE,REPORTS_TO,"
						+ "EMAIL_OPT_OUT,CREATED_BY,CREATED_DATE,MODIFY_BY,MODIFY_DATE,SKYPE_ID,SECONDARY_EMAIL,LAST_ACTIVITY_TIME,TWITTER,"
						+ "MAILING_ADDRESS_STREET,MAILING_ADDRESS_CITY,MAILING_ADDRESS_STATE,MAILING_ADDRESS_ZIP,MAILING_ADDRESS_COUNTRY,"
						+ "OTHER_ADDRESS_STREET,OTHER_ADDRESS_CITY,OTHER_ADDRESS_STATE,OTHER_ADDRESS_ZIP,OTHER_ADDRESS_COUNTRY,"
						+ "DESCRIPTION,PARENT_ID,SALUTATION,ACCOUNT_ID)"
						+ "VALUES (?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?,?,?,?,?);";
				Object[] values = new Object[41];

				values[0] = contactRequest.getContactId();
				values[1] = contactRequest.getContactOwner();
				values[2] = contactRequest.getLeadSource();
				values[3] = contactRequest.getFirstName();
				values[4] = contactRequest.getLastName();
				values[5] = contactRequest.getAccountName();
				values[6] = contactRequest.getEmail();
				values[7] = contactRequest.getTitle();
				values[8] = contactRequest.getDepartment();
				values[9] = contactRequest.getPhone();
				values[10] = contactRequest.getHomePhone();
				values[11] = contactRequest.getOtherPhone();
				values[12] = contactRequest.getFax();
				values[13] = contactRequest.getMobile();
				values[14] = contactRequest.getDateOfBirth();
				values[15] = contactRequest.getAssistant();
				values[16] = contactRequest.getAsstPhone();
				values[17] = contactRequest.getReportsTo();
				values[18] = contactRequest.isEmailOptOut();
				values[19] = contactRequest.getCreatedBy();
				values[20] = contactRequest.getCreatedDate();
				values[21] = contactRequest.getModifyBy();
				values[22] = contactRequest.getModifyDate();
				values[23] = contactRequest.getSkypeId();
				values[24] = contactRequest.getSecondaryEmail();
				values[25] = contactRequest.getLastActivityTime();
				values[26] = contactRequest.getTwitter();
				values[27] = contactRequest.getMailingAddressStreet();
				values[28] = contactRequest.getMailingAddressCity();
				values[29] = contactRequest.getMailingAddressState();
				values[30] = contactRequest.getMailingAddressZip();
				values[31] = contactRequest.getMailingAddressCountry();
				values[32] = contactRequest.getOtherAddressStreet();
				values[33] = contactRequest.getOtherAddressCity();
				values[34] = contactRequest.getOtherAddressState();
				values[35] = contactRequest.getOtherAddressZip();
				values[36] = contactRequest.getOtherAddressCountry();
				values[37] = contactRequest.getDescription();
				values[38]=contactRequest.getParentId();
				values[39]=contactRequest.getSalutation();

				values[40]=contactRequest.getAccountId();
				logger.debug("Insert contact values" +values[0]);


				count = cpwTemplete.upsert(insertSql, values, jdbcTemplateObject);
				logger.debug("Record creation status: " + count);
			}
			logger.debug("Before Calling upsert");
			logger.debug("User Id" +userId);
			NotificationImpl notificationDAO=new NotificationImpl();
			notificationDAO.tokenList(userId,contactRequest.getContactId());
			return count;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int removeContact(long contactId,String userId) {
		logger.debug("Entering into remove Contact" +contactId);
		final String trackingSql = "DELETE FROM CONTACT WHERE CONTACT_ID = ?";
		try {
			int count=jdbcTemplateObject.update(trackingSql, contactId);
			ImageStoreImpl imageImpl=new ImageStoreImpl();
			imageImpl.removeImage(contactId);
			EventImpl eventImpl=new EventImpl();
			eventImpl.removeType(contactId);
			NotificationImpl notificationDAO=new NotificationImpl();
			notificationDAO.tokenList(userId,contactId);
			return count;
		} catch (DataAccessException e) {
			logger.error("No Contact available in system coresponding to contact id: " + contactId);
			return 0;
		}

	}

}
