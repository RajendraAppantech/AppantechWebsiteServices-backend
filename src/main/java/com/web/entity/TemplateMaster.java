package com.web.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "template_master")
public class TemplateMaster {
	@Id
	@Column(name = "template_id", nullable = false, length = 100)
	private String templateId;
	@Column(name = "template_name", nullable = false, length = 100)
	private String templateName;
	@Column(name = "template_type", nullable = false, length = 50)
	private String templateType;
	@Column(name = "sendor_id", nullable = false, length = 30)
	private String sendorId;
	@Column(name = "customer_code", nullable = false, length = 10)
	private String customerCode;
	@Column(name = "customer_name", nullable = false, length = 100)
	private String customerName;
	@Column(name = "entity_id", nullable = false, length = 50)
	private String entityId;
	@Column(name = "status", length = 10)
	private String status;
	@Column(name = "sms_content", nullable = false, length = 1024)
	private String smsContent;
	@Column(name = "sms_desc", length = 1024)
	private String smsDesc;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDate;
	@CreatedBy
	@Column(name = "created_by", length = 100)
	private String createdBy;

	@CreatedBy
	@Column(name = "template_key", length = 100)
	private String templateKey;

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateType() {
		return this.templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getSendorId() {
		return this.sendorId;
	}

	public void setSendorId(String sendorId) {
		this.sendorId = sendorId;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSmsContent() {
		return this.smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsDesc() {
		return this.smsDesc;
	}

	public void setSmsDesc(String smsDesc) {
		this.smsDesc = smsDesc;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}
}
