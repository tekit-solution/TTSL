package com.cpw.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.EventDAOImpl;
import com.cpw.jdbc.model.Event;
import com.cpw.model.EventRequest;
import com.cpw.model.EventResponse;

public class EventImpl {
	public EventImpl() {
		// TODO Auto-generated constructor stub
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private final EventDAOImpl eventDao=(EventDAOImpl)context.getBean("eventDAOImpl");

	public int upsertEvent(EventRequest eventRequest,String userId) {
		logger.debug("Entering into Event");
		return eventDao.upsertEvent(map(eventRequest),userId);
	}
	private Event map(EventRequest eventRequest)
	{
		Event event=new Event();

		if(eventRequest!=null)
		{
			event.setEventId(eventRequest.getEventId());
			event.setTitle(eventRequest.getTitle());
			event.setLocation(eventRequest.getLocation());
			event.setAllDay(eventRequest.isAllDay());
			event.setFromDate(eventRequest.getFromDate());
			event.setToDate(eventRequest.getToDate());
			event.setHost(eventRequest.getHost());
			event.setContact(eventRequest.getContact());
			event.setAccount(eventRequest.getAccount());
			event.setRepeat(eventRequest.getRepeat());
			event.setDescription(eventRequest.getDescription());
			event.setReminder(eventRequest.getReminder());
			event.setCreatedBy(eventRequest.getCreatedBy());
			event.setCreatedTime(eventRequest.getCreatedTime());
			event.setModifyBy(eventRequest.getModifyBy());
			event.setModifyTime(eventRequest.getModifyTime() );
			event.setNotes(eventRequest.getNotes());
			event.setAttachments(eventRequest.getAttachments());
			event.setNoOfParticipants(eventRequest.getNoOfParticipants());
			event.setContactId(eventRequest.getContactId());
			event.setAccountId(eventRequest.getAccountId());
			event.setParticipants(eventRequest.getParticipants());
			event.setFromTime(eventRequest.getFromTime());
			event.setToTime(eventRequest.getToTime());
			event.setLeadId(eventRequest.getLeadId());
			event.setLeadName(eventRequest.getLeadName());




		}
		return event;
	}


	public List<EventResponse> eventList(long eventId) {
		logger.info("Entering into Event List");
		final List<Event> eventList = eventDao.eventList(eventId);
		logger.debug("Event List :" +eventList);
		return map(eventList);

	}
	private List<EventResponse> map(List<Event> eventList) {
		List<EventResponse> eventResponseList = Collections.emptyList();
		if (eventList != null && !eventList.isEmpty()) {
			eventResponseList = new ArrayList<EventResponse>();
			for (Event event : eventList) {
				EventResponse trackingResponse = map(event);
				eventResponseList.add(trackingResponse);
			}
			eventList.clear();
		}
		return eventResponseList;

	}
	private EventResponse map(Event event) {
		EventResponse eventResponse = new EventResponse();

		if (event != null) {
			eventResponse.setEventId(event.getEventId());
			eventResponse.setTitle(event.getTitle());
			eventResponse.setLocation(event.getLocation());
			eventResponse.setAllDay(event.isAllDay());
			eventResponse.setFromDate(event.getFromDate());
			eventResponse.setToDate(event.getToDate());
			eventResponse.setHost(event.getHost());
			eventResponse.setContact(event.getContact());
			eventResponse.setAccount(event.getAccount());
			eventResponse.setRepeat(event.getRepeat());
			eventResponse.setDescription(event.getDescription());
			eventResponse.setReminder(event.getReminder());
			eventResponse.setCreatedBy(event.getCreatedBy());
			eventResponse.setCreatedTime(event.getCreatedTime());
			eventResponse.setModifyBy(event.getModifyBy());
			eventResponse.setModifyTime(event.getModifyTime());
			eventResponse.setNotes(event.getNotes());
			eventResponse.setAttachments(event.getAttachments());
			eventResponse.setContactId(event.getContactId());
			eventResponse.setAccountId(event.getAccountId());
			eventResponse.setFromTime(event.getFromDate());
			eventResponse.setToTime(event.getToTime());
			eventResponse.setLeadId(event.getLeadId());
			eventResponse.setLeadName(event.getLeadName());
			eventResponse.setNoOfParticipants(event.getNoOfParticipants());
			eventResponse.setParticipants(event.getParticipants());

		}
		return eventResponse;

	}


	
	public int removeEvent(long eventId,String userId) {
		logger.debug("Entering into Remove Event");
		System.out.println(eventId);
		return eventDao.removeEvent(eventId,userId);
	}

	public int removeParticipant(long eventId){
		logger.debug("entering into remove Participant");
		return eventDao.removeParticipant(eventId);

	}
	public int removeDeatil(long eventId,String userId)
	{
		logger.debug("Entering into remove details");
		EventImpl eventImpl=new EventImpl();
		eventImpl.removeEvent(eventId,userId);
		eventImpl.removeParticipant(eventId);

		return 0;

	}
	public int removeType(long participantId){
		
		return eventDao.removeType(participantId);
		
	}



}







