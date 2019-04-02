package com.cpw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CallRequest {
	
	private long callId;
	private String type;
	private String contact;
	private String subject;
	private String callPurpose;
	private String account;
	private String callType;
	private long callStartTime;
	private long callDuration;
	private String description;
	private String callResult;
	private boolean billable;
	private String reminder;
	private String notes;
	private String createdBy;
	private String modifiedBy;
	private long createdTime;
	private long modifiedTime;
	private long accountId;
	private long contactId;
	private long callStartDate;
	private long leadId;
	private String leadName;
	@JsonCreator
	public CallRequest(@JsonProperty("callId")long callId,@JsonProperty("type")String type,
			@JsonProperty("contact")String contact,@JsonProperty("subject")String subject,
			@JsonProperty("callPurpose")String callPurpose,@JsonProperty("account")String account,
			@JsonProperty("callType")String callType,@JsonProperty("callStartTime")long callStartTime,
			@JsonProperty("callDuration")long callDuration,@JsonProperty("description")String description,
			@JsonProperty("callResult")String callResult,@JsonProperty("billable")boolean billable,
			@JsonProperty("reminder")String reminder,@JsonProperty("notes")String notes,
			@JsonProperty("createdBy")String createdBy,@JsonProperty("modifiedBy")String modifiedBy,
			@JsonProperty("createdTime")long createdTime,@JsonProperty("modifiedTime")long modifiedTime,
			@JsonProperty("accountId")long accountId,@JsonProperty("contactId")long contactId,
			@JsonProperty("callStartDate")long callStartDate,@JsonProperty("leadId")long leadId,
			@JsonProperty("leadName")String leadName)
	
	{
		this.callId=callId;
		this.type=type;
		this.contact=contact;
		this.subject=subject;
		this.callPurpose=callPurpose;
		this.account=account;
		this.callType=callType;
		this.callStartTime=callStartTime;
		this.callDuration=callDuration;
		this.description=description;
		this.callResult=callResult;
		this.billable=billable;
		this.reminder=reminder;
		this.notes=notes;
		this.createdBy=createdBy;
		this.modifiedBy=modifiedBy;
		this.createdTime=createdTime;
		this.modifiedTime=modifiedTime;
		this.accountId=accountId;
		this.contactId=contactId;
		this.callStartDate=callStartDate;
		this.leadId=leadId;
		this.leadName=leadName;
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

	public long getCallId() {
		return callId;
	}

	public void setCallId(long callId) {
		this.callId = callId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCallPurpose() {
		return callPurpose;
	}

	public void setCallPurpose(String callPurpose) {
		this.callPurpose = callPurpose;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public long getCallStartTime() {
		return callStartTime;
	}

	public void setCallStartTime(long callStartTime) {
		this.callStartTime = callStartTime;
	}

	public long getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(long callDuration) {
		this.callDuration = callDuration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public boolean isBillable() {
		return billable;
	}

	public void setBillable(boolean billable) {
		this.billable = billable;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	
	
	public long getCallStartDate() {
		return callStartDate;
	}

	public void setCallStartDate(long callStartDate) {
		this.callStartDate = callStartDate;
	}

	//default constructor
	public CallRequest() {
		// TODO Auto-generated constructor stub
	}

}
