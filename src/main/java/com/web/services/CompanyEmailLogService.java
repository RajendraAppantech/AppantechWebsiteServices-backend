package com.web.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.entity.CustomerDetails;
import com.web.entity.EmailLog;
import com.web.entity.TemplateMaster;
import com.web.entity.UserMaster;
import com.web.models.CommonResponse;
import com.web.repositories.Repositories.CustomerDetailsRepository;
import com.web.repositories.Repositories.EmailLogRepository;
import com.web.repositories.Repositories.TemplateMasterRepository;


@Service
public class CompanyEmailLogService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyEmailLogService.class);

    @Autowired
    private EmailLogRepository emailLogRepository;

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private TemplateMasterRepository templateMasterRepository;

    @Value("${FROM_ID}")
    private String fromId;

    @Value("${SUPPORT_TO_ID}")
    private String supportToId;

    @Value("${SUPPORT_SUB}")
    private String supportSub;

    @Value("${SUPPORT_CC_ID}")
    private String supportCcId;

    private static final String EMAIL_TEMPLATE_PATH = "/home/ubuntu/sms-docs/email-template/supportEmailBody.html";
    private static final String IMAGE_RESOURCE_PATH = "/home/ubuntu/sms-docs/email-template/banner.jpg";
    private static final String EMAIL_SAVE_BASE_DIR = "/home/ubuntu/sms-docs/email-logs/";

    public CommonResponse sendMailToCompany(UserMaster user) {
        CommonResponse response = new CommonResponse();

        try {
            CustomerDetails customer = customerDetailsRepository.findByCustomerCode(user.getUserCode());
            if (customer == null) {
                return createErrorResponse("Customer not found for user", "01");
            }

            logger.info("Reading email template from path: {}", EMAIL_TEMPLATE_PATH);
            String template = new String(Files.readAllBytes(Paths.get(EMAIL_TEMPLATE_PATH)), StandardCharsets.UTF_8);

            logger.info("Populating template placeholders for customer: {}", customer.getCustomerCode());
            template = populateTemplatePlaceholders(template, customer, user);

            long timestamp = System.currentTimeMillis();
            String folderName = "log_" + timestamp;
            File targetDir = new File(EMAIL_SAVE_BASE_DIR, folderName);
            if (!targetDir.exists()) {
                logger.info("Creating directory at: {}", targetDir.getAbsolutePath());
                targetDir.mkdirs();
            }

            // Write HTML file
            File htmlFile = new File(targetDir, "appantechCompany.html");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile))) {
                writer.write(template);
            }
            logger.info("HTML email saved to: {}", htmlFile.getAbsolutePath());

            // Copy image
            File imageFile = new File(targetDir, "banner.jpg");
            Files.copy(Paths.get(IMAGE_RESOURCE_PATH), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Banner image copied to: {}", imageFile.getAbsolutePath());

            // Save to DB
            EmailLog log = new EmailLog();
            log.setSendFrom(fromId);
            log.setSendTo(supportToId);
            log.setSendCc(supportCcId);
            log.setSendName("AppanTech");
            log.setSubject(supportSub);
            log.setIsAttachment("Y");
            log.setBodyType("HTML");
            log.setDataBody("Company Registration Email");
            log.setHtmlFilePath(htmlFile.getAbsolutePath().replace("\\", "/"));
            log.setHtmlFileName(htmlFile.getName());
            log.setImageFilePath(imageFile.getAbsolutePath().replace("\\", "/"));
            log.setImageFileName(imageFile.getName());
            log.setStatus("U");
            log.setCreatedBy(user.getCreatedBy());
            log.setCreatedDate(new Date());
            log.setServiceName("CustomerAuthorization");

            emailLogRepository.save(log);

            response.setStatus(true);
            response.setMessage("Company email log saved successfully.");
            response.setRespCode("00");
            response.setData("logId", log.getId());

        } catch (Exception e) {
            logger.error("Error while saving company email log: {}", e.getMessage(), e);
            response.setStatus(false);
            response.setMessage("Company email log failed: " + e.getMessage());
            response.setRespCode("99");
        }

        return response;
    }

    private String populateTemplatePlaceholders(String template, CustomerDetails customer, UserMaster user) {
        template = template.replace("{{companyName}}", safe(customer.getName()));
        template = template.replace("{{spocNo}}", safe(user.getMobileNo()));
        template = template.replace("{{spocName}}", safe(customer.getName()));
        template = template.replace("{{spocEmail}}", safe(customer.getEmailId()));
        template = template.replace("{{entityId}}", safe(customer.getEntityId()));

        TemplateMaster tm = templateMasterRepository
                .findFirstByEntityIdAndStatusIgnoreCase(customer.getEntityId(), "ACTIVE").orNull();

        template = template.replace("{{templateId}}", tm != null ? safe(tm.getTemplateId()) : "");
        template = template.replace("{{template}}", tm != null ? safe(tm.getSmsContent()) : "");
        template = template.replace("{{senderId}}", tm != null ? safe(tm.getSendorId()) : "");

        template = template.replace("{{address1}}", safe(customer.getAddress1()));
        template = template.replace("{{address2}}", safe(customer.getAddress2()));
        template = template.replace("{{pincode}}", safe(customer.getPinCode()));
        template = template.replace("{{city}}", safe(customer.getCity()));
        template = template.replace("{{state}}", safe(customer.getState()));
        template = template.replace("{{country}}", "India");

        return template;
    }

    private String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private CommonResponse createErrorResponse(String message, String code) {
        CommonResponse res = new CommonResponse();
        res.setStatus(false);
        res.setMessage(message);
        res.setRespCode(code);
        return res;
    }
}
