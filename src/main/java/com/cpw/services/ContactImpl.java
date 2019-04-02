package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.ContactDAOImpl;
import com.cpw.jdbc.model.Contact;
import com.cpw.model.ContactData;
import com.cpw.model.ContactDataResponse;

public class ContactImpl {
	public ContactImpl() {
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private final ContactDAOImpl contactDAO = (ContactDAOImpl) context.getBean("contactDAOImpl");

	public int upsertContact(ContactData contactRequest,String userId) {
		logger.info("Entering into upsert Contact");
		return contactDAO.upsertContact(map(contactRequest),userId);
	}

	public int removeContact(long contactId,String userId) {
		logger.debug("Entering into Remove Contact" +contactId);
		return contactDAO.removeContact(contactId,userId);
	}

	public List<ContactDataResponse> contactList(long contactId) {
		logger.debug("Entering into contact List" +contactId);
		final List<Contact> contactList = contactDAO.contactList(contactId);
		return map(contactList);

	}

	private List<ContactDataResponse> map(List<Contact> contactList) {
		List<ContactDataResponse> contactResponseList = Collections.emptyList();
		if (contactList != null && !contactList.isEmpty()) {
			contactResponseList = new ArrayList<ContactDataResponse>();
			for (Contact contact : contactList) {
				ContactDataResponse trackingResponse = map(contact);
				contactResponseList.add(trackingResponse);
			}
			contactList.clear();
		}
		return contactResponseList;

	}

	private ContactDataResponse map(Contact contact) {
		ContactDataResponse contactData = new ContactDataResponse();
		if (contact != null) {
			/*contactData.setImage(contact.getImage());*/
			contactData.setMailingAddressCity(contact.getMailingAddressCity());
			contactData.setMailingAddressCountry(contact.getMailingAddressCountry());
			contactData.setMailingAddressState(contact.getMailingAddressState());
			contactData.setMailingAddressStreet(contact.getMailingAddressStreet());
			contactData.setMailingAddressZip(contact.getMailingAddressZip());
			contactData.setOtherAddressCity(contact.getOtherAddressCity());
			contactData.setOtherAddressCountry(contact.getOtherAddressCountry());
			contactData.setOtherAddressState(contact.getOtherAddressState());
			contactData.setOtherAddressStreet(contact.getOtherAddressStreet());
			contactData.setOtherAddressZip(contact.getOtherAddressZip());
			contactData.setTwitter(contact.getTwitter());
			contactData.setDescription(contact.getDescription());
			contactData.setEmail(contact.getEmail());
			contactData.setSecondaryEmail(contact.getSecondaryEmail());
			contactData.setEmailOptOut(contact.isEmailOptOut());
			contactData.setReportsTo(contact.getReportsTo());
			contactData.setFax(contact.getFax());
			contactData.setFirstName(contact.getFirstName());
			contactData.setLastName(contact.getLastName());
			contactData.setContactId(contact.getContactId());
			contactData.setContactOwner(contact.getContactOwner());
			contactData.setLeadSource(contact.getLeadSource());
			contactData.setAssistant(contact.getAssistant());
			contactData.setAsstPhone(contact.getAsstPhone());
			contactData.setMobile(contact.getMobile());
			contactData.setHomePhone(contact.getHomePhone());
			contactData.setOtherPhone(contact.getOtherPhone());
			contactData.setPhone(contact.getPhone());
			contactData.setAccountName(contact.getAccountName());
			contactData.setDepartment(contact.getDepartment());
			contactData.setFax(contact.getFax());
			contactData.setLastActivityTime(contact.getLastActivityTime());
		    contactData.setSkypeId(contact.getSkypeId());
			contactData.setTitle(contact.getTitle());
			contactData.setDateOfBirth(contact.getDateOfBirth());
			contactData.setCreatedDate(contact.getCreatedDate());
			contactData.setModifyDate(contact.getModifyDate());
			contactData.setCreatedBy(contact.getCreatedBy());
			contactData.setAccountId(contact.getAccountId());
			contactData.setSalutation(contact.getSalutation());
			contactData.setParentId(contact.getParentId());
		}
		return contactData;

	}

	private Contact map(ContactData contactRequest) {
		Contact contact = new Contact();
		if (contactRequest != null) {
			
			/*contact.setImage(contactRequest.getImage());*/
			contact.setMailingAddressCity(contactRequest.getMailingAddressCity());
			contact.setMailingAddressCountry(contactRequest.getMailingAddressCountry());
			contact.setMailingAddressState(contactRequest.getMailingAddressState());
			contact.setMailingAddressStreet(contactRequest.getMailingAddressStreet());
			contact.setMailingAddressZip(contactRequest.getMailingAddressZip());
			contact.setOtherAddressCity(contactRequest.getOtherAddressCity());
			contact.setOtherAddressCountry(contactRequest.getOtherAddressCountry());
			contact.setOtherAddressState(contactRequest.getOtherAddressState());
			contact.setOtherAddressStreet(contactRequest.getOtherAddressStreet());
			contact.setOtherAddressZip(contactRequest.getOtherAddressZip());
			contact.setTwitter(contactRequest.getTwitter());
			contact.setDescription(contactRequest.getDescription());
			contact.setEmail(contactRequest.getEmail());
			contact.setSecondaryEmail(contactRequest.getSecondaryEmail());
			contact.setEmailOptOut(contactRequest.isEmailOptOut());
			contact.setReportsTo(contactRequest.getReportsTo());
			contact.setFax(contactRequest.getFax());
			contact.setFirstName(contactRequest.getFirstName());
			contact.setLastName(contactRequest.getLastName());
			contact.setContactId(contactRequest.getContactId());
			contact.setContactOwner(contactRequest.getContactOwner());
			contact.setLeadSource(contactRequest.getLeadSource());
			contact.setAssistant(contactRequest.getAssistant());
			contact.setAsstPhone(contactRequest.getAsstPhone());
			contact.setMobile(contactRequest.getMobile());
			contact.setHomePhone(contactRequest.getHomePhone());
			contact.setOtherPhone(contactRequest.getOtherPhone());
			contact.setPhone(contactRequest.getPhone());
			contact.setAccountName(contactRequest.getAccountName());
			contact.setDepartment(contactRequest.getDepartment());
			contact.setFax(contactRequest.getFax());
			
			contact.setLastActivityTime(contactRequest.getLastActivityTime());
			
			contact.setSkypeId(contactRequest.getSkypeId());
			contact.setTitle(contactRequest.getTitle());
			
			contact.setDateOfBirth(contactRequest.getDateOfBirth());
			
				contact.setCreatedDate(contactRequest.getCreatedDate());
			
				contact.setModifyDate(contactRequest.getModifyDate());
			contact.setCreatedBy(contactRequest.getCreatedBy());
			contact.setAccountId(contactRequest.getAccountId());
			contact.setSalutation(contactRequest.getSalutation());
			contact.setParentId(contactRequest.getParentId());
		}
		return contact;
	}
}
