package com.cpw.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cpw.jdbc.model.Participants;
import com.cpw.model.AddAccountRequest;
import com.cpw.model.CallRequest;
import com.cpw.model.ContactData;
import com.cpw.model.CustomerChallengeRequest;
import com.cpw.model.DealRequest;
import com.cpw.model.EventRequest;
import com.cpw.model.ImageStoreRequest;
import com.cpw.model.LeadData;
import com.cpw.model.SyncAllDetailRequest;
import com.cpw.model.TaskData;

public class SyncAllDetailDaoImpl implements SyncAllDetailDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	List<Participants> participant = new ArrayList<>();
	Participants partList;
	int[] countEvent;
	long eventId;
	int noOfParticipant;
	int partiSize;
	String matched="Matched";
	String notMatched="NOtMatched";


	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public String insertAccount(SyncAllDetailRequest syncAll) {

		logger.info("Entering into syncDao for Account");


		/*{
                 logger.debug("NO accountList in SyncDetail");
                 return null;

		}*/
		if(syncAll.getAccount()!=null && !syncAll.getAccount().isEmpty()){
			try {  


				String sql="INSERT INTO ACCOUNT (ACCOUNT_ID,ACCOUNT_OWNER,RATING,ACCOUNT_NAME,PHONE,"
						+ "ACCOUNT_SITE,FAX,PARENT_ACCOUNT,WEBSITE,ACCOUNT_NUMBER,TICKER_SYMBOL,ACCOUNT_TYPE,"
						+ "OWNERSHIP,INDUSTRY,EMPLOYEE,ANNUAL_REVENUE,SIC_CODE,BILLING_STREET,BILLING_CITY,"
						+ "BILLING_STATE,BILLING_CODE,BILLING_COUNTRY,ADDRESS_STREET,ADDRESS_CITY,"
						+ "ADDRESS_STATE,ADDRESS_CODE,ADDRESS_COUNTRY,DESCRIPTION,CREATED_BY"
						+ ",CREATED_TIME,MODIFY_BY,MODIFY_TIME,PARENT_ACCOUNT_ID)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

				List<AddAccountRequest> accountRequest=syncAll.getAccount();


				jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						AddAccountRequest accountList  = accountRequest.get(i);

						ps.setLong(1, accountList.getId());
						ps.setString(2,accountList.getAccountOwner());
						ps.setString(3, accountList.getRating()); 
						ps.setString(4, accountList.getAccountName());
						ps.setString(5, accountList.getPhone());
						ps.setString(6, accountList.getAccountSite());
						ps.setString(7,accountList.getFax());
						ps.setString(8, accountList.getParentAccount());
						ps.setString(9, accountList.getWebSite());
						ps.setInt(10, accountList.getAccountNumber());
						ps.setString(11, accountList.getTickerSymbol());
						ps.setString(12, accountList.getAccountType());
						ps.setString(13, accountList.getOwnerShip());
						ps.setString(14, accountList.getIndustry());
						ps.setString(15, accountList.getEmployees());
						ps.setString(16, accountList.getAnnualRevenue());
						ps.setString(17, accountList.getSicCode());
						ps.setString(18, accountList.getBillingAddressStreet());
						ps.setString(19,accountList.getBillingAddressCity());
						ps.setString(20, accountList.getBillingAddressCity());
						ps.setString(21,accountList.getBillingAddressState());
						ps.setString(22, accountList.getBillingAddressCode());
						ps.setString(23,accountList.getBillingAddressCountry());
						ps.setString(24, accountList.getShippingAddressStreet());
						ps.setString(25, accountList.getShippingAddressCity());
						ps.setString(26, accountList.getShippingAddressState());
						ps.setString(27, accountList.getShippingAddressCode());
						ps.setString(28, accountList.getShippingAddressCountry());
						ps.setString(29, 	accountList.getCreatedBy());
						ps.setLong(30,accountList.getCreatedTime());
						ps.setString(31, accountList.getModifyBy());
						ps.setLong(32, 	accountList.getModifyTime());
						ps.setLong(33, accountList.getParentAccountId());
					}

					@Override
					public int getBatchSize() {

						return accountRequest.size();
					}
				});


				return "Matched";

			} catch (Exception e) {


				logger.error("Exception at creation Time :" +e);
				return null;

			}}
		else {
			logger.debug("No AccountList in system");
			return "null";
		}




	}


	@Override
	public String insertLead(SyncAllDetailRequest syncAll) {
		logger.info("Entering into sync for leadDetail");

		if(syncAll.getLead()!=null && !syncAll.getLead().isEmpty())
		{
			try {
				String insertSql = "INSERT INTO LEAD (LEAD_ID, LEAD_OWNER, COMPANY, FNAME, LNAME, TITLE,"
						+ " EMAIL, PHONE, FAX, MOBILE, WEBSITE, LEAD_SOURCE, LEAD_STATUS, INDUSTRY, NO_OF_EMP, ANNUAL_REVENUE,"
						+ " RATING, EMAIL_OUTPUT, SKYPE_ID, ADDRESS_STREET, ADDRESS_CITY, ADDRESS_STATE, ADDRESS_ZIPCODE,"
						+ " ADDRESS_COUNTRY, DESCRIPTION, CR_DATE, MD_DATE, CR_BY,SALUTATION,SECONDARY_EMAIL,TWITTER,MD_BY)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";

				List<LeadData> leadData = syncAll.getLead();

				 jdbcTemplateObject.batchUpdate(insertSql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {

						LeadData leadList = leadData.get(i);
						ps.setLong(1, leadList.getLeadId());
						ps.setString(2, leadList.getLeadOwner());
						ps.setString(3, leadList.getCompany());
						ps.setString(4, leadList.getFirstName());
						ps.setString(5, leadList.getLastName());
						ps.setString(6, leadList.getTitle());
						ps.setString(7, leadList.getEmail());
						ps.setString(8, leadList.getPhone());
						ps.setString(9, leadList.getFax());
						ps.setString(10, leadList.getMobile());
						ps.setString(11, leadList.getWebsite());
						ps.setString(12, leadList.getLeadSource());
						ps.setString(13, leadList.getLeadStatus());
						ps.setString(14, leadList.getIndustry());
						ps.setString(15, leadList.getNoOfEmployees());
						ps.setString(16, leadList.getAnnualRevenue());
						ps.setString(17, leadList.getRating());
						ps.setBoolean(18, leadList.isEmailOptOut());
						ps.setString(19, leadList.getSkypeId());
						ps.setString(20, leadList.getAddressStreet());
						ps.setString(21, leadList.getAddressCity());
						ps.setString(22, leadList.getAddressState());
						ps.setString(23, leadList.getAddressZipCode());
						ps.setString(24, leadList.getAddressCounty());
						ps.setString(25, leadList.getDescription());

						java.sql.Date createdDate = new java.sql.Date(leadList.getCreatedDate().getTime());
						ps.setDate(26, createdDate);
						java.sql.Date modifyDate = new java.sql.Date(leadList.getModifyDate().getTime());
						ps.setDate(27, modifyDate);
						ps.setString(28, leadList.getCreatedBy());
						ps.setString(29, leadList.getSalutation());
						ps.setString(30, leadList.getSecondaryEmailId());
						ps.setString(31, leadList.getTwitter());
						ps.setString(32, leadList.getModifyBy());
					}

					@Override
					public int getBatchSize() {

						return leadData.size();
					}
				});
				return "Matched";
			} catch (Exception e) {
				logger.error("Exception at the creation Time:" + e);
				return null;
			}}else {
				logger.debug("No list in syncall");
				return "null";
			}


	}

	@Override
	public String insertContact(SyncAllDetailRequest syncAll) {
		logger.info("Entering into sync for contactDetail");
		if(syncAll.getContact()!=null && !syncAll.getContact().isEmpty())
		{
			try {
				String insertSql = "INSERT INTO CONTACT (CONTACT_ID, CONTACT_OWNER,LEAD_SOURCE,FIRST_NAME, LAST_NAME,ACCOUNT_NAME,EMAIL, TITLE,"
						+ "DEPARTMENT,PHONE,HOME_PHONE,OTHER_PHONE,FAX,MOBILE,DATE_OF_BIRTH,ASSISTANT,ASST_PHONE,REPORTS_TO,"
						+ "EMAIL_OPT_OUT,CREATED_BY,CREATED_DATE,MODIFY_BY,MODIFY_DATE,SKYPE_ID,SECONDARY_EMAIL,LAST_ACTIVITY_TIME,TWITTER,"
						+ "MAILING_ADDRESS_STREET,MAILING_ADDRESS_CITY,MAILING_ADDRESS_STATE,MAILING_ADDRESS_ZIP,MAILING_ADDRESS_COUNTRY,"
						+ "OTHER_ADDRESS_STREET,OTHER_ADDRESS_CITY,OTHER_ADDRESS_STATE,OTHER_ADDRESS_ZIP,OTHER_ADDRESS_COUNTRY,"
						+ "DESCRIPTION,PARENT_ID,SALUTATION,ACCOUNT_ID)"
						+ "VALUES (?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?,?,?,?,?);";
				List<ContactData> contactData = syncAll.getContact();
				 jdbcTemplateObject.batchUpdate(insertSql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ContactData contactList = contactData.get(i);
						ps.setLong(1, contactList.getContactId());
						ps.setString(2, contactList.getContactOwner());
						ps.setString(3, contactList.getLeadSource());
						ps.setString(4, contactList.getFirstName());
						ps.setString(5, contactList.getLastName());
						ps.setString(6, contactList.getAccountName());
						ps.setString(7, contactList.getEmail());
						ps.setString(8, contactList.getTitle());
						ps.setString(9, contactList.getDepartment());
						ps.setString(10, contactList.getPhone());
						ps.setString(11, contactList.getHomePhone());
						ps.setString(12, contactList.getOtherPhone());
						ps.setString(13, contactList.getFax());
						ps.setString(14, contactList.getMobile());
						ps.setString(15, contactList.getDateOfBirth());
						ps.setString(16, contactList.getAssistant());
						ps.setString(17, contactList.getAsstPhone());
						ps.setString(18, contactList.getReportsTo());
						ps.setBoolean(19, contactList.isEmailOptOut());
						ps.setString(20, contactList.getCreatedBy());
						ps.setLong(21, contactList.getCreatedDate());
						ps.setString(22, contactList.getModifyBy());
						ps.setLong(23, contactList.getModifyDate());
						ps.setString(24, contactList.getSkypeId());
						ps.setString(25, contactList.getSecondaryEmail());
						ps.setString(26, contactList.getLastActivityTime());
						ps.setString(27, contactList.getTwitter());
						ps.setString(28, contactList.getMailingAddressStreet());
						ps.setString(29, contactList.getMailingAddressCity());
						ps.setString(30, contactList.getMailingAddressState());
						ps.setString(31, contactList.getMailingAddressZip());
						ps.setString(32, contactList.getMailingAddressCountry());
						ps.setString(33, contactList.getOtherAddressStreet());
						ps.setString(34, contactList.getOtherAddressCity());
						ps.setString(35, contactList.getOtherAddressState());
						ps.setString(36, contactList.getOtherAddressZip());
						ps.setString(37, contactList.getOtherAddressCountry());
						ps.setString(38, contactList.getDescription());
						ps.setLong(39, contactList.getParentId());
						ps.setString(40, contactList.getSalutation());
						ps.setLong(41, contactList.getAccountId());

					}

					@Override
					public int getBatchSize() {

						return contactData.size();
					}
				});
				return matched;

			} catch (Exception e) {

				logger.error("Exception at the creation Time :" + e);
				return null;
			}}else {
				logger.debug("No list in syncAll");
				return "null";
			}

	}

	@Override
	public String insertDeal(SyncAllDetailRequest syncAll) {
		logger.info("Entering into sync for dealDetail");
		if(syncAll.getDeal()!=null && !syncAll.getDeal().isEmpty())
		{
			try {

				String sql = "INSERT INTO DEALS(DEAL_ID,DEAL_OWNER,AMOUNT,DEAL_NAME,CLOSING_DATE,ACCOUNT_NAME,"
						+ "STAGE,TYPE,PROBABILITY,NEXT_STEP,EXPECTED_REVENUE,LEAD_SOURCE,COMPAIGN_SOURCE,"
						+ "CONTACT_NAME,DESCRIPTION,CREATED_BY,MODIFIED_BY,CREATED_TIME,MODIFIED_TIME,NOTES,"
						+ "ATTACHMENTS,CONTACT_ID,ACCOUNT_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				List<DealRequest> dealRequest = syncAll.getDeal();
				 jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						DealRequest dealList = dealRequest.get(i);

						ps.setLong(1, dealList.getDealId());
						ps.setString(2, dealList.getDealOwner());
						ps.setInt(3, dealList.getAmount());
						ps.setString(4, dealList.getDealName());
						ps.setString(5, dealList.getClosingDate());
						ps.setString(6, dealList.getAccountName());
						ps.setString(7, dealList.getStage());
						ps.setString(8, dealList.getType());
						ps.setInt(9, dealList.getProbability());
						ps.setString(10, dealList.getNextStep());
						ps.setString(11, dealList.getExpectedRevenue());
						ps.setString(12, dealList.getLeadSource());
						ps.setString(13, dealList.getCampaignSource());
						ps.setString(14, dealList.getContactName());
						ps.setString(15, dealList.getDescription());
						ps.setString(16, dealList.getCreatedBy());
						ps.setString(17, dealList.getModifiedBy());
						ps.setLong(18, dealList.getCreatedTime());
						ps.setLong(19, dealList.getModifiedTime());
						ps.setString(20, dealList.getNotes());
						ps.setString(21, dealList.getAttachments());
						ps.setLong(22, dealList.getContactId());
						ps.setLong(23, dealList.getAccountId());

					}

					@Override
					public int getBatchSize() {

						return dealRequest.size();
					}
				});
				return matched;
			} catch (Exception e) {
				logger.error("Exception at the creation Time:" + e);
				return null;
			}}else {
				logger.debug("No dealList in system");
				return "null";
			}

	}

	@Override
	public String insertTask(SyncAllDetailRequest syncAll) {
		logger.info("Entering into sync for TASK");
		if(syncAll.getTask()!=null && !syncAll.getTask().isEmpty())
		{
			try {
				String insertSql = "INSERT INTO TASK (ACCOUNT, CONTACT, DESCRIPTION, DUE_DATE, PRIORITY, REMINDER, REPEAT, SEND_NOTIFICATION_EMAIL, STATUS, SUBJECT, TASK_ID,LEAD_ID,LEAD_NAME)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				List<TaskData> taskData = syncAll.getTask();
				 jdbcTemplateObject.batchUpdate(insertSql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						TaskData taskList = taskData.get(i);

						ps.setString(1, taskList.getAccount());
						ps.setString(2, taskList.getContact());
						ps.setString(3, taskList.getDescription());
						ps.setString(4, taskList.getDueDate());
						ps.setString(5, taskList.getPriority());
						ps.setString(6, taskList.getReminder());
						ps.setString(7, taskList.getRepeat());
						ps.setString(8, taskList.getSendNotificationEmail());
						ps.setString(9, taskList.getStatus());
						ps.setString(10, taskList.getSubject());
						ps.setLong(11, taskList.getTaskId());
						ps.setLong(12, taskList.getLeadId());
						ps.setString(13, taskList.getLeadName());
					}

					@Override
					public int getBatchSize() {

						return taskData.size();
					}
				});
				return matched;
			} catch (Exception e) {
				logger.error("Exception at creation time :" + e);
				return null;
			}}else {
				logger.debug("No Task list in syncall");
				return "null";
			}

	}

	public String insertParticipant() {
		logger.info("Entering into syncDao for Participant");

		try {

			String sql = "INSERT INTO PARTICIPATION(PARTICIPANT,EVENT_ID,TYPE)VALUES(?,?,?)";
			// List<EventRequest> eventRequest=syncAll.getEvent();

			 jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {

					partList = participant.get(i);
					System.out.println("value of i" +i);


					ps.setLong(1, partList.getParticipant());
					ps.setLong(2, partList.getEventId());
					ps.setString(3, partList.getType());
					System.out.println("Size" +participant.size());

				}

				@Override
				public int getBatchSize() {
					logger.debug("ParticipantSize" + participant.size());
					return participant.size();
				}
			});

			return matched;

		}catch (Exception e) {
			logger.error("Exception at creation time participants:" + e);
			return null;
		}

	}

	@Override
	public String insertEvent(SyncAllDetailRequest syncAll) {
		logger.info("Entering into syncDao for Event");
		if(syncAll.getEvent()!=null && !syncAll.getEvent().isEmpty())
		{
			try {

				List<EventRequest> eventRequest = syncAll.getEvent();
				for(int j=0;j<eventRequest.size();j++){
					EventRequest	eventReq=eventRequest.get(j);
					eventId=eventReq.getEventId();
					noOfParticipant	 =eventReq.getNoOfParticipants();
					System.out.println("no of participant" +noOfParticipant);
					partiSize=eventReq.getParticipants().size();

					participant = eventReq.getParticipants();


				}
				//System.out.println("Participant eventId" +participant.get(0).getEventId());

				System.out.println(eventId);
				System.out.println("participant id" +partiSize);
				System.out.println("Number of participant" +noOfParticipant+","+ partiSize);
				if(noOfParticipant!=0 && partiSize!=0){
					if(noOfParticipant==partiSize){
						
						logger.debug("Participant eventId" +participant.get(0).getEventId());

						if(eventId==participant.get(0).getEventId()){


							String sql = "INSERT INTO EVENT (EVENT_ID,TITLE,LOCATION,ALL_DAY,FROM_DATE,"
									+ "TO_DATE,HOST,NO_OF_PARTICIPANTS,CONTACT,ACCOUNT,REPEAT,DESCRIPTION,"
									+ "REMINDER,CREATED_BY,CREATED_TIME,"
									+ "MODIFY_BY,MODIFY_TIME,NOTES,ATTACHMENTS,CONTACT_ID,ACCOUNT_ID,FROM_TIME,TO_TIME,"
									+ "LEAD_ID,LEAD_NAME)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


							/*List<EventRequest> eventRequest = syncAll.getEvent();*/

							countEvent = jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

								@Override
								public void setValues(PreparedStatement ps,int i) throws SQLException {
									EventRequest eventList = eventRequest.get(i);

									ps.setLong(1, eventList.getEventId());
									ps.setString(2, eventList.getTitle());
									ps.setString(3, eventList.getLocation());
									ps.setBoolean(4, eventList.isAllDay());
									ps.setLong(5, eventList.getFromDate());
									ps.setLong(6, eventList.getToDate());
									ps.setString(7, eventList.getHost());
									ps.setInt(8, eventList.getNoOfParticipants());
									ps.setString(9, eventList.getContact());
									ps.setString(10, eventList.getAccount());
									ps.setString(11, eventList.getRepeat());
									ps.setString(12, eventList.getDescription());
									ps.setString(13, eventList.getReminder());
									ps.setString(14, eventList.getCreatedBy());
									ps.setLong(15, eventList.getCreatedTime());
									ps.setString(16, eventList.getModifyBy());
									ps.setLong(17, eventList.getModifyTime());
									ps.setString(18, eventList.getNotes());
									ps.setString(19, eventList.getAttachments());
									ps.setLong(20, eventList.getContactId());
									ps.setLong(21, eventList.getAccountId());
									ps.setLong(22, eventList.getFromTime());
									ps.setLong(23, eventList.getToTime());
									ps.setLong(24, eventList.getLeadId());
									ps.setString(25, eventList.getLeadName());
									System.out.println(eventList.getParticipants());
									if(!eventList.getParticipants().isEmpty()){

										if(eventList.getNoOfParticipants()!=0){
											participant =eventList.getParticipants();

										}}
								}

								@Override
								public int getBatchSize() {

									logger.debug("EventSize" + eventRequest.size());
									return eventRequest.size();
								}
							});


						}else {
							return notMatched;	
						}

						System.out.println("countEvent" +countEvent);
						//new SyncAllDetailDaoImpl().insertParticipant();

					}else {
						return notMatched;
					}return matched;
				}else {
					String sql = "INSERT INTO EVENT (EVENT_ID,TITLE,LOCATION,ALL_DAY,FROM_DATE,"
							+ "TO_DATE,HOST,NO_OF_PARTICIPANTS,CONTACT,ACCOUNT,REPEAT,DESCRIPTION,"
							+ "REMINDER,CREATED_BY,CREATED_TIME,"
							+ "MODIFY_BY,MODIFY_TIME,NOTES,ATTACHMENTS,CONTACT_ID,ACCOUNT_ID,FROM_TIME,TO_TIME,"
							+ "LEAD_ID,LEAD_NAME)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							EventRequest eventList = eventRequest.get(i);
							ps.setLong(1, eventList.getEventId());
							ps.setString(2, eventList.getTitle());
							ps.setString(3, eventList.getLocation());
							ps.setBoolean(4, eventList.isAllDay());
							ps.setLong(5, eventList.getFromDate());
							ps.setLong(6, eventList.getToDate());
							ps.setString(7, eventList.getHost());
							ps.setInt(8, eventList.getNoOfParticipants());
							ps.setString(9, eventList.getContact());
							ps.setString(10, eventList.getAccount());
							ps.setString(11, eventList.getRepeat());
							ps.setString(12, eventList.getDescription());
							ps.setString(13, eventList.getReminder());
							ps.setString(14, eventList.getCreatedBy());
							ps.setLong(15, eventList.getCreatedTime());
							ps.setString(16, eventList.getModifyBy());
							ps.setLong(17, eventList.getModifyTime());
							ps.setString(18, eventList.getNotes());
							ps.setString(19, eventList.getAttachments());
							ps.setLong(20, eventList.getContactId());
							ps.setLong(21, eventList.getAccountId());
							ps.setLong(22, eventList.getFromTime());
							ps.setLong(23, eventList.getToTime());
							ps.setLong(24, eventList.getLeadId());
							ps.setString(25, eventList.getLeadName());

						}

						@Override
						public int getBatchSize() {
							logger.debug("EventSize" + eventRequest.size());
							return eventRequest.size();
						}
					});

				}
				return matched;

			} catch (Exception e) {
				logger.error("Exception at creation time event:" + e);
				return null;
			}}else {
				logger.debug("No eventList in syncall");
				return "null";
			}

	}

	@Override
	public String insertCall(SyncAllDetailRequest syncAll) {
		logger.info("Entering into sync for Call");
		if(syncAll.getCall()!=null && !syncAll.getCall().isEmpty())
		{
			try {
				String sql = "INSERT INTO CALL(CALL_ID,TYPE,CONTACT,SUBJECT,CALL_PURPOSE,ACCOUNT,"
						+ "CALL_TYPE,CALL_START_TIME,CALL_DURATION,DESCRIPTION,CALL_RESULT,BILLABLE,"
						+ "REMINDER,NOTES,CREATED_BY,MODIFIED_BY,CREATED_TIME,MODIFIED_TIME,ACCOUNT_ID,"
						+ "CONTACT_ID,CALL_START_DATE,LEAD_ID,LEAD_NAME)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				List<CallRequest> callRequest = syncAll.getCall();
				 jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						CallRequest callList = callRequest.get(i);

						ps.setLong(1, callList.getCallId());
						ps.setString(2, callList.getType());
						ps.setString(3, callList.getContact());
						ps.setString(4, callList.getSubject());
						ps.setString(5, callList.getCallPurpose());
						ps.setString(6, callList.getAccount());
						ps.setString(7, callList.getCallType());
						ps.setLong(8, callList.getCallStartTime());
						ps.setLong(9, callList.getCallDuration());
						ps.setString(10, callList.getDescription());
						ps.setString(11, callList.getCallResult());
						ps.setBoolean(12, callList.isBillable());
						ps.setString(13, callList.getReminder());
						ps.setString(14, callList.getNotes());
						ps.setString(15, callList.getCreatedBy());
						ps.setString(16, callList.getModifiedBy());
						ps.setLong(17, callList.getCreatedTime());
						ps.setLong(18, callList.getModifiedTime());
						ps.setLong(19, callList.getAccountId());
						ps.setLong(20, callList.getContactId());
						ps.setLong(21, callList.getCallStartDate());
						ps.setLong(22, callList.getLeadId());
						ps.setString(23, callList.getLeadName());

					}

					@Override
					public int getBatchSize() {

						return callRequest.size();
					}
				});
				return matched;

			} catch (Exception e) {
				logger.error("Exception at creation Time:" + e);
				return null;
			}

		}else {
			logger.debug("No CallList in syncall");
			return "null";
		}

	}

	@Override
	public String insertCustChallenge(SyncAllDetailRequest syncAll) {
		logger.info("Entering into syncDao for customerChallenge");
		if(syncAll.getCustomerChallenge()!=null && !syncAll.getCustomerChallenge().isEmpty()){
			try {
				String sql="INSERT INTO CUSTOMER_CHALLENGE" + 
						"(CUSTOMER_ID, CONTACT_ID, CUSTOMER_NAME, CONTACT_NAME, LEAD_ID, LEAD_NAME, LOG_DATE, PRIORITY, ORIGIN,"
						+ " REASON, DUE_DATE, CLOSED_DATE, INCHARGE, CC_MAIL_ID, STATUS, SUBJECT, NOTES, DESCRIPTION,"
						+ " INTERNAL_NOTE, CUSTOMER_FEEDBACK, CREATED_BY, CREATED_DATE, CREATED_TIME, MODIFY_BY, MODIFY_DATE, MODIFY_TIME)" 
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				List<CustomerChallengeRequest> customerChallenge =syncAll.getCustomerChallenge();
				jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						CustomerChallengeRequest custChallenge	=customerChallenge.get(i);
						ps.setLong(1, custChallenge.getCustomerId());
						ps.setLong(2,custChallenge.getContactId());
						ps.setString(3,custChallenge.getCustomerName());
						ps.setString(4,custChallenge.getContactName());
						ps.setLong(5,custChallenge.getLeadId());
						ps.setString(6,custChallenge.getLeadName());
						ps.setLong(7,custChallenge.getLogDate());
						ps.setString(8,custChallenge.getPriority());
						ps.setString(9,custChallenge.getOrigin());
						ps.setString(10,custChallenge.getReason());
						ps.setLong(11,custChallenge.getDueDate());
						ps.setLong(12,custChallenge.getCloseddate());
						ps.setString(13,custChallenge.getIncharge());
						ps.setString(14,custChallenge.getCcMailId());
						ps.setString(15,custChallenge.getStatus());
						ps.setString(16,custChallenge.getSubject());
						ps.setString(17,custChallenge.getNotes());
						ps.setString(18,custChallenge.getDescription());
						ps.setString(19,custChallenge.getInternalNote());
						ps.setString(20,custChallenge.getCustomerFeedback());
						ps.setString(21,custChallenge.getCreatedBy());
						ps.setLong(22,custChallenge.getCreatedDate());
						ps.setLong(23,custChallenge.getCreatedTime());
						ps.setString(24,custChallenge.getModifyBy());
						ps.setLong(25,custChallenge.getModifyDate());
						ps.setLong(26,custChallenge.getModifyTime());
						System.out.println(custChallenge);

					}

					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						System.out.println("customer challenge" +customerChallenge.size());
						return customerChallenge.size();
					}
				});
				return matched;
			} catch (Exception e) {
				logger.error("Exception at creation Time:" + e);
				return null;
			}
		}else {
			logger.debug("No customerchallenge in syncall");
			return "null";
		}


	}

	@Override
	public String insertSalesBudget(SyncAllDetailRequest syncAll) {
		logger.info("Entering into syncDao for Account");
		if(syncAll.getSalesBudget()!=null && syncAll.getSalesBudget().isEmpty()){
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return null;
	}

	@Override
	public String insertImage(SyncAllDetailRequest syncAll) {
		logger.info("Entering into SyncDao for image");
		if(syncAll.getImageStore()!=null && !syncAll.getImageStore().isEmpty()){
			String sql = "INSERT INTO IMAGE_PATHSTORE(IMAGE_ID,IMAGE_URL)VALUES(?,?)";
		List<ImageStoreRequest>	imageaStoreList=syncAll.getImageStore();
		jdbcTemplateObject.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
			ImageStoreRequest imageStore	=imageaStoreList.get(i);
			ps.setLong(1,imageStore.getImageId());
			String base64String =imageStore.getImageFile();
			byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64String);
			//String filePath = "C:\\tomcat8\\Photos\\";
			ResourceBundle rb = ResourceBundle.getBundle("path");
			String filePath =rb.getString("filepath");
			String fileName = System.currentTimeMillis() + "";
			try {
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
				if (image == null) {
					logger.error("Buffered Image is null");
				}
				File imageFile = new File(filePath + fileName + ".jpg");

				// write the image
				ImageIO.write(image, "jpg",imageFile);
				System.out.println("ImagePath" +imageFile.getPath());
				 
			ps.setString(2, imageFile.getPath());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return imageaStoreList.size();
			}
		});
		return matched;
		}else {
			logger.debug("No image in syncall");
			return "null";
		}
		
		
		}
}
