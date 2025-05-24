package com.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(
   name = "vendor_master"
)
@Table(
   name = "vendor_master"
)
@Audited
@EntityListeners({AuditingEntityListener.class})
public class VendorMaster {
   @Id
   @Column(
      name = "vendor_id",
      nullable = false,
      length = 10
   )
   private String vendorId;
   @Column(
      name = "vendor_code",
      nullable = false,
      length = 10
   )
   private String vendorCode;
   @Column(
      name = "vendor_name",
      nullable = false,
      length = 200
   )
   private String vendorName;
   @Column(
      name = "sms_type",
      nullable = false,
      length = 50
   )
   private String smsType;
   @Column(
      name = "vendor_address",
      length = 500
   )
   private String vendorAddress;
   @Column(
      name = "spoc_name",
      nullable = false,
      length = 100
   )
   private String spocName;
   @Column(
      name = "spoc_contact_no",
      nullable = false,
      length = 13
   )
   private String spocContactNo;
   @Column(
      name = "bank_name",
      nullable = false,
      length = 100
   )
   private String bankName;
   @Column(
      name = "account_no",
      nullable = false,
      length = 35
   )
   private String accountNo;
   @Column(
      name = "ifsc_code",
      nullable = false,
      length = 11
   )
   private String ifscCode;
   @Column(
      name = "rate",
      precision = 16,
      scale = 2
   )
   private BigDecimal rate;
   @Column(
      name = "rate_date"
   )
   @Temporal(TemporalType.TIMESTAMP)
   private Date rateDate;
   @Column(
      name = "sms_balance"
   )
   private Long smsBalance;
   @Column(
      name = "status",
      length = 10
   )
   private String status;
   @Column(
      name = "last_txn_update_by",
      length = 100
   )
   private String lastTxnUpdateBy;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "last_txn_update_date"
   )
   private Date lastTxnUpdateDate;
   @CreatedBy
   @Column(
      name = "created_by",
      length = 100
   )
   private String createdBy;
   @CreatedDate
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "created_dt"
   )
   private Date createdDt;
   @LastModifiedBy
   @Column(
      name = "modify_by",
      length = 100
   )
   private String modifyBy;
   @LastModifiedDate
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "modify_dt"
   )
   private Date modifyDt;
   @Column(
      name = "auth_by",
      length = 100
   )
   private String authBy;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "auth_dt"
   )
   private Date authDt;
   @Column(
      name = "auth_status",
      length = 10
   )
   private String authStatus;

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

   public String getSmsType() {
      return this.smsType;
   }

   public void setSmsType(String smsType) {
      this.smsType = smsType;
   }

   public String getVendorAddress() {
      return this.vendorAddress;
   }

   public void setVendorAddress(String vendorAddress) {
      this.vendorAddress = vendorAddress;
   }

   public String getSpocName() {
      return this.spocName;
   }

   public void setSpocName(String spocName) {
      this.spocName = spocName;
   }

   public String getSpocContactNo() {
      return this.spocContactNo;
   }

   public void setSpocContactNo(String spocContactNo) {
      this.spocContactNo = spocContactNo;
   }

   public String getBankName() {
      return this.bankName;
   }

   public void setBankName(String bankName) {
      this.bankName = bankName;
   }

   public String getAccountNo() {
      return this.accountNo;
   }

   public void setAccountNo(String accountNo) {
      this.accountNo = accountNo;
   }

   public String getIfscCode() {
      return this.ifscCode;
   }

   public void setIfscCode(String ifscCode) {
      this.ifscCode = ifscCode;
   }

   public BigDecimal getRate() {
      return this.rate;
   }

   public void setRate(BigDecimal rate) {
      this.rate = rate;
   }

   public Date getRateDate() {
      return this.rateDate;
   }

   public void setRateDate(Date rateDate) {
      this.rateDate = rateDate;
   }

   public Long getSmsBalance() {
      return this.smsBalance;
   }

   public void setSmsBalance(Long smsBalance) {
      this.smsBalance = smsBalance;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Date getCreatedDt() {
      return this.createdDt;
   }

   public void setCreatedDt(Date createdDt) {
      this.createdDt = createdDt;
   }

   public String getVendorCode() {
      return this.vendorCode;
   }

   public void setVendorCode(String vendorCode) {
      this.vendorCode = vendorCode;
   }

   public String getModifyBy() {
      return this.modifyBy;
   }

   public void setModifyBy(String modifyBy) {
      this.modifyBy = modifyBy;
   }

   public Date getModifyDt() {
      return this.modifyDt;
   }

   public void setModifyDt(Date modifyDt) {
      this.modifyDt = modifyDt;
   }

   public String getAuthBy() {
      return this.authBy;
   }

   public void setAuthBy(String authBy) {
      this.authBy = authBy;
   }

   public Date getAuthDt() {
      return this.authDt;
   }

   public void setAuthDt(Date authDt) {
      this.authDt = authDt;
   }

   public String getAuthStatus() {
      return this.authStatus;
   }

   public void setAuthStatus(String authStatus) {
      this.authStatus = authStatus;
   }

   public Date getLastTxnUpdateDate() {
      return this.lastTxnUpdateDate;
   }

   public void setLastTxnUpdateDate(Date lastTxnUpdateDate) {
      this.lastTxnUpdateDate = lastTxnUpdateDate;
   }

   public String getLastTxnUpdateBy() {
      return this.lastTxnUpdateBy;
   }

   public void setLastTxnUpdateBy(String lastTxnUpdateBy) {
      this.lastTxnUpdateBy = lastTxnUpdateBy;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }
}
