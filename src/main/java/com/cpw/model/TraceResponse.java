package com.cpw.model;

public class TraceResponse {
	private String jobId;
	private String sobDate;
	private String containerNumber;
	private String volume;
	private String bookingDate;
	private String customerClearanceDate;
	private String cartingDate;
	private String createDate;
	private String grWt;
	private String bookingNumber;
	private String shippingBillNumber;

	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getSobDate() {
		return sobDate;
	}
	public void setSobDate(String sobDate) {
		this.sobDate = sobDate;
	}
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getCustomerClearanceDate() {
		return customerClearanceDate;
	}
	public void setCustomerClearanceDate(String customerClearanceDate) {
		this.customerClearanceDate = customerClearanceDate;
	}
	public String getCartingDate() {
		return cartingDate;
	}
	public void setCartingDate(String cartingDate) {
		this.cartingDate = cartingDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getGrWt() {
		return grWt;
	}
	public void setGrWt(String grWt) {
		this.grWt = grWt;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getShippingBillNumber() {
		return shippingBillNumber;
	}
	public void setShippingBillNumber(String shippingBillNumber) {
		this.shippingBillNumber = shippingBillNumber;
	}
}

class HeaderResponse
{
	private String polCode;
	private String podCode;
	private String etd;
	private String eta;
	private String currentStatus;
	public String getPolCode() {
		return polCode;
	}
	public void setPolCode(String polCode) {
		this.polCode = polCode;
	}
	public String getPodCode() {
		return podCode;
	}
	public void setPodCode(String podCode) {
		this.podCode = podCode;
	}
	public String getEtd() {
		return etd;
	}
	public void setEtd(String etd) {
		this.etd = etd;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@Override
	public String toString() {
		return "HeaderResponse [polCode=" + polCode + ", podCode=" + podCode + ", etd=" + etd + ", eta=" + eta
				+ ", currentStatus=" + currentStatus + "]";
	}
	
	
	
}


