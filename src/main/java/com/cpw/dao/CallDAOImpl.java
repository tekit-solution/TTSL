package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cpw.dao.mapper.CallMapper;
import com.cpw.jdbc.model.Call;
import com.cpw.services.NotificationImpl;

public class CallDAOImpl implements CallDAO{

	final private Logger logger=LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject=new JdbcTemplate(ds);

	}

	@Override
	public int upsertCall(Call call,String userId) {
		logger.info("Entering into callDAO");
		CpwTemplete<Call> cpwTemplete=new CpwTempleteImpl<Call>();

		String q1="SELECT *FROM CALL WHERE CALL_ID=?";
		Call c=null;
      try {
			c=jdbcTemplateObject.queryForObject(q1, new Object[]{call.getCallId()},new CallMapper());

		} catch (EmptyResultDataAccessException e) {
			c=null;
		}
		try {

			int count=-1;
			if(c!=null && c.getCallId() == call.getCallId())
			{
				logger.debug("Update value"+ call.getCallId());
				
				String updateSql="UPDATE CALL SET TYPE=?,CONTACT=?,SUBJECT=?,CALL_PURPOSE=?,"
						+ "ACCOUNT=?,CALL_TYPE=?,CALL_START_TIME=?,CALL_DURATION=?,DESCRIPTION=?,"
						+ "CALL_RESULT=?,BILLABLE=?,REMINDER=?,NOTES=?,CREATED_BY=?,MODIFIED_BY=?,"
						+ "CREATED_TIME=?,MODIFIED_TIME=?,ACCOUNT_ID=?,CONTACT_ID=?,CALL_START_DATE=?,"
						+ "LEAD_ID=?,LEAD_NAME=? WHERE CALL_ID=?";

				count= jdbcTemplateObject.update(updateSql, call.getType(),
						call.getContact(),
						call.getSubject(),
						call.getCallPurpose(),
						call.getAccount(),
						call.getCallType(),
						call.getCallStartTime(),
						call.getCallDuration(),
						call.getDescription(),
						call.getCallResult(),
						call.isBillable(),
						call.getReminder(),
						call.getNotes(),
						call.getCreatedBy(),
						call.getModifiedBy(),
						call.getCreatedTime(),
						call.getModifiedTime(),
						call.getAccountId(),
						call.getContactId(),
						call.getCallStartDate(),
						call.getLeadId(),
						call.getLeadName(),
						call.getCallId());
			}else {

				Object[] values=new Object[23];
				values[0]=call.getCallId();
				values[1]=call.getType();
				values[2]=call.getContact();
				values[3]=call.getSubject();
				values[4]=call.getCallPurpose();
				values[5]=call.getAccount();
				values[6]=call.getCallType();
				values[7]=call.getCallStartTime();
				values[8]=call.getCallDuration();
				values[9]=call.getDescription();
				values[10]=call.getCallResult();
				values[11]=call.isBillable();
				values[12]=call.getReminder();
				values[13]=call.getNotes();
				values[14]=call.getCreatedBy();
				values[15]=call.getModifiedBy();
				values[16]=call.getCreatedTime();
				values[17]=call.getModifiedTime();
				values[18]=call.getAccountId();
				values[19]=call.getContactId();
				values[20]=call.getCallStartDate();
				values[21]=call.getLeadId();
				values[22]=call.getLeadName();

				logger.debug("Insert Call Values" +values[0]);
				String sql="INSERT INTO CALL(CALL_ID,TYPE,CONTACT,SUBJECT,CALL_PURPOSE,ACCOUNT,"
						+ "CALL_TYPE,CALL_START_TIME,CALL_DURATION,DESCRIPTION,CALL_RESULT,BILLABLE,"
						+ "REMINDER,NOTES,CREATED_BY,MODIFIED_BY,CREATED_TIME,MODIFIED_TIME,ACCOUNT_ID,"
						+ "CONTACT_ID,CALL_START_DATE,LEAD_ID,LEAD_NAME)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				count=cpwTemplete.upsert(sql, values, jdbcTemplateObject);
				logger.debug("Record Creation status" +count);

			}
			logger.debug("before calling upsert" );
			logger.debug("User Id" +userId);
			NotificationImpl notificationDAO=new NotificationImpl();
      notificationDAO.tokenList(userId,call.getCallId());
			return count;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}


	}

	@Override
	public List<Call> callList(long callId) {
		CpwTemplete<Call> cpwTemplete = new CpwTempleteImpl<Call>();
	
		try {
			if(callId==-1)
			{
				logger.debug("Entering into callList "+callId);
				final String trackingSql = "SELECT * FROM CALL";
				List<Call> callList = cpwTemplete.getRecordList(trackingSql, jdbcTemplateObject,new CallMapper());
				return callList;
			}else{
			logger.debug("Entering into CallList" +callId);
			
			String trackingSql="SELECT * FROM CALL WHERE CALL_ID>?";
			List<Call> callList=jdbcTemplateObject.query(trackingSql, new Object[]{callId}, new CallMapper());
			return callList;
			}
			
		} catch (DataAccessException e) {
			logger.debug("No CallList In System");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int removeCall(long callId,String userId) {
		logger.debug("Entering into Remove" +callId);
		final String sql="DELETE FROM CALL WHERE CALL_ID=?";
		try {
			NotificationImpl notificationDAO=new NotificationImpl();
		     notificationDAO.tokenList(userId,callId);
			return jdbcTemplateObject.update(sql, callId);
			
		} catch (Exception e) {
			logger.debug("NO CallList is available in system corresponding to Call Id" +callId);
			return 0;
		}
		
	}

}
