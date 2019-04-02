package com.cpw.model;

import java.util.Date;

public class PdfCreationResponse {
	private String shipper;
	private String multi_trans_regNo;
	private String multi_trans_docNo;
	private String shipperRefrence;
	private String consignee;
	private String branchAddrerss;
	private String notifyParty;
	private String placeOfReciept;
	private String portOfLoading;
	private String freightPayableAt;
	private String vessel;
	private String portOfDischarge;
	private String placeOfDelivery;
	private String noOfOriginalBill;
	private String descriptionOfGoods;
	private double grossWeight;
	private double measurement;
	private Date shippedOnBoard;
	private String locationMaster;
	private Date currentDate;
	private String forTTSL;
	private String shipperAddress;
	private String consigneeAddress;
	private String fristNotifyAddrerss;
	private String markAndNumber;
	private String containerNo;
	private String sB_No;
	
	
	
	public String getMarkAndNumber() {
		return markAndNumber;
	}
	public void setMarkAndNumber(String markAndNumber) {
		this.markAndNumber = markAndNumber;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getsB_No() {
		return sB_No;
	}
	public void setsB_No(String sB_No) {
		this.sB_No = sB_No;
	}
	public String getShipperAddress() {
		return shipperAddress;
	}
	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public String getFristNotifyAddrerss() {
		return fristNotifyAddrerss;
	}
	public void setFristNotifyAddrerss(String fristNotifyAddrerss) {
		this.fristNotifyAddrerss = fristNotifyAddrerss;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getMulti_trans_regNo() {
		return multi_trans_regNo;
	}
	public void setMulti_trans_regNo(String multi_trans_regNo) {
		this.multi_trans_regNo = multi_trans_regNo;
	}
	public String getMulti_trans_docNo() {
		return multi_trans_docNo;
	}
	public void setMulti_trans_docNo(String multi_trans_docNo) {
		this.multi_trans_docNo = multi_trans_docNo;
	}
	public String getShipperRefrence() {
		return shipperRefrence;
	}
	public void setShipperRefrence(String shipperRefrence) {
		this.shipperRefrence = shipperRefrence;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getBranchAddrerss() {
		return branchAddrerss;
	}
	public void setBranchAddrerss(String branchAddrerss) {
		this.branchAddrerss = branchAddrerss;
	}
	public String getNotifyParty() {
		return notifyParty;
	}
	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}
	public String getPlaceOfReciept() {
		return placeOfReciept;
	}
	public void setPlaceOfReciept(String placeOfReciept) {
		this.placeOfReciept = placeOfReciept;
	}
	public String getPortOfLoading() {
		return portOfLoading;
	}
	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}
	public String getFreightPayableAt() {
		return freightPayableAt;
	}
	public void setFreightPayableAt(String freightPayableAt) {
		this.freightPayableAt = freightPayableAt;
	}
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public String getPlaceOfDelivery() {
		return placeOfDelivery;
	}
	public void setPlaceOfDelivery(String placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}
	public String getNoOfOriginalBill() {
		return noOfOriginalBill;
	}
	public void setNoOfOriginalBill(String noOfOriginalBill) {
		this.noOfOriginalBill = noOfOriginalBill;
	}
	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}
	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}
	public double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public double getMeasurement() {
		return measurement;
	}
	public void setMeasurement(double measurement) {
		this.measurement = measurement;
	}
	public Date getShippedOnBoard() {
		return shippedOnBoard;
	}
	public void setShippedOnBoard(Date shippedOnBoard) {
		this.shippedOnBoard = shippedOnBoard;
	}
	public String getLocationMaster() {
		return locationMaster;
	}
	public void setLocationMaster(String locationMaster) {
		this.locationMaster = locationMaster;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getForTTSL() {
		return forTTSL;
	}
	public void setForTTSL(String forTTSL) {
		this.forTTSL = forTTSL;
	}
	@Override
	public String toString() {
		return "PdfCreationResponse [shipper=" + shipper + ", multi_trans_regNo=" + multi_trans_regNo
				+ ", multi_trans_docNo=" + multi_trans_docNo + ", shipperRefrence=" + shipperRefrence + ", consignee="
				+ consignee + ", branchAddrerss=" + branchAddrerss + ", notifyParty=" + notifyParty
				+ ", placeOfReciept=" + placeOfReciept + ", portOfLoading=" + portOfLoading + ", freightPayableAt="
				+ freightPayableAt + ", vessel=" + vessel + ", portOfDischarge=" + portOfDischarge
				+ ", placeOfDelivery=" + placeOfDelivery + ", noOfOriginalBill=" + noOfOriginalBill
				+ ", descriptionOfGoods=" + descriptionOfGoods + ", grossWeight=" + grossWeight + ", measurement="
				+ measurement + ", shippedOnBoard=" + shippedOnBoard + ", locationMaster=" + locationMaster
				+ ", currentDate=" + currentDate + ", forTTSL=" + forTTSL + ", shipperAddress=" + shipperAddress
				+ ", consigneeAddress=" + consigneeAddress + ", fristNotifyAddrerss=" + fristNotifyAddrerss
				+ ", markAndNumber=" + markAndNumber + ", containerNo=" + containerNo + ", sB_No=" + sB_No + "]";
	}

	
	

}
