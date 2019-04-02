package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.CustomerChallengeDAOImpl;
import com.cpw.jdbc.model.CustomerChallenge;
import com.cpw.model.CustomerChallengeRequest;
import com.cpw.model.CustomerChallengeResponse;


public class CustomerChallengeImpl {
	public CustomerChallengeImpl() {
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private final CustomerChallengeDAOImpl customerChallengeDAO = (CustomerChallengeDAOImpl) context
			.getBean("customerChallengeDAOImpl");


	public int customerChallenge(CustomerChallengeRequest customerChallengeRequest,String userId) {
		logger.info("Entering into customerChallenge");
		return customerChallengeDAO.customerChallenge(map(customerChallengeRequest),userId);
	}

	private CustomerChallenge map(CustomerChallengeRequest customerChallengeRequest) {
		CustomerChallenge customerChallenge = new CustomerChallenge();

		if (customerChallengeRequest != null) {
			customerChallenge.setCustomerId(customerChallengeRequest.getCustomerId());
			customerChallenge.setContactId(customerChallengeRequest.getContactId());
			customerChallenge.setCustomerName(customerChallengeRequest.getCustomerName());
			customerChallenge.setContactName(customerChallengeRequest.getContactName());
			customerChallenge.setLeadId(customerChallengeRequest.getLeadId());
			customerChallenge.setLeadName(customerChallengeRequest.getLeadName());
			customerChallenge.setLogDate(customerChallengeRequest.getLogDate());
			customerChallenge.setPriority(customerChallengeRequest.getPriority());
			customerChallenge.setOrigin(customerChallengeRequest.getOrigin());
			customerChallenge.setReason(customerChallengeRequest.getReason());
			customerChallenge.setDueDate(customerChallengeRequest.getDueDate());
			customerChallenge.setCloseddate(customerChallengeRequest.getCloseddate());
			customerChallenge.setIncharge(customerChallengeRequest.getIncharge());
			customerChallenge.setCcMailId(customerChallengeRequest.getCcMailId());
			customerChallenge.setStatus(customerChallengeRequest.getStatus());
			customerChallenge.setSubject(customerChallengeRequest.getSubject());
			customerChallenge.setNotes(customerChallengeRequest.getNotes());
			customerChallenge.setDescription(customerChallengeRequest.getDescription());
			customerChallenge.setInternalNote(customerChallengeRequest.getInternalNote());
			customerChallenge.setCustomerFeedback(customerChallengeRequest.getCustomerFeedback());
			customerChallenge.setCreatedBy(customerChallengeRequest.getCreatedBy());
			customerChallenge.setCreatedDate(customerChallengeRequest.getCreatedDate());
			customerChallenge.setCreatedTime(customerChallengeRequest.getCreatedTime());
			customerChallenge.setModifyBy(customerChallengeRequest.getModifyBy());
			customerChallenge.setModifyDate(customerChallengeRequest.getModifyDate());
			customerChallenge.setModifyTime(customerChallengeRequest.getModifyTime());


		}
		return customerChallenge;

	}
	public List<CustomerChallengeResponse> customerChallengeList(long id)
	{
		logger.debug("Entering into customerList"+id);
		List<CustomerChallenge> customerChallengeList=customerChallengeDAO.customerChallengeList(id);
		return map(customerChallengeList);

	}

	private List<CustomerChallengeResponse> map(List<CustomerChallenge> customerChallengeList)
	{
		List<CustomerChallengeResponse> customerChallengeResponse=Collections.emptyList();
		if(customerChallengeList!= null && !customerChallengeList.isEmpty())
		{
			customerChallengeResponse=new ArrayList<CustomerChallengeResponse>();
			for(CustomerChallenge customerChallenge: customerChallengeList)
			{
				CustomerChallengeResponse custChallengeResponseList=map(customerChallenge);
				customerChallengeResponse.add(custChallengeResponseList);
			}
			customerChallengeList.clear();
		}
		return customerChallengeResponse;

	}
	private CustomerChallengeResponse map(CustomerChallenge customerChallenge)
	{
		CustomerChallengeResponse customerResponse=new CustomerChallengeResponse();
		if(customerChallenge!=null)
		{
			customerResponse.setCustomerId(customerChallenge.getCustomerId());
			customerResponse.setContactId(customerChallenge.getContactId());
			customerResponse.setCustomerName(customerChallenge.getCustomerName());
			customerResponse.setContactName(customerChallenge.getContactName());
			customerResponse.setLeadId(customerChallenge.getLeadId());
			customerResponse.setLeadName(customerChallenge.getLeadName());
			customerResponse.setLogDate(customerChallenge.getLogDate());
			customerResponse.setPriority(customerChallenge.getPriority());
			customerResponse.setOrigin(customerChallenge.getOrigin());
			customerResponse.setReason(customerChallenge.getReason());
			customerResponse.setDueDate(customerChallenge.getDueDate());
			customerResponse.setCloseddate(customerChallenge.getCloseddate());
			customerResponse.setIncharge(customerChallenge.getIncharge());
			customerResponse.setCcMailId(customerChallenge.getCcMailId());
			customerResponse.setStatus(customerChallenge.getStatus());
			customerResponse.setSubject(customerChallenge.getSubject());
			customerResponse.setNotes(customerChallenge.getNotes());
			customerResponse.setDescription(customerChallenge.getDescription());
			customerResponse.setInternalNote(customerChallenge.getInternalNote());
			customerResponse.setCustomerFeedback(customerChallenge.getCustomerFeedback());
			customerResponse.setCreatedBy(customerChallenge.getCreatedBy());
			customerResponse.setCreatedDate(customerChallenge.getCreatedDate());
			customerResponse.setCreatedTime(customerChallenge.getCreatedTime());
			customerResponse.setModifyBy(customerChallenge.getModifyBy());
			customerResponse.setModifyDate(customerChallenge.getModifyDate());
			customerResponse.setModifyTime(customerChallenge.getModifyTime());

			
		}
		return customerResponse;

	}
	public int removeCustomerChallenge(long id,String userId)
	{
		logger.debug("Entering into Remove List" +id);
		return customerChallengeDAO.removeCustomerChallenge(id,userId);

	}

}
