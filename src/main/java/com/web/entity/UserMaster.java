package com.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
   name = "user_master"
)
@Audited
@EntityListeners({AuditingEntityListener.class})
public class UserMaster {
   @Id
   @Column(
      name = "user_id",
      length = 50,
      nullable = false
   )
   private String userId;
   @Column(
      name = "name",
      length = 200
   )
   private String name;
   @Column(
      name = "mobile_no",
      length = 13
   )
   private String mobileNo;
   @Column(
      name = "email_id",
      length = 30
   )
   private String emailId;
   @Column(
      name = "user_role"
   )
   private String userRole;
   @Column(
      name = "user_profile"
   )
   private String userProfile;
   @Column(
      name = "status",
      length = 10
   )
   private String status;
   @Column(
      name = "user_code"
   )
   private String userCode;
   @Column(
      name = "passwd",
      length = 64
   )
   private String passwd;
   @Column(
      name = "passwd_exp"
   )
   private Date passwdExp;
   @Column(
      name = "last_login_dt"
   )
   private Date lastLoginDt;
   @Column(
      name = "login_attempt"
   )
   private Integer loginAttempt;
   @Column(
      name = "lock_time"
   )
   private Date lockTime;
   @Column(
      name = "created_by",
      length = 100
   )
   private String createdBy;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "created_dt"
   )
   private Date createdDt;
   @Column(
      name = "modify_by",
      length = 100
   )
   private String modifyBy;
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
   @Column(
      name = "logout_status",
      length = 10
   )
   private String logoutStatus;
   @Temporal(TemporalType.TIMESTAMP)
   @Column(
      name = "last_logout_date"
   )
   private Date lastLogoutDate;
   @Column(
      name = "user_menu",
      length = 100
   )
   private String userMenu;

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getMobileNo() {
      return this.mobileNo;
   }

   public void setMobileNo(String mobileNo) {
      this.mobileNo = mobileNo;
   }

   public String getEmailId() {
      return this.emailId;
   }

   public void setEmailId(String emailId) {
      this.emailId = emailId;
   }

   public String getUserRole() {
      return this.userRole;
   }

   public void setUserRole(String userRole) {
      this.userRole = userRole;
   }

   public String getUserProfile() {
      return this.userProfile;
   }

   public void setUserProfile(String userProfile) {
      this.userProfile = userProfile;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }

   public String getPasswd() {
      return this.passwd;
   }

   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }

   public Date getPasswdExp() {
      return this.passwdExp;
   }

   public void setPasswdExp(Date passwdExp) {
      this.passwdExp = passwdExp;
   }

   public Date getLastLoginDt() {
      return this.lastLoginDt;
   }

   public void setLastLoginDt(Date lastLoginDt) {
      this.lastLoginDt = lastLoginDt;
   }

   public Integer getLoginAttempt() {
      return this.loginAttempt;
   }

   public void setLoginAttempt(Integer loginAttempt) {
      this.loginAttempt = loginAttempt;
   }

   public Date getLockTime() {
      return this.lockTime;
   }

   public void setLockTime(Date lockTime) {
      this.lockTime = lockTime;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }

   public Date getCreatedDt() {
      return this.createdDt;
   }

   public void setCreatedDt(Date createdDt) {
      this.createdDt = createdDt;
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

   public String getLogoutStatus() {
      return this.logoutStatus;
   }

   public void setLogoutStatus(String logoutStatus) {
      this.logoutStatus = logoutStatus;
   }

   public Date getLastLogoutDate() {
      return this.lastLogoutDate;
   }

   public void setLastLogoutDate(Date lastLogoutDate) {
      this.lastLogoutDate = lastLogoutDate;
   }

   public String getUserMenu() {
      return this.userMenu;
   }

   public void setUserMenu(String userMenu) {
      this.userMenu = userMenu;
   }

   public String toString() {
      String var10000 = this.userId;
      return "UserMaster [userId=" + var10000 + ", name=" + this.name + ", mobileNo=" + this.mobileNo + ", emailId=" + this.emailId + ", userRole=" + this.userRole + ", userProfile=" + this.userProfile + ", status=" + this.status + ", userCode=" + this.userCode + ", passwd=" + this.passwd + ", passwdExp=" + String.valueOf(this.passwdExp) + ", lastLoginDt=" + String.valueOf(this.lastLoginDt) + ", loginAttempt=" + String.valueOf(this.loginAttempt) + ", lockTime=" + String.valueOf(this.lockTime) + ", createdBy=" + this.createdBy + ", createdDt=" + String.valueOf(this.createdDt) + ", modifyBy=" + this.modifyBy + ", modifyDt=" + String.valueOf(this.modifyDt) + ", authBy=" + this.authBy + ", authDt=" + String.valueOf(this.authDt) + ", authStatus=" + this.authStatus + ", logoutStatus=" + this.logoutStatus + ", lastLogoutDate=" + String.valueOf(this.lastLogoutDate) + ", userMenu=" + this.userMenu + "]";
   }
}
