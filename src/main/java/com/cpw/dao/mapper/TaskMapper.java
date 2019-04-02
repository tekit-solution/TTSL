package com.cpw.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.Task;

public class TaskMapper implements RowMapper<Task> {

	public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final Task task = new Task();
		task.setTaskId(resultSet.getLong("TASK_ID"));
		task.setSubject(resultSet.getString("SUBJECT"));
		task.setDueDate(resultSet.getString("DUE_DATE"));
		task.setContact(resultSet.getString("CONTACT"));
		task.setAccount(resultSet.getString("ACCOUNT"));
		task.setStatus(resultSet.getString("STATUS"));
		task.setPriority(resultSet.getString("PRIORITY"));
		task.setSendNotificationEmail(resultSet.getString("SEND_NOTIFICATION_EMAIL"));
		task.setReminder(resultSet.getString("REMINDER"));
		task.setRepeat(resultSet.getString("REPEAT"));
		task.setDescription(resultSet.getString("DESCRIPTION"));
		task.setLeadId(resultSet.getLong("LEAD_ID"));
		task.setLeadName(resultSet.getString("LEAD_NAME"));
		task.setContactId(resultSet.getLong("CONTACT_ID"));
		task.setAccountId(resultSet.getLong("ACCOUNT_ID"));
		task.setTaskOwner(resultSet.getString("TASKOWNER"));
		task.setTaskCreatedBy(resultSet.getString("CREATED_BY"));
		task.setTaskModifyBy(resultSet.getString("MODIFY_BY"));
		task.setTaskCreatedTime(resultSet.getLong("CREATED_TIME"));
		task.setTaskModifyTime(resultSet.getLong("MODIFY_TIME"));
		task.setTaskCompleted(resultSet.getBoolean("TASKCOMPLETED"));
		
		
		
		return task;
	}

}
