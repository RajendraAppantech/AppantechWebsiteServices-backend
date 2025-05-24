package com.web.models;
public class EmailRequest {

    private SmtpDetails smtpDetails;
    private String sendFrom;
    private String sendTo;
    private String sendName;
    private String sendCc;
    private String subject;
    private String isAttachment; // Use boolean if you prefer true/false
    private String bodyType;
    private String dataBody;

    // Getters and Setters

    public SmtpDetails getSmtpDetails() {
        return smtpDetails;
    }

    public void setSmtpDetails(SmtpDetails smtpDetails) {
        this.smtpDetails = smtpDetails;
    }

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

    public String getIsAttachment() {
        return isAttachment;
    }

    public void setIsAttachment(String isAttachment) {
        this.isAttachment = isAttachment;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDataBody() {
        return dataBody;
    }

    public void setDataBody(String dataBody) {
        this.dataBody = dataBody;
    }
}
