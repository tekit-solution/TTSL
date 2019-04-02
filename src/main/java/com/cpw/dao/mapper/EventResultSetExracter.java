package com.cpw.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.cpw.jdbc.model.Event;
import com.cpw.jdbc.model.Participants;

public class EventResultSetExracter implements ResultSetExtractor<Event>{

	@Override
	public Event extractData(ResultSet rs) throws SQLException, DataAccessException {
		final Event event=new Event();
		event.setEventId(rs.getLong("EVENT_ID"));
		event.setTitle(rs.getString("TITLE"));
		event.setLocation(rs.getString("LOCATION"));
		event.setAllDay(rs.getBoolean("ALL_DAY"));
		event.setFromDate(rs.getLong("FROM_DATE"));
		event.setToDate(rs.getLong("TO_DATE"));
		event.setHost(rs.getString("HOST"));
		event.setNoOfParticipants(rs.getInt("NO_OF_PARTICIPANTS"));
		event.setContact(rs.getString("CONTACT"));
		event.setAccount(rs.getString("ACCOUNT"));
		event.setRepeat(rs.getString("REPEAT"));
		event.setDescription(rs.getString("DESCRIPTION"));
		event.setReminder(rs.getString("REMINDER"));
		event.setCreatedBy(rs.getString("CREATED_BY"));
		event.setCreatedTime(rs.getLong("CREATED_TIME"));
		event.setModifyBy(rs.getString("MODIFY_BY"));
		event.setModifyTime(rs.getLong("MODIFY_TIME"));
		event.setNotes(rs.getString("NOTES"));
		event.setAttachments(rs.getString("ATTACHMENTS"));
		event.setContactId(rs.getLong("CONTACT_ID"));
		event.setAccountId(rs.getLong("ACCOUNT_ID"));
		event.setFromTime(rs.getLong("FROM_TIME"));
		event.setToTime(rs.getLong("TO_TIME"));
		event.setLeadId(rs.getLong("LEAD_ID"));
		event.setLeadName(rs.getString("LEAD_NAME"));
		List<Participants> participant=event.getParticipants();
		if(participant==null){
			participant=new ArrayList<Participants>();
			
		}
		Participants parti=new Participants();
		parti.setParticipant(rs.getLong("PARTICIPANT"));
		parti.setEventId(rs.getLong("EVENT_ID"));
		participant.add(parti);
		event.setParticipants(participant);
		return event;
	}

}
