/**
 * 
 */
package com.cpw.model;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/**
 * @author Unknown
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeadData {
	//private byte[] uploadedInputStream;
	private String uploadedInputStream;
	private long leadId;
	private String leadOwner;
	private String company;
	private String firstName;
	private String lastName;
	private String title;
	private String email;
	private String phone;
	private String fax;
	private String mobile;
	private String website;
	private String leadSource;
	private String leadStatus;
	private String industry;
	private String noOfEmployees;
	private String annualRevenue;
	private String rating;
	private boolean emailOptOut;
	private String skypeId;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private String addressZipCode;
	private String addressCounty;
	private String description;
	private String createdBy;
	@JsonSerialize(using=DateSerializer.class)
	private Date createdDate;
	@JsonSerialize(using=DateSerializer.class)
	private Date modifyDate;
	private String salutation;
	private String secondaryEmailId;
	private String twitter;
	private String modifyBy;
	
	@JsonCreator
	public LeadData(@JsonProperty("uploadedInputStream") String uploadedInputStream, @JsonProperty("leadId") long leadId, @JsonProperty("leadOwner") String leadOwner, @JsonProperty("company") String company,
			@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
			@JsonProperty("title") String title, @JsonProperty("email") String email,
			@JsonProperty("phone") String phone, @JsonProperty("fax") String fax, @JsonProperty("mobile") String mobile,
			@JsonProperty("website") String website, @JsonProperty("leadSource") String leadSource,
			@JsonProperty("leadStatus") String leadStatus, @JsonProperty("industry") String industry,
			@JsonProperty("noOfEmployees") String noOfEmployees, @JsonProperty("annualRevenue") String annualRevenue,
			@JsonProperty("rating") String rating, @JsonProperty("emailOptOut") boolean emailOptOut,
			@JsonProperty("skypeId") String skypeId, @JsonProperty("addressStreet") String addressStreet,
			@JsonProperty("addressCity") String addressCity, @JsonProperty("addressState") String addressState,
			@JsonProperty("addressZipCode") String addressZipCode, @JsonProperty("addressCounty") String addressCounty,
			@JsonProperty("description") String description, @JsonProperty("createdBy") String createdBy,
			@JsonProperty("createdDate") Date createdDate, @JsonProperty("modifyDate") Date modifyDate,
			@JsonProperty("salutation")String salutation,@JsonProperty("secondaryEmailId")String secondaryEmailId,
			@JsonProperty("twitter")String twitter,@JsonProperty("modifyBy")String modifyBy) {
		this.uploadedInputStream = uploadedInputStream;
		this.leadId =leadId;
		this.leadOwner = leadOwner;
		this.company = company;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.mobile = mobile;
		this.website = website;
		this.leadSource = leadSource;
		this.leadStatus = leadStatus;
		this.industry = industry;
		this.noOfEmployees = noOfEmployees;
		this.annualRevenue = annualRevenue;
		this.rating = rating;
		this.emailOptOut = emailOptOut;
		this.skypeId = skypeId;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZipCode = addressZipCode;
		this.addressCounty = addressCounty;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifyDate = modifyDate;
		this.salutation=salutation;
		this.secondaryEmailId=secondaryEmailId;
		this.twitter=twitter;
		this.modifyBy=modifyBy;
	}

	public LeadData() {
		// TODO Auto-generated constructor stub
	}

	
	
	

	public String getUploadedInputStream() {
		return uploadedInputStream;
	}

	public void setUploadedInputStream(String uploadedInputStream) {
		this.uploadedInputStream = uploadedInputStream;
	}

	public long getLeadId() {
		return leadId;
	}

	public void setLeadId(long leadId) {
		this.leadId = leadId;
	}

	public String getLeadOwner() {
		return leadOwner;
	}

	public void setLeadOwner(String leadOwner) {
		this.leadOwner = leadOwner;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public String getAnnualRevenue() {
		return annualRevenue;
	}

	public void setAnnualRevenue(String annualRevenue) {
		this.annualRevenue = annualRevenue;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public boolean isEmailOptOut() {
		return emailOptOut;
	}

	public void setEmailOptOut(boolean emailOptOut) {
		this.emailOptOut = emailOptOut;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZipCode() {
		return addressZipCode;
	}

	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}

	public String getAddressCounty() {
		return addressCounty;
	}

	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Override
	public String toString() {
		return "LeadData [uploadedInputStream=" + uploadedInputStream + ", leadId=" + leadId + ", leadOwner="
				+ leadOwner + ", company=" + company + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", title=" + title + ", email=" + email + ", phone=" + phone + ", fax=" + fax + ", mobile=" + mobile
				+ ", website=" + website + ", leadSource=" + leadSource + ", leadStatus=" + leadStatus + ", industry="
				+ industry + ", noOfEmployees=" + noOfEmployees + ", annualRevenue=" + annualRevenue + ", rating="
				+ rating + ", emailOptOut=" + emailOptOut + ", skypeId=" + skypeId + ", addressStreet=" + addressStreet
				+ ", addressCity=" + addressCity + ", addressState=" + addressState + ", addressZipCode="
				+ addressZipCode + ", addressCounty=" + addressCounty + ", description=" + description + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifyDate=" + modifyDate + ", salutation="
				+ salutation + ", secondaryEmailId=" + secondaryEmailId + ", twitter=" + twitter + ", modifyBy="
				+ modifyBy + "]";
	}

	
	
	
	
}
