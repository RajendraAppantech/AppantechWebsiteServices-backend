package com.web.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_sendorid")
public class MasterSendorid {
	@Id
	@Column(name = "sendor_id", length = 30, nullable = false)
	private String sendorId;
	@Column(name = "customer_code", length = 10, nullable = false)
	private String customerCode;
	@Column(name = "customer_name", length = 200)
	private String customerName;
	@Column(name = "entity_id", length = 100)
	private String entityId;
	@Column(name = "txn_type", length = 50)
	private String txnType;
	@Column(name = "created_date", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date createdDate;
	@Column(name = "status", length = 20)
	private String status;
	@Column(name = "created_by", length = 100)
	private String createdBy;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_code", referencedColumnName = "customer_code", insertable = false, updatable = false)
	private CustomerDetails customerDetails;
	@Column(name = "sendor_name", length = 100)
	private String sendorName;

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

	public String getTxnType() {
		return this.txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public CustomerDetails getCustomerDetails() {
		return this.customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getSendorName() {
		return this.sendorName;
	}

	public void setSendorName(String sendorName) {
		this.sendorName = sendorName;
	}
}
