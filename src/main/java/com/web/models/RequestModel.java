package com.web.models;

public class RequestModel {

	private String sendFrom;
	private String sendTo;
	private String sendName;
	private String sendCc;
	private String subject;
	private String attachmentFileName;
	private String bodyType;
	private String dataBody;
	private String isAttachment;
	private String isLogo;
	private SmtpDetails smtpDetails;
	public String getSendFrom() {
		return sendFrom;
	}
	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getSendCc() {
		return sendCc;
	}
	public void setSendCc(String sendCc) {
		this.sendCc = sendCc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	
	public String getIsAttachment() {
		return isAttachment;
	}
	public void setIsAttachment(String isAttachment) {
		this.isAttachment = isAttachment;
	}
	public String getIsLogo() {
		return isLogo;
	}
	public void setIsLogo(String isLogo) {
		this.isLogo = isLogo;
	}
	public SmtpDetails getSmtpDetails() {
		return smtpDetails;
	}
	public void setSmtpDetails(SmtpDetails smtpDetails) {
		this.smtpDetails = smtpDetails;
	}
	public String getDataBody() {
		return dataBody;
	}
	public void setDataBody(String dataBody) {
		this.dataBody = dataBody;
	}
	@Override
	public String toString() {
		return "RequestModel [sendFrom=" + sendFrom + ", sendTo=" + sendTo + ", sendName=" + sendName + ", sendCc="
				+ sendCc + ", subject=" + subject + ", attachmentFileName=" + attachmentFileName + ", bodyType="
				+ bodyType + ", dataBody=" + dataBody + ", isAttachment=" + isAttachment + ", isLogo=" + isLogo
				+ ", smtpDetails=" + smtpDetails + "]";
	}
}
