package com.web.services;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.entity.BuySmsTransaction;
import com.web.entity.CustomerDetails;
import com.web.entity.MasterSendorid;
import com.web.entity.SmsMaster;
import com.web.entity.TemplateMaster;
import com.web.entity.UserMaster;
import com.web.entity.UserMenu;
import com.web.entity.VendorMaster;
import com.web.models.CommonResponse;
import com.web.models.CustomerOnboardRequest;
import com.web.repositories.Repositories.BuySmsTransactionRepository;
import com.web.repositories.Repositories.CustomerDetailsRepository;
import com.web.repositories.Repositories.MasterSendoridRepository;
import com.web.repositories.Repositories.SmsMasterRepository;
import com.web.repositories.Repositories.TemplateMasterRepository;
import com.web.repositories.Repositories.UserMasterRepository;
import com.web.repositories.Repositories.UserMenuRepository;
import com.web.repositories.Repositories.VendorMasterRepository;
import com.web.utils.MyUtils;

@Service
public class OnboardCustomerService {

	@Value("${TOPUP_SMS_COUNT}")
	private String TOPUP_SMS_COUNT;

	@Value("${DEFAULT_DEBIT_VENDOR}")
	private String DEFAULT_DEBIT_VENDOR;

	@Value("${TOPUP_SMS_RATE}")
	private String TOPUP_SMS_RATE;

	@Value("${SMS_KEY}")
	private String smsKey;
	
	@Value("${SMS_FROM}")
	private String smsFrom;
	
	@Value("${USER_CODE}")
	private String userCode;

	@Value("${APPLICATION_LINK}")
	private String APPLICATION_LINK;
	
	@Autowired
	private MyUtils myUtils;

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	private MasterSendoridRepository masterSendoridRepository;

	@Autowired
	private TemplateMasterRepository templateMasterRepository;

	@Autowired
	private VendorMasterRepository vendorMasterRepository;

	@Autowired
	private BuySmsTransactionRepository buySmsTransactionRepository;

	@Autowired
	private UserMasterRepository masterRepository;

	@Autowired
	private UserMenuRepository userMenuRepository;

	@Autowired
	private SmsMasterRepository smsMasterRepository;
	
	@Autowired
	private CompanyEmailLogService companyEmailLogService;

	@Autowired
	private EmailLogService emailLogService;
	
	private static final Logger Logger = LoggerFactory.getLogger(OnboardCustomerService.class);

