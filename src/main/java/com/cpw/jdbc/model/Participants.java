package com.cpw.jdbc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participants {
	
	private long eventId;
	private long participant;
	private String type;
	
	@JsonCreator
	public Participants(@JsonProperty("eventId")long eventId,
			@JsonProperty("participant")long participant,@JsonProperty("type")String type)
	{
	this.eventId=eventId;
	this.participant=participant;
	this.type=type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getEventId() {
		return eventId;
	}


	public void setEventId(long eventId) {
		this.eventId = eventId;
	}


	public long getParticipant() {
		return participant;
	}


	public void setParticipant(long participant) {
		this.participant = participant;
	}


	
	
	
	


	@Override
	public String toString() {
		return "Participants [eventId=" + eventId + ", participant=" + participant + ", type=" + type + "]";
	}

	public Participants() {
		// TODO Auto-generated constructor stub
	}
	
}
