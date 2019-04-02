package com.cpw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncAllDetailRequest {
	private List<LeadData> lead;
	private List<ContactData> contact;
	private List<AddAccountRequest> account;
	private List<DealRequest> deal;
	private List<TaskData> task;
	private List<EventRequest> event;
	private List<CallRequest> call;
	private List<CustomerChallengeRequest> customerChallenge;
	private List<SalesBudgetRequest> salesBudget;
	private List<ImageStoreRequest> imageStore;
	
	@JsonCreator
	public SyncAllDetailRequest(@JsonProperty("lead")List<LeadData> lead,@JsonProperty("contact")List<ContactData> contact,
			@JsonProperty("account")List<AddAccountRequest> account,@JsonProperty("deal")List<DealRequest> deal,
			@JsonProperty("task")List<TaskData> task,@JsonProperty("event")List<EventRequest> event,
			@JsonProperty("call")List<CallRequest> call,@JsonProperty("customerChallenge")List<CustomerChallengeRequest> customerChallenge,
			@JsonProperty("salesBudget")List<SalesBudgetRequest> salesBudget,@JsonProperty("imageStore")List<ImageStoreRequest> imageStore)
	{
		this.lead=lead;
		this.contact=contact;
		this.account=account;
		this.deal=deal;
		this.task=task;
		this.event=event;
		this.call=call;
		this.customerChallenge=customerChallenge;
		this.salesBudget=salesBudget;
		this.imageStore=imageStore;
		
	}

	public List<LeadData> getLead() {
		return lead;
	}

	public void setLead(List<LeadData> lead) {
		this.lead = lead;
	}

	public List<ContactData> getContact() {
		return contact;
	}

	public void setContact(List<ContactData> contact) {
		this.contact = contact;
	}

	public List<AddAccountRequest> getAccount() {
		return account;
	}

	public void setAccount(List<AddAccountRequest> account) {
		this.account = account;
	}

	public List<DealRequest> getDeal() {
		return deal;
	}

	public void setDeal(List<DealRequest> deal) {
		this.deal = deal;
	}

	public List<TaskData> getTask() {
		return task;
	}

	public void setTask(List<TaskData> task) {
		this.task = task;
	}

	public List<EventRequest> getEvent() {
		return event;
	}

	public void setEvent(List<EventRequest> event) {
		this.event = event;
	}

	public List<CallRequest> getCall() {
		return call;
	}

	public void setCall(List<CallRequest> call) {
		this.call = call;
	}
	

	public List<CustomerChallengeRequest> getCustomerChallenge() {
		return customerChallenge;
	}

	public void setCustomerChallenge(List<CustomerChallengeRequest> customerChallenge) {
		this.customerChallenge = customerChallenge;
	}

	public List<SalesBudgetRequest> getSalesBudget() {
		return salesBudget;
	}

	public void setSalesBudget(List<SalesBudgetRequest> salesBudget) {
		this.salesBudget = salesBudget;
	}

	public List<ImageStoreRequest> getImageStore() {
		return imageStore;
	}

	public void setImageStore(List<ImageStoreRequest> imageStore) {
		this.imageStore = imageStore;
	}

	@Override
	public String toString() {
		return "SyncAllDetailRequest [lead=" + lead + ", contact=" + contact + ", account=" + account + ", deal=" + deal
				+ ", task=" + task + ", event=" + event + ", call=" + call + ", customerChallenge=" + customerChallenge
				+ ", salesBudget=" + salesBudget + ", imageStore=" + imageStore + "]";
	}

	



	
	

}
