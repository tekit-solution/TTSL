package com.cpw.jdbc.model;

public class Task {
	private long taskId;
	private String subject;
	private String dueDate;
	private String contact;
	private String account;
	private String status;
	private String priority;
	private String sendNotificationEmail;
	private String reminder;
	private String repeat;
	private String description;
	private long leadId;
	private String leadName;
	private long contactId;
	private long accountId;
	private String taskOwner;
	private String taskCreatedBy;
	private String taskModifyBy;
	private long taskCreatedTime;
	private long taskModifyTime;
	private boolean taskCompleted;
	
	
	public boolean isTaskCompleted() {
		return taskCompleted;
	}
	public void setTaskCompleted(boolean taskCompleted) {
		this.taskCompleted = taskCompleted;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getTaskOwner() {
		return taskOwner;
	}
	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}
	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}
	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}
	public String getTaskModifyBy() {
		return taskModifyBy;
	}
	public void setTaskModifyBy(String taskModifyBy) {
		this.taskModifyBy = taskModifyBy;
	}
	public long getTaskCreatedTime() {
		return taskCreatedTime;
	}
	public void setTaskCreatedTime(long taskCreatedTime) {
		this.taskCreatedTime = taskCreatedTime;
	}
	public long getTaskModifyTime() {
		return taskModifyTime;
	}
	public void setTaskModifyTime(long taskModifyTime) {
		this.taskModifyTime = taskModifyTime;
	}
	public long getLeadId() {
		return leadId;
	}
	public void setLeadId(long leadId) {
		this.leadId = leadId;
	}
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getStatus() {
		return status;
	}
	
public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSendNotificationEmail() {
		return sendNotificationEmail;
	}
	public void setSendNotificationEmail(String sendNotificationEmail) {
		this.sendNotificationEmail = sendNotificationEmail;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
