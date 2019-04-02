package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.jdbc.model.Event;

public interface EventDAO {
	public void setDataSource(DataSource ds);

	public int upsertEvent(Event event,String userId);

	public List<Event> eventList(long eventId);
	
	//public List<Participants> participantList(long eventId);

	public int removeEvent(long eventId,String userId);
	
	public int removeParticipant(long eventId);
	



}
