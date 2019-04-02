package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.TaskDAOImpl;
import com.cpw.jdbc.model.Task;
import com.cpw.model.TaskData;
import com.cpw.model.TaskDataResponse;

public class TaskImpl {
	public TaskImpl() {
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private final TaskDAOImpl taskDAO = (TaskDAOImpl) context.getBean("taskDAOImpl");

	public int upsertTask(TaskData taskRequest,String userId) {
		logger.info("Entering into upsert Task");
		return taskDAO.upsertTask(map(taskRequest),userId);
	}

	public int removeTask(long taskId,String userId) {
		logger.info("Entering into Remove Task" +taskId);
		return taskDAO.removeTask(taskId,userId);
	}

	public List<TaskDataResponse> taskList(long taskId) {
		logger.info("Entering into task List" +taskId);
		final List<Task> taskList = taskDAO.taskList(taskId);
		return map(taskList);

	}

	private List<TaskDataResponse> map(List<Task> taskList) {
		List<TaskDataResponse> taskResponseList = Collections.emptyList();
		if (taskList != null && !taskList.isEmpty()) {
			taskResponseList = new ArrayList<TaskDataResponse>();
			for (Task task : taskList) {
				TaskDataResponse trackingResponse = map(task);
				taskResponseList.add(trackingResponse);
			}
			taskList.clear();
		}
		return taskResponseList;

	}

	private TaskDataResponse map(Task task) {
		TaskDataResponse taskData = new TaskDataResponse();
		if (task != null) {
			taskData.setAccount(task.getAccount());
			taskData.setContact(task.getContact());
			taskData.setDescription(task.getDescription());
			taskData.setDueDate(task.getDueDate());
			taskData.setPriority(task.getPriority());
			taskData.setReminder(task.getReminder());
			taskData.setSendNotificationEmail(task.getSendNotificationEmail());
			taskData.setStatus(task.getStatus());
			taskData.setSubject(task.getSubject());
			taskData.setTaskId(task.getTaskId());
			taskData.setLeadId(task.getLeadId());
			taskData.setLeadName(task.getLeadName());
			taskData.setAccountId(task.getAccountId());
			taskData.setContactId(task.getContactId());
			taskData.setTaskOwner(task.getTaskOwner());
			taskData.setTaskCreatedBy(task.getTaskCreatedBy());
			taskData.setTaskModifyBy(task.getTaskModifyBy());
			taskData.setTaskCreatedTime(task.getTaskCreatedTime());
			taskData.setTaskModifyTime(task.getTaskModifyTime());
			taskData.setTaskCompleted(task.isTaskCompleted());
		}
		return taskData;
	}

	private Task map(TaskData taskRequest) {
		Task task = new Task();
		if (taskRequest != null) {
			task.setAccount(taskRequest.getAccount());
			task.setContact(taskRequest.getContact());
			task.setDescription(taskRequest.getDescription());
			task.setDueDate(taskRequest.getDueDate());
			task.setPriority(taskRequest.getPriority());
			task.setReminder(taskRequest.getReminder());
			task.setSendNotificationEmail(taskRequest.getSendNotificationEmail());
			task.setStatus(taskRequest.getStatus());
			task.setSubject(taskRequest.getSubject());
			task.setTaskId(taskRequest.getTaskId());
			task.setLeadId(taskRequest.getLeadId());
			task.setLeadName(taskRequest.getLeadName());
			task.setAccountId(taskRequest.getAccountId());
			task.setContactId(taskRequest.getContactId());
			task.setTaskOwner(taskRequest.getTaskOwner());
			task.setTaskCreatedBy(taskRequest.getTaskCreatedBy());
			task.setTaskModifyBy(taskRequest.getTaskModifyBy());
			task.setTaskCreatedTime(taskRequest.getTaskCreatedTime());
			task.setTaskModifyTime(taskRequest.getTaskModifyTime());
			task.setTaskCompleted(taskRequest.isTaskCompleted());
			
		}
		return task;
	}
}
