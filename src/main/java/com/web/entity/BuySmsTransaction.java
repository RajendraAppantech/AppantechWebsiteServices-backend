package com.web.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "buy_sms_transaction")
public class BuySmsTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@Column(name = "vendor_id", length = 10)
	private String vendorId;
	@Column(name = "vendor_name", length = 200)
	private String vendorName;
	@Column(name = "txn_type", nullable = false, length = 50)
	private String txnType;
	@Column(name = "rrn", nullable = false, length = 50)
	private String rrn;
	@Column(name = "amount", precision = 16, scale = 2)
	private BigDecimal amount;
	@Column(name = "rate", precision = 16, scale = 2)
	private BigDecimal rate;
	@Column(name = "sms", nullable = false)
	private Long sms;
	@Column(name = "vendor_old_sms")
	private Long vendorOldSms;
	@Column(name = "vendor_new_sms")
	private Long vendorNewSms;
	@Column(name = "customer_id", length = 10)
	private String customerId;
	@Column(name = "customer_old_sms")
	private Long customerOldSms;
	@Column(name = "customer_new_sms")
	private Long customerNewSms;
	@Column(name = "status", length = 10)
	private String status;
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_code", insertable = false, updatable = false)
	private CustomerDetails customerDetails;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getTxnType() {
		return this.txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getRrn() {
		return this.rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getSms() {
		return this.sms;
	}

	public void setSms(Long sms) {
		this.sms = sms;
	}

	public Long getVendorOldSms() {
		return this.vendorOldSms;
	}

	public void setVendorOldSms(Long vendorOldSms) {
		this.vendorOldSms = vendorOldSms;
	}

	public Long getVendorNewSms() {
		return this.vendorNewSms;
	}

	public void setVendorNewSms(Long vendorNewSms) {
		this.vendorNewSms = vendorNewSms;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerOldSms() {
		return this.customerOldSms;
	}

	public void setCustomerOldSms(Long customerOldSms) {
		this.customerOldSms = customerOldSms;
	}

	public Long getCustomerNewSms() {
		return this.customerNewSms;
	}

	public void setCustomerNewSms(Long customerNewSms) {
		this.customerNewSms = customerNewSms;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerDetails getCustomerDetails() {
		return this.customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}
}