	public CommonResponse onboardCustomer(CustomerOnboardRequest request) {
		// TODO Auto-generated method stub
		CommonResponse response = new CommonResponse();
		BigDecimal topupAmount = new BigDecimal(TOPUP_SMS_RATE).multiply(new BigDecimal(TOPUP_SMS_COUNT));
		try {

			CustomerDetails existingByEntityId = this.customerDetailsRepository.findByEntityId(request.getEntityId());
			if (existingByEntityId != null) {
				response.setStatus(false);
				response.setMessage("Customer with this entity ID already exists. " + request.getEntityId());
				response.setRespCode("01");
				return response;
			}

			MasterSendorid globalSendorIdCheck = masterSendoridRepository.findBySendorId(request.getSendorId());
			if (globalSendorIdCheck != null) {
				response.setStatus(false);
				response.setMessage("Sendor ID " + request.getSendorId() + " already exists in the system.");
				response.setRespCode("05");
				return response;
			}

			boolean globalTemplateExists = templateMasterRepository.existsByTemplateId(request.getTemplateId());
			if (globalTemplateExists) {
				response.setStatus(false);
				response.setMessage("Template ID already exists in the system.");
				response.setRespCode("04");
				return response;
			}

			// Insert into the customer table
			String custId = "" + myUtils.getNextCustId();
			CustomerDetails ms = new CustomerDetails();
			ms.setCustomerCode(custId);
			ms.setName(request.getCompanyName().toUpperCase());
			ms.setEmailId(request.getEmailId());
			ms.setAddress1(request.getAddress1().toUpperCase());
			ms.setAddress2(request.getAddress2() != null ? request.getAddress2().toUpperCase() : "");
			ms.setAddress3(request.getArea().toUpperCase());
			ms.setCity(request.getCity().toUpperCase());
			ms.setState(request.getState().toUpperCase());
			ms.setPinCode(request.getPincode());
			ms.setFirstContactPersonName(request.getSpocName().toUpperCase());
			ms.setFirstContactPersonMobileNo(request.getSpocContactNo());
			ms.setRate(new BigDecimal(TOPUP_SMS_RATE));
			ms.setRateDate(new Date());
			ms.setAccountNo(" ");
			ms.setIfscCode(" ");
			ms.setBankName(" ");
			ms.setSmsBalance(Long.valueOf(TOPUP_SMS_COUNT));
			ms.setUsername("");
			ms.setEntityId(request.getEntityId());
			ms.setEntityName(request.getCompanyName());
			ms.setEntityStatus("1");
			ms.setEntityDate(new Date());
			ms.setAuthKey(custId.substring(3) + myUtils.generateAlphanumericString(6));
			ms.setStatus("1");
			ms.setAuthStatus("1");
			ms.setCreatedBy("WEBSITE");
			ms.setCreatedDate(new Date());
			customerDetailsRepository.save(ms);

			// Insert into the Sendor id table
			MasterSendorid sendorId = new MasterSendorid();
			sendorId.setSendorId(request.getSendorId());
			sendorId.setSendorName(request.getCompanyName());
			sendorId.setCustomerCode(custId);
			sendorId.setCustomerName(request.getSpocName());
			sendorId.setEntityId(request.getEntityId());
			sendorId.setTxnType("PROMOTIONAL");
			sendorId.setCreatedDate(new Date());
			sendorId.setStatus("4");
			sendorId.setCreatedBy("WEBSITE");
			masterSendoridRepository.save(sendorId);

			// Insert into the TemplateMaster table
			TemplateMaster template = new TemplateMaster();
			template.setTemplateId(request.getTemplateId());
			template.setTemplateName("WEBSITE");
			template.setTemplateType("PROMOTIONAL");
			template.setEntityId(request.getEntityId());
			template.setSendorId(request.getSendorId());
			template.setCustomerCode(custId);
			template.setCustomerName(request.getSpocName());
			template.setSmsContent(request.getTempateBody().trim());
			template.setSmsDesc(request.getTempateBody().trim());
			template.setTemplateKey("TE"+myUtils.generateAlphanumericString(6));
			template.setStatus("4");
			template.setCreatedBy("WEBSITE");
			template.setCreatedDate(new Date());
			templateMasterRepository.save(template);

			UserMenu userMenu = userMenuRepository.findByUserProfile("CUSTOMER");

			String var10000 = custId.toLowerCase().substring(0, 3);
			String newPass = "S" + var10000 + "@" + myUtils.generateOTP();
			String pass = this.myUtils.hashSHA256(custId, newPass);
			Date passExDt = myUtils.getExpiryDt();

			UserMaster ums = new UserMaster();
			ums.setUserId(custId);
			ums.setName(request.getSpocName().toUpperCase());
			ums.setMobileNo(request.getSpocContactNo());
			ums.setEmailId(request.getEmailId());
			ums.setUserRole(userMenu.getUserRole());
			ums.setUserProfile(userMenu.getUserProfile());
			ums.setStatus("1");
			ums.setUserMenu(userMenu.getMenu());
			ums.setUserCode(custId);
			ums.setPasswd(pass);
			ums.setPasswdExp(passExDt);
			ums.setCreatedBy("WEBSITE");
			ums.setCreatedDt(new Date());
			ums.setAuthStatus("2");
			ums.setAuthBy("WEBSITE");
			ums.setAuthDt(new Date());
			masterRepository.save(ums);

			String txnId = userCode + myUtils.getTxnId();
			SmsMaster sms = new SmsMaster();
			sms.setMobileNo(request.getSpocContactNo());
			sms.setUsername(userCode);
			sms.setOtpDate(new Date());
			sms.setSms("Dear User, Your login account has been created successfully for the SMS Portal, Please note your username is <USERNAME> and password is <PASSWORD> - Appan Dukan"
							.replace("<USERNAME>", custId).replace("<PASSWORD>", newPass));
			//sms.setSms("Dear User, Your OTP is 3345. It is valid for a limited time. Please do not share this OTP with anyone. Regards, Appan Mobility Team");
			sms.setStatus("P");
			sms.setSmsKey(this.smsKey);
			sms.setSmsFrom(this.smsFrom);
			sms.setTemplateId("1707174582347334847");
			sms.setEntityId("1701174410243974966");
			sms.setSmsResponse("SMS send pending for proccess");
			sms.setSendTxnId(txnId);
			smsMasterRepository.save(sms);

			// TOP UP SMS Process
			response = topupSMStocustomerWallet(request, topupAmount, custId);
			if (!response.isStatus()) {
				return response;
			}
			
			// Call mail services
			Logger.info("Sending company email...");
			CommonResponse companyEmailResponse = companyEmailLogService.sendMailToCompany(ums);

			if (companyEmailResponse.isStatus()) {
				Logger.info("Company email sent successfully. Proceeding to send customer email...");

				Logger.info("Sending customer email...");
				emailLogService.sendMailToCustomer(ums, ums.getUserId(), newPass);
				Logger.info("Customer email sent.");
			} else {
				Logger.error("Failed to send company email. Skipping customer email. Reason: {}", companyEmailResponse.getMessage());
			}

			response.setStatus(true);
			response.setMessage(
					"Your request has beed submitted successfully processed. You will receive your User ID and Password via SMS and email shortly.");
			response.setRespCode("00");
			response.setData("applicationLink", APPLICATION_LINK);
			response.setData("username", custId);
			response.setData("password", newPass);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Logger.error("Exception in onboardCustomer: ", e);
			response.setStatus(false);
			response.setMessage("Internal Server Error");
			response.setRespCode("EX");
		}
		return response;
	}

