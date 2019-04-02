package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cpw.dao.mapper.TaskMapper;
import com.cpw.jdbc.model.Task;
import com.cpw.services.NotificationImpl;

public class TaskDAOImpl implements TaskDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}


	@Override
	public List<Task> taskList(long taskId) {
		logger.info("Entering into taskList with taskId " + taskId);
		CpwTemplete<Task> cpwTemplete = new CpwTempleteImpl<Task>();
		if(taskId==-1){
			final String taskListSql = "Select * FROM TASK";
			List<Task> taskList=cpwTemplete.getRecordList(taskListSql, jdbcTemplateObject, new TaskMapper());
			return taskList;

		}else {

			logger.info("Entering into taskList with taskId " + taskId);
			final String taskListSql = "Select *FROM  TASK  WHERE TASK_ID >?";

			List<Task> taskList = jdbcTemplateObject.query(taskListSql, new Object[] { taskId }, new TaskMapper());
			return taskList;

		}	}

	@Override
	public int upsertTask(Task taskRequest,String userId) {
		logger.info("Entering into Task DAO");
		CpwTemplete<Task> cpwTemplete = new CpwTempleteImpl<Task>();
		String requestedRecord = "select * from task where task_id=?";
		Task taskInSystem = null;
		try {
			taskInSystem = jdbcTemplateObject.queryForObject(requestedRecord, new Object[] { taskRequest.getTaskId() },
					new TaskMapper());
		} catch (EmptyResultDataAccessException e) {
			taskInSystem = null;
		}
		try {
			int count = -1;
			logger.debug("Task ID" + taskRequest.getTaskId());
			if (taskInSystem != null && taskInSystem.getTaskId() == taskRequest.getTaskId()) {
				String updateSql = "UPDATE TASK SET ACCOUNT=?, CONTACT=?, DESCRIPTION=?, DUE_DATE=?, PRIORITY=?,"
						+ " REMINDER=?, REPEAT=?, SEND_NOTIFICATION_EMAIL=?, STATUS=?, SUBJECT=?,LEAD_ID=?,LEAD_NAME=?,CONTACT_ID=?,"
						+ " ACCOUNT_ID=?,TASKOWNER=?,CREATED_BY=?,MODIFY_BY=?,CREATED_TIME=?,MODIFY_TIME=?,TASKCOMPLETED=?" + " WHERE TASK_ID=?";
				count=jdbcTemplateObject.update(updateSql,
						taskRequest.getAccount() == null ? taskInSystem.getAccount() : taskRequest.getAccount(),
								taskRequest.getContact() == null ? taskInSystem.getContact() : taskRequest.getContact(),
										taskRequest.getDescription() == null ? taskInSystem.getDescription()
												: taskRequest.getDescription(),
												taskRequest.getDueDate() == null ? taskInSystem.getDueDate() : taskRequest.getDueDate(),
														taskRequest.getPriority() == null ? taskInSystem.getPriority() : taskRequest.getPriority(),
																taskRequest.getReminder() == null ? taskInSystem.getReminder() : taskRequest.getReminder(),
																		taskRequest.getRepeat() == null ? taskInSystem.getRepeat() : taskRequest.getRepeat(),
																				taskRequest.getSendNotificationEmail() == null ? taskInSystem.getSendNotificationEmail()
																						: taskRequest.getSendNotificationEmail(),
																						taskRequest.getStatus() == null ? taskInSystem.getStatus() : taskRequest.getStatus(),
																								taskRequest.getSubject() == null ? taskInSystem.getSubject() : taskRequest.getSubject(),
																										taskRequest.getLeadId(),
																										taskRequest.getLeadName()==null ? taskInSystem.getLeadName() : taskRequest.getLeadName(),
																												taskRequest.getAccountId(),taskRequest.getContactId(),
																												taskRequest.getTaskOwner()==null ? taskInSystem.getTaskOwner() : taskRequest.getTaskOwner(),
																														taskRequest.getTaskCreatedBy()==null ? taskInSystem.getTaskCreatedBy() : taskRequest.getTaskCreatedBy(),
																																taskRequest.getTaskModifyBy()==null ? taskInSystem.getTaskModifyBy() : taskRequest.getTaskModifyBy(),
																																		taskRequest.getTaskCreatedTime(),
																																		taskRequest.getTaskModifyTime(),
																																		taskRequest.isTaskCompleted(),
																																		taskRequest.getTaskId());
			} else {
				Object[] values = new Object[21];
				values[0] = taskRequest.getAccount();
				values[1] = taskRequest.getContact();
				values[2] = taskRequest.getDescription();
				values[3] = taskRequest.getDueDate();
				values[4] = taskRequest.getPriority();
				values[5] = taskRequest.getReminder();
				values[6] = taskRequest.getRepeat();
				values[7] = taskRequest.getSendNotificationEmail();
				values[8] = taskRequest.getStatus();
				values[9] = taskRequest.getSubject();
				values[10] = taskRequest.getTaskId();
				values[11]= taskRequest.getLeadId();
				values[12]=taskRequest.getLeadName();
				values[13]=taskRequest.getContactId();
				values[14]=taskRequest.getAccountId();
				values[15]=taskRequest.getTaskOwner();
				values[16]=taskRequest.getTaskCreatedBy();
				values[17]=taskRequest.getTaskModifyBy();
				values[18]=taskRequest.getTaskCreatedTime();
				values[19]=taskRequest.getTaskModifyTime();
				values[20]=taskRequest.isTaskCompleted();
				String insertSql = "INSERT INTO TASK (ACCOUNT, CONTACT, DESCRIPTION, DUE_DATE, PRIORITY, REMINDER, REPEAT, "
						+ "SEND_NOTIFICATION_EMAIL, STATUS, SUBJECT, TASK_ID,LEAD_ID,LEAD_NAME,CONTACT_ID,"
						+ "ACCOUNT_ID,TASKOWNER,CREATED_BY,MODIFY_BY,CREATED_TIME,MODIFY_TIME,TASKCOMPLETED)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				count = cpwTemplete.upsert(insertSql, values, jdbcTemplateObject);
				logger.debug("Record creation status: " + count);
			}
			logger.debug("Before Calling upsert");
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,taskRequest.getTaskId());
			return count;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeTask(long taskId,String userId) {
		logger.debug("Entering into remove Task" +taskId);
		final String deleteTaskSql = "DELETE FROM TASK WHERE TASK_ID = ?";
		try {
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,taskId);
			return jdbcTemplateObject.update(deleteTaskSql, taskId);
		} catch (DataAccessException e) {
			logger.error("No Task available in system coresponding to task id: " + taskId);
			return 0;
		}

	}

}
