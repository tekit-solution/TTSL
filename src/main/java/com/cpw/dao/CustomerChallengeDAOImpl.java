package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cpw.dao.mapper.CustomerChallengeMapper;
import com.cpw.jdbc.model.CustomerChallenge;
import com.cpw.services.NotificationImpl;

public class CustomerChallengeDAOImpl implements CustomerChallengeDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int customerChallenge(CustomerChallenge customerChallenge,String userId) {
		logger.info("Entering into customerChallenge DAO");
		CpwTemplete<CustomerChallenge> cpwTemplete = new CpwTempleteImpl<CustomerChallenge>();
		
		String q1 = "SELECT *FROM CUSTOMER_CHALLENGE WHERE CUSTOMER_ID=?";
		CustomerChallenge custChallenge = null;
		try {
			custChallenge = jdbcTemplateObject.queryForObject(q1, new Object[] { customerChallenge.getCustomerId()}, new CustomerChallengeMapper());
		} catch (EmptyResultDataAccessException e) {
			custChallenge = null;
		}
		try {
			int count = -1;
			if(custChallenge!=null && custChallenge.getCustomerId()==customerChallenge.getCustomerId())
			{
				logger.debug("UPDATE values" + customerChallenge.getCustomerId());
				String updateSql="UPDATE CUSTOMER_CHALLENGE SET CONTACT_ID=?,CUSTOMER_NAME=?,CONTACT_NAME=?,"
						+ "LEAD_ID=?,LEAD_NAME=?,LOG_DATE=?,PRIORITY=?,ORIGIN=?,REASON=?,"
						+ "DUE_DATE=?,CLOSED_DATE=?,INCHARGE=?,CC_MAIL_ID=?,STATUS=?,"
						+ "SUBJECT=?,NOTES=?,DESCRIPTION=?,INTERNAL_NOTE=?,CUSTOMER_FEEDBACK=?,"
						+ "CREATED_BY=?,CREATED_DATE=?,CREATED_TIME=?,MODIFY_BY=?,"
						+ "MODIFY_DATE=?,MODIFY_TIME=? WHERE CUSTOMER_ID=? ";
				count= jdbcTemplateObject.update(updateSql, customerChallenge.getContactId(),
						customerChallenge.getCustomerName(),
						customerChallenge.getContactName(),
						customerChallenge.getLeadId(),
						customerChallenge.getLeadName(),
						customerChallenge.getLogDate(),
						customerChallenge.getPriority(),
						customerChallenge.getOrigin(),
						customerChallenge.getReason(),
						customerChallenge.getDueDate(),
						customerChallenge.getCloseddate(),
						customerChallenge.getIncharge(),
						customerChallenge.getCcMailId(),
						customerChallenge.getStatus(),
						customerChallenge.getSubject(),
						customerChallenge.getNotes(),
						customerChallenge.getDescription(),
						customerChallenge.getInternalNote(),
						customerChallenge.getCustomerFeedback(),
						customerChallenge.getCreatedBy(),
						customerChallenge.getCreatedDate(),
						customerChallenge.getCreatedTime(),
						customerChallenge.getModifyBy(),
						customerChallenge.getModifyDate(),
						customerChallenge.getModifyTime(),
						customerChallenge.getCustomerId());
			}else {
				
				Object [] values = new Object [26];
				values [0] = customerChallenge.getCustomerId();
				values [1] = customerChallenge.getContactId();
				values [2] = customerChallenge.getCustomerName();
				values [3] = customerChallenge.getContactName();
				values [4] = customerChallenge.getLeadId();
				values [5] = customerChallenge.getLeadName();
				values [6] = customerChallenge.getLogDate();
				values [7] = customerChallenge.getPriority();
				values [8] = customerChallenge.getOrigin();
				values [9] = customerChallenge.getReason();
				values [10] = customerChallenge.getDueDate();
				values [11] = customerChallenge.getCloseddate();
				values [12] = customerChallenge.getIncharge();
				values [13] = customerChallenge.getCcMailId();
				values [14] = customerChallenge.getStatus();
				values [15] = customerChallenge.getSubject();
				values [16] = customerChallenge.getNotes();
				values [17] = customerChallenge.getDescription();
				values [18] = customerChallenge.getInternalNote();
				values [19] = customerChallenge.getCustomerFeedback();
				values [20] = customerChallenge.getCreatedBy();
				values [21] = customerChallenge.getCreatedDate();
				values [22] = customerChallenge.getCreatedTime();
				values [23] = customerChallenge.getModifyBy();
				values [24] = customerChallenge.getModifyDate();
				values [25] = customerChallenge.getModifyTime();

				logger.debug("INSERT values" + values[1]);
				String sql = "INSERT INTO CUSTOMER_CHALLENGE" + 
						"(CUSTOMER_ID, CONTACT_ID, CUSTOMER_NAME, CONTACT_NAME, LEAD_ID, LEAD_NAME, LOG_DATE, PRIORITY, ORIGIN,"
						+ " REASON, DUE_DATE, CLOSED_DATE, INCHARGE, CC_MAIL_ID, STATUS, SUBJECT, NOTES, DESCRIPTION,"
						+ " INTERNAL_NOTE, CUSTOMER_FEEDBACK, CREATED_BY, CREATED_DATE, CREATED_TIME, MODIFY_BY, MODIFY_DATE, MODIFY_TIME)" 
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			
					logger.debug("Before Calling upsert");
					 count = cpwTemplete.upsert(sql, values, jdbcTemplateObject);
					logger.debug("Record creation status: "+count);
					
				}
			logger.debug("Before Calling upsert");
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,customerChallenge.getCustomerId());
			return count;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}

	@Override
	public List<CustomerChallenge> customerChallengeList(long id) {
		CpwTemplete<CustomerChallenge> cpwTemplete = new CpwTempleteImpl<CustomerChallenge>();


		try {
			if(id==-1)
			{
				logger.info("Entering into ChallengeList ");
				final String sql = "SELECT * FROM CUSTOMER_CHALLENGE";
				List<CustomerChallenge> challengeList = cpwTemplete.getRecordList(sql, jdbcTemplateObject,new CustomerChallengeMapper());
				return challengeList;
			}else
			{
				logger.debug("Entering into challengeList" +id);
				final String sql = "SELECT * FROM CUSTOMER_CHALLENGE WHERE CUSTOMER_ID>?";
				List<CustomerChallenge> challengeList = jdbcTemplateObject.query(sql, new Object[] {id},
						new CustomerChallengeMapper());
				return challengeList;
			}
		} catch (Exception e) {
			logger.debug("No list in System" +e);
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int removeCustomerChallenge(long id,String userId) {
		logger.info("Entering into remove CustomerChallenge");
		String sql="DELETE FROM CUSTOMER_CHALLENGE WHERE CUSTOMER_ID=? ";
		try {
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,id);
			return jdbcTemplateObject.update(sql, id);
		} catch (Exception e) {
			logger.error("No CustomerChallenge available in system coresponding to id: " + id);
			e.printStackTrace();
			return 0;
		}

	}

}