	public CommonResponse topupSMStocustomerWallet(CustomerOnboardRequest request, BigDecimal topupAmount,
			String customerId) {
		// TODO Auto-generated method stub
		CommonResponse response = new CommonResponse();
		try {
			VendorMaster venMaster = vendorMasterRepository.findByVendorId(DEFAULT_DEBIT_VENDOR);
			if (venMaster == null) {
				response.setStatus(false);
				response.setMessage("Vendor Id not found");
				response.setRespCode("02");
				return response;
			}
			if (!venMaster.getAuthStatus().equalsIgnoreCase("1")) {
				response.setStatus(false);
				response.setMessage("Vendor Not Authorized. Please authorize first and then try again.");
				response.setRespCode("02");
				return response;
			}
			long amountInSmsCount = Long.valueOf(TOPUP_SMS_COUNT);
			if (amountInSmsCount > venMaster.getSmsBalance()) {
				response.setStatus(false);
				response.setMessage("Dear customer, Vendor account balance is low for your requested amount.");
				response.setRespCode("02");
				return response;
			}

			String rrn = myUtils.getTxnId();
			long newVendorSmsCount = venMaster.getSmsBalance() - amountInSmsCount;
			// long newVendorSmsCount = customerDetails.getSmsBalance() + amountInSmsCount;
			BuySmsTransaction txn = new BuySmsTransaction();
			txn.setVendorId(venMaster.getVendorId());
			txn.setVendorName(venMaster.getVendorName().toUpperCase());
			txn.setTransactionDate(new Date());
			txn.setTxnType("TOPUP_SMS");
			txn.setAmount(topupAmount);
			txn.setRate(new BigDecimal(TOPUP_SMS_RATE));
			txn.setSms(amountInSmsCount);
			txn.setVendorOldSms(venMaster.getSmsBalance());
			txn.setVendorNewSms(newVendorSmsCount);
			txn.setCustomerId(customerId);
			txn.setCustomerOldSms(Long.valueOf(0));
			txn.setCustomerNewSms(amountInSmsCount);
			txn.setRrn(rrn);
			txn.setStatus("C");
			buySmsTransactionRepository.save(txn);

			venMaster.setSmsBalance(newVendorSmsCount);
			venMaster.setLastTxnUpdateBy("WEBSITE");
			venMaster.setLastTxnUpdateDate(new Date());
			vendorMasterRepository.save(venMaster);

			response.setStatus(true);
			response.setMessage("SMS sale successfully. Total SMS: " + newVendorSmsCount);
			response.setRespCode("00");

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("Exception in onboardCustomer: ", e);
			response.setStatus(false);
			response.setMessage("Internal Server Error");
			response.setRespCode("EX");
			return response;
		}
	}
}
