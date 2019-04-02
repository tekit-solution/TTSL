package com.cpw.jdbc.model;

import java.util.List;

import com.cpw.model.AddAccountRequest;
import com.cpw.model.CallRequest;
import com.cpw.model.ContactData;
import com.cpw.model.CustomerChallengeRequest;
import com.cpw.model.DealRequest;
import com.cpw.model.EventRequest;
import com.cpw.model.ImageStoreRequest;
import com.cpw.model.LeadData;
import com.cpw.model.SalesBudgetRequest;
import com.cpw.model.TaskData;

public class SyncAllDetail {
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
	
	
	
	
	

}
