package com.cpw.model;

public class AgentInfoResponse {
	private String port;
	private String agentAddress;
	private String lclDetail;
	private String lalDetail1;
	private String hbl;
	private String notes;
	
	
	public String getLalDetail1() {
		return lalDetail1;
	}
	public void setLalDetail1(String lalDetail1) {
		this.lalDetail1 = lalDetail1;
	}
	public String getHbl() {
		return hbl;
	}
	public void setHbl(String hbl) {
		this.hbl = hbl;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLclDetail() {
		return lclDetail;
	}
	public void setLclDetail(String lclDetail) {
		this.lclDetail = lclDetail;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAgentAddress() {
		return agentAddress;
	}
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}
	@Override
	public String toString() {
		return "AgentDetail [port=" + port + ", agentAddress=" + agentAddress + ", lclDetail=" + lclDetail
				+ ", lalDetail1=" + lalDetail1 + ", hbl=" + hbl + ", notes=" + notes + "]";
	}
	

}
