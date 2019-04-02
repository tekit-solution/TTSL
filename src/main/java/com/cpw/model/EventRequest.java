package com.cpw.model;

import java.util.ArrayList;
import java.util.List;

import com.cpw.jdbc.model.Participants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRequest {
	private long eventId;
	private String title;
	private String location;
	private boolean allDay;
	private long fromDate;
	private long toDate;
	private String host;
	private int noOfParticipants;
	private List<Participants> participants;
	private String contact;
	private String account;
	private String repeat;
	private String description;
	private String reminder;
	private String createdBy;
	private long createdTime;
	private String modifyBy;
	private long modifyTime;
	private String notes;
	private String attachments;
	private long contactId;
	private long accountId;
	private long fromTime;
	private long toTime;
	private long leadId;
	private String leadName;


	@JsonCreator
	public EventRequest(@JsonProperty("eventId")long eventId,@JsonProperty("title")String title,
			@JsonProperty("location")String location,@JsonProperty("allDay")boolean allDay,
			@JsonProperty("fromDate")long fromDate,@JsonProperty("toDate")long toDate,
			@JsonProperty("host")String host,@JsonProperty("participants")ArrayList<Participants> participants,
			@JsonProperty("contact")String contact,@JsonProperty("account")String account,
			@JsonProperty("repeat")String repeat,@JsonProperty("description")String description,
			@JsonProperty("reminder")String reminder,@JsonProperty("participantId")long participantId,
			@JsonProperty("createdBy")String createdBy,
			@JsonProperty("createdTime")long createdTime,@JsonProperty("modifyBy")String modifyBy,
			@JsonProperty("modifyTime")long modifyTime,@JsonProperty("notes")String notes,
			@JsonProperty("attachments")String attachments,@JsonProperty("noOfParticipants")int noOfParticipants,
			@JsonProperty("contactId")long contactId,@JsonProperty("accountId")long accountId,
			@JsonProperty("fromTime")long fromTime,@JsonProperty("toTime")long toTime,@JsonProperty("leadId")long leadId,
			@JsonProperty("leadName")String leadName)

	{
		this.eventId=eventId;
		this.title=title;
		this.location=location;
		this.allDay=allDay;
		this.fromDate=fromDate;
		this.toDate=toDate;
		this.host=host;
		this.participants=participants;
		this.contact=contact;
		this.account=account;
		this.repeat=repeat;
		this.description=description;
		this.reminder=reminder;
		this.createdBy=createdBy;
		this.createdTime=createdTime;
		this.modifyBy=modifyBy;
		this.modifyTime=modifyTime;
		this.notes=notes;
		this.attachments=attachments;
		this.noOfParticipants=noOfParticipants;
		this.contactId=contactId;
		this.accountId=accountId;
		this.fromTime=fromTime;
		this.toTime=toTime;
		this.leadId=leadId;
		this.leadName=leadName;

	}
	public EventRequest() {
		// TODO Auto-generated constructor stub
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public long getFromDate() {
		return fromDate;
	}

	public void setFromDate(long fromDate) {
		this.fromDate = fromDate;
	}

	public long getToDate() {
		return toDate;
	}

	public void setToDate(long toDate) {
		this.toDate = toDate;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	public List<Participants> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participants> participants) {
		this.participants = participants;
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

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}



	public int getNoOfParticipants() {
		return noOfParticipants;
	}

	public void setNoOfParticipants(int noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
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

	public long getFromTime() {
		return fromTime;
	}
	public void setFromTime(long fromTime) {
		this.fromTime = fromTime;
	}
	public long getToTime() {
		return toTime;
	}
	public void setToTime(long toTime) {
		this.toTime = toTime;

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
	@Override
	public String toString() {
		return "EventRequest [eventId=" + eventId + ", title=" + title + ", location=" + location + ", allDay=" + allDay
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", host=" + host + ", noOfParticipants="
				+ noOfParticipants + ", participants=" + participants + ", contact=" + contact + ", account=" + account
				+ ", repeat=" + repeat + ", description=" + description + ", reminder=" + reminder + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime
				+ ", notes=" + notes + ", attachments=" + attachments + ", contactId=" + contactId + ", accountId="
				+ accountId + ", fromTime=" + fromTime + ", toTime=" + toTime + ", leadId=" + leadId + ", leadName="
				+ leadName + "]";
	}
	
}

