package com.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.entity.BuySmsTransaction;
import com.web.entity.CustomerDetails;
import com.web.entity.EmailLog;
import com.web.entity.MasterSendorid;
import com.web.entity.PincodeMaster;
import com.web.entity.SmsMaster;
import com.web.entity.TemplateMaster;
import com.web.entity.UserMaster;
import com.web.entity.UserMenu;
import com.web.entity.VendorMaster;

@Repository
public class Repositories {

	public interface UserMasterRepository extends JpaRepository<UserMaster, String> {
		UserMaster findByUserId(String userId);
	}

	public interface UserMenuRepository extends JpaRepository<UserMenu, Long> {
		UserMenu findByUserProfile(String userProfile);
	}

	public interface SmsMasterRepository extends JpaRepository<SmsMaster, Long> {
	}

	public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String> {

		CustomerDetails findByName(String name);

		CustomerDetails findByCustomerCode(String customerCode);

		@Query("SELECT max(u.customerCode) FROM customer_details u")
		String getLastCustId();

		List<CustomerDetails> findByAuthStatus(String authStatus);

		@Query("SELECT s FROM customer_details s where s.customerCode =:userCode")
		List<CustomerDetails> findByCustomerCodeWithList(String userCode);

		CustomerDetails findByEntityId(String entityId);

	}

	public interface PincodeMasterRepository extends JpaRepository<PincodeMaster, String> {
		List<PincodeMaster> findByPinCode(String pincode);
	}

	public interface MasterSendoridRepository extends JpaRepository<MasterSendorid, String> {

		boolean existsBySendorIdAndCustomerCode(String sendorId, String customerCode);

		MasterSendorid findBySendorId(String sendorId);

		MasterSendorid findByCustomerCodeAndSendorId(String customerCode, String sendorId);
	}

	public interface TemplateMasterRepository extends JpaRepository<TemplateMaster, String> {
		boolean existsByTemplateIdAndCustomerCode(String templateId, String customerCode);

		boolean existsByTemplateId(String templateId);

		TemplateMaster findByTemplateId(String templateId);

		TemplateMaster findByTemplateIdAndCustomerCode(String templateId, String customerCode);

		@Query("SELECT t FROM TemplateMaster t WHERE t.customerCode = :customerCode ORDER BY t.createdDate DESC")
		List<TemplateMaster> findTemplatesByCustomerCode(@Param("customerCode") String customerCode);

		@Query("SELECT DISTINCT tm.sendorId FROM TemplateMaster tm WHERE tm.customerCode = :customerCode")
		List<String> findDistinctSenderIdsByCustomerCode(@Param("customerCode") String customerCode);

		@Query("SELECT DISTINCT tm.entityId FROM TemplateMaster tm WHERE tm.customerCode = :customerCode AND tm.sendorId = :sendorId")
		List<String> findEntityIdsByCustomerCodeAndSenderId(@Param("customerCode") String customerCode,
				@Param("sendorId") String sendorId);

		@Query("SELECT DISTINCT tm.templateId FROM TemplateMaster tm WHERE tm.customerCode = :customerCode AND tm.sendorId = :sendorId")
		List<String> findTemplateIdsByCustomerCodeAndSenderId(@Param("customerCode") String customerCode,
				@Param("sendorId") String sendorId);

		List<TemplateMaster> findByCustomerCodeAndSendorId(String customerCode, String sendorId);
	}

	public interface VendorMasterRepository extends JpaRepository<VendorMaster, String> {
		@Query("SELECT max(u.vendorId) FROM vendor_master u")
		String getLastVendorId();

		VendorMaster findByVendorId(String vendorId);

		VendorMaster findByVendorCodeAndSmsType(String vendorCode, String smsType);

		List<VendorMaster> findByAuthStatus(String authStatus);
	}

	public interface BuySmsTransactionRepository extends JpaRepository<BuySmsTransaction, Long> {
	}

	public interface EmailLogRepository extends JpaRepository<EmailLog, Integer>{
		}

}
