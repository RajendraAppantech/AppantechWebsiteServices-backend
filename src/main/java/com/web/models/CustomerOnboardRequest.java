package com.web.models;

import jakarta.validation.constraints.NotBlank;

public class CustomerOnboardRequest {

	@NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @NotBlank(message = "SPOC name cannot be blank")
    private String spocName;

    @NotBlank(message = "SPOC contact number cannot be blank")
    private String spocContactNo;

    @NotBlank(message = "Email ID cannot be blank")
    private String emailId;

    @NotBlank(message = "Address1 cannot be blank")
    private String address1;

    @NotBlank(message = "Address2 cannot be blank")
    private String address2;

    @NotBlank(message = "Area cannot be blank")
    private String area;

    @NotBlank(message = "Pincode cannot be blank")
    private String pincode;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Entity ID cannot be blank")
    private String entityId;

    @NotBlank(message = "Sender ID cannot be blank")
    private String sendorId;

    @NotBlank(message = "Template ID cannot be blank")
    private String templateId;

    @NotBlank(message = "Template body cannot be blank")
    private String tempateBody;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSpocName() {
		return spocName;
	}

	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}

	public String getSpocContactNo() {
		return spocContactNo;
	}

	public void setSpocContactNo(String spocContactNo) {
		this.spocContactNo = spocContactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getSendorId() {
		return sendorId;
	}

	public void setSendorId(String sendorId) {
		this.sendorId = sendorId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTempateBody() {
		return tempateBody;
	}

	public void setTempateBody(String tempateBody) {
		this.tempateBody = tempateBody;
	}

	@Override
	public String toString() {
		return "CustomerOnboardRequest [companyName=" + companyName + ", spocName=" + spocName + ", spocContactNo="
				+ spocContactNo + ", emailId=" + emailId + ", address1=" + address1 + ", address2=" + address2
				+ ", area=" + area + ", pincode=" + pincode + ", city=" + city + ", state=" + state + ", country="
				+ country + ", entityId=" + entityId + ", sendorId=" + sendorId + ", templateId=" + templateId
				+ ", tempateBody=" + tempateBody + "]";
	}
}
