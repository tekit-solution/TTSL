package com.cpw.jdbc.model;

public class Sail_schedule {
	private long Schedule_id;
	private String origin_Location;
	private String port_Of_Loading;
	private String terminal;
	private String port_Of_Discharge;
	private String sector;
	private String service;
	private String vessel;
	private String voy;
	private long from_Eta;
	private long from_Etd;
	private String cfs_Cut_off;
	private String time;
	private String last_Stuffing;
	private String day;
	private String transit;
	private String remark;
	private String m_Vessel;
	private String voyy;
	private long to_Eta;
	private long to_Etd;
	private String importExport;
	public long getSchedule_id() {
		return Schedule_id;
	}
	public void setSchedule_id(long schedule_id) {
		Schedule_id = schedule_id;
	}
	public String getOrigin_Location() {
		return origin_Location;
	}
	public void setOrigin_Location(String origin_Location) {
		this.origin_Location = origin_Location;
	}
	public String getPort_Of_Loading() {
		return port_Of_Loading;
	}
	public void setPort_Of_Loading(String port_Of_Loading) {
		this.port_Of_Loading = port_Of_Loading;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getPort_Of_Discharge() {
		return port_Of_Discharge;
	}
	public void setPort_Of_Discharge(String port_Of_Discharge) {
		this.port_Of_Discharge = port_Of_Discharge;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}
	public String getVoy() {
		return voy;
	}
	public void setVoy(String voy) {
		this.voy = voy;
	}
	public long getFrom_Eta() {
		return from_Eta;
	}
	public void setFrom_Eta(long from_Eta) {
		this.from_Eta = from_Eta;
	}
	public long getFrom_Etd() {
		return from_Etd;
	}
	public void setFrom_Etd(long from_Etd) {
		this.from_Etd = from_Etd;
	}
	public String getCfs_Cut_off() {
		return cfs_Cut_off;
	}
	public void setCfs_Cut_off(String cfs_Cut_off) {
		this.cfs_Cut_off = cfs_Cut_off;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLast_Stuffing() {
		return last_Stuffing;
	}
	public void setLast_Stuffing(String last_Stuffing) {
		this.last_Stuffing = last_Stuffing;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTransit() {
		return transit;
	}
	public void setTransit(String transit) {
		this.transit = transit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getM_Vessel() {
		return m_Vessel;
	}
	public void setM_Vessel(String m_Vessel) {
		this.m_Vessel = m_Vessel;
	}
	public String getVoyy() {
		return voyy;
	}
	public void setVoyy(String voyy) {
		this.voyy = voyy;
	}
	public long getTo_Eta() {
		return to_Eta;
	}
	public void setTo_Eta(long to_Eta) {
		this.to_Eta = to_Eta;
	}
	public long getTo_Etd() {
		return to_Etd;
	}
	public void setTo_Etd(long to_Etd) {
		this.to_Etd = to_Etd;
	}
	public String getImportExport() {
		return importExport;
	}
	public void setImportExport(String importExport) {
		this.importExport = importExport;
	}
	@Override
	public String toString() {
		return "Sail_schedule [Schedule_id=" + Schedule_id + ", origin_Location=" + origin_Location
				+ ", port_Of_Loading=" + port_Of_Loading + ", terminal=" + terminal + ", port_Of_Discharge="
				+ port_Of_Discharge + ", sector=" + sector + ", service=" + service + ", vessel=" + vessel + ", voy="
				+ voy + ", from_Eta=" + from_Eta + ", from_Etd=" + from_Etd + ", cfs_Cut_off=" + cfs_Cut_off + ", time="
				+ time + ", last_Stuffing=" + last_Stuffing + ", day=" + day + ", transit=" + transit + ", remark="
				+ remark + ", m_Vessel=" + m_Vessel + ", voyy=" + voyy + ", to_Eta=" + to_Eta + ", to_Etd=" + to_Etd
				+ ", importExport=" + importExport + "]";
	}
	

}
