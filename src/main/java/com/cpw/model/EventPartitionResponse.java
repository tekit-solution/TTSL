package com.cpw.model;

import java.util.List;

public class EventPartitionResponse {
private List<EventResponse> event;
private List<ParticipationResponse> participation;

public EventPartitionResponse(List<EventResponse> event, List<ParticipationResponse> participation) {
	super();
	this.event = event;
	this.participation = participation;
}
public List<EventResponse> getEvent() {
	return event;
}
public void setEvent(List<EventResponse> event) {
	this.event = event;
}
public List<ParticipationResponse> getParticipation() {
	return participation;
}
public void setParticipation(List<ParticipationResponse> participation) {
	this.participation = participation;
}


}
