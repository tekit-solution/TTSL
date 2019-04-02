package com.cpw.model;

public class ParticipationResponse {

	private long eventId;
	private long participant;
	private String type;
	
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
	
	

}
