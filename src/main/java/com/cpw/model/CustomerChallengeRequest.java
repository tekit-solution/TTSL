/**
 * 
 */
package com.cpw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Unknown
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerChallengeRequest {
	
	private long customerId;
	private long contactId;
	private String customerName;
	private String contactName;
	private long leadId;
	private String leadName;
	private long logDate;
	private String priority;
	private String origin;
	private String reason;
	private long dueDate;
	private long closeddate;
	private String incharge;
	private String ccMailId;
	private String status;
	private String subject;
	private String notes;
	private String description;
	private String internalNote;
	private String customerFeedback;
	private String createdBy;
	private long createdDate;
	private long createdTime;
	private String modifyBy;
	private long modifyDate;
	private long modifyTime;
	
	@JsonCreator
	public CustomerChallengeRequest(@JsonProperty("customerId")long customerId,@JsonProperty("contactId")long contactId,
			@JsonProperty("customerName")String customerName,@JsonProperty("contactName")String contactName,
			@JsonProperty("leadId")long leadId,@JsonProperty("leadName")String leadName,
			@JsonProperty("logDate")long logDate,@JsonProperty("priority")String priority,
			@JsonProperty("origin")String origin,@JsonProperty("reason")String reason,
			@JsonProperty("dueDate")long dueDate,@JsonProperty("closeddate")long closeddate,
			@JsonProperty("incharge")String incharge,@JsonProperty("ccMailId")String ccMailId,
			@JsonProperty("status")String status,@JsonProperty("subject")String subject,
			@JsonProperty("notes")String notes,@JsonProperty("description")String description,
			@JsonProperty("internalNote")String internalNote,@JsonProperty("customerFeedback")String customerFeedback,
			@JsonProperty("createdBy")String createdBy,@JsonProperty("createdDate")long createdDate,
			@JsonProperty("createdTime")long createdTime,@JsonProperty("modifyBy")String modifyBy,
			@JsonProperty("modifyDate")long modifyDate,@JsonProperty("modifyTime")long modifyTime)
	{
		this.customerId=customerId;
		this.contactId=contactId;
		this.customerName=customerName;
		this.contactName=contactName;
		this.leadId=leadId;
		this.leadName=leadName;
		this.logDate=logDate;
		this.priority=priority;
		this.origin=origin;
		this.reason=reason;
		this.dueDate=dueDate;
		this.closeddate=closeddate;
		this.incharge=incharge;
		this.ccMailId=ccMailId;
		this.status=status;
		this.subject=subject;
		this.notes=notes;
		this.description=description;
		this.internalNote=internalNote;
		this.customerFeedback=customerFeedback;
		this.createdBy=createdBy;
		this.createdDate=createdDate;
		this.createdTime=createdTime;
		this.modifyBy=modifyBy;
		this.modifyDate=modifyDate;
		this.modifyTime=modifyTime;
		
		
	}
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public long getLogDate() {
		return logDate;
	}
	public void setLogDate(long logDate) {
		this.logDate = logDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getDueDate() {
		return dueDate;
	}
	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}
	public long getCloseddate() {
		return closeddate;
	}
	public void setCloseddate(long closeddate) {
		this.closeddate = closeddate;
	}
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	public String getCcMailId() {
		return ccMailId;
	}
	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInternalNote() {
		return internalNote;
	}
	public void setInternalNote(String internalNote) {
		this.internalNote = internalNote;
	}
	public String getCustomerFeedback() {
		return customerFeedback;
	}
	public void setCustomerFeedback(String customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
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
	public long getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(long modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "CustomerChallengeRequest [customerId=" + customerId + ", contactId=" + contactId + ", customerName="
				+ customerName + ", contactName=" + contactName + ", leadId=" + leadId + ", leadName=" + leadName
				+ ", logDate=" + logDate + ", priority=" + priority + ", origin=" + origin + ", reason=" + reason
				+ ", dueDate=" + dueDate + ", closeddate=" + closeddate + ", incharge=" + incharge + ", ccMailId="
				+ ccMailId + ", status=" + status + ", subject=" + subject + ", notes=" + notes + ", description="
				+ description + ", internalNote=" + internalNote + ", customerFeedback=" + customerFeedback
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", createdTime=" + createdTime
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", modifyTime=" + modifyTime + "]";
	}
	
	
	
	/*private long id;
	private String code;
	private long customerId;
	private long contactId;
	@JsonSerialize(using = DateSerializer.class)
	private Date logDate;
	private int status;
	private long priority;
	private int origin;
	private int typeId;
	private String reason;
	@JsonSerialize(using = DateSerializer.class)
	private Date dueDate;
	private long inchargeId;
	private String ccEmail;
	private String subject;
	private String description;
	private String internalNote;
	private String customerFeedback;
	@JsonSerialize(using = DateSerializer.class)
	private Date closeDate;
	private String note;
	private long locId;
	private long fyId;
	private long time;// Database CREATED_TIME
	private int isDeleted;
	private long createdBy;
	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;
	private String createdTime;
	private long modifyBy;
	@JsonSerialize(using = DateSerializer.class)
	private Date modifyDate;
	private String modifyTime;

	@JsonCreator
	public CustomerChallengeRequest(@JsonProperty("id") long id, @JsonProperty("code") String code,
			@JsonProperty("customerId") long customerId, @JsonProperty("contactId") long contactId,
			@JsonProperty("logDate") Date logDate, @JsonProperty("status") int status,
			@JsonProperty("priority") long priority, @JsonProperty("origin") int origin,
			@JsonProperty("typeId") int typeId, @JsonProperty("reason") String reason,
			@JsonProperty("dueDate") Date dueDate, @JsonProperty("inchargeId") long inchargeId,
			@JsonProperty("ccEmail") String ccEmail, @JsonProperty("subject") String subject,
			@JsonProperty("description") String description, @JsonProperty("internalNote") String internalNote,
			@JsonProperty("customerFeedback") String customerFeedback, @JsonProperty("closeDate") Date closeDate,
			@JsonProperty("note") String note, @JsonProperty("locId") long locId, @JsonProperty("fyId") long fyId,
			@JsonProperty("time") long time, @JsonProperty("isDeleted") int isDeleted,
			@JsonProperty("createdBy") long createdBy, @JsonProperty("createdDate") Date createdDate,
			@JsonProperty("createdTime") String createdTime, @JsonProperty("modifyBy") long modifyBy,
			@JsonProperty("modifyDate") Date modifyDate, @JsonProperty("modifyTime") String modifyTime

	)*/ 
	
	
		
}
