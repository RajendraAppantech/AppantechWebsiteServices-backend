package com.web.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

import com.web.entity.EmailLog;
import com.web.entity.UserMaster;
import com.web.models.CommonResponse;
import com.web.repositories.Repositories.EmailLogRepository;


@Service
public class EmailLogService {

    private static final Logger logger = LoggerFactory.getLogger(EmailLogService.class);

    @Autowired
    private EmailLogRepository emailLogRepository;

    @Value("${FROM_ID}")
    private String FROM_ID;

    @Value("${CUSTOMER_CC_ID}")
    private String CUSTOMER_CC_ID;

    @Value("${CUSTOMER_SUB}")
    private String CUSTOMER_SUB;

    private static final String emailTemplatePath= "/home/ubuntu/sms-docs/email-template/customerOnboardingTemp.html";

    private static final String emailSaveDir= "/home/ubuntu/sms-docs/email-logs/";

    private static final String emailBannerImagePath = "/home/ubuntu/sms-docs/email-template/banner.jpg";

    public CommonResponse sendMailToCustomer(UserMaster ms, String username, String password) {
        CommonResponse response = new CommonResponse();

        try {
            logger.info("Reading email template from path: {}", emailTemplatePath);
            String template = new String(Files.readAllBytes(Paths.get(emailTemplatePath)), StandardCharsets.UTF_8);

            logger.info("Replacing placeholders in the email template for customer: {}", ms.getUserCode());
            template = template.replace("{{customerName}}", ms.getName())
                               .replace("{{username}}", username)
                               .replace("{{password}}", password);

            String folderName = "log_" + System.currentTimeMillis();
            File outputDir = new File(emailSaveDir, folderName);
            if (!outputDir.exists()) {
                boolean created = outputDir.mkdirs();
                logger.info("Created output directory: {} - success: {}", outputDir.getAbsolutePath(), created);
            } else {
                logger.info("Using existing output directory: {}", outputDir.getAbsolutePath());
            }

            File htmlFile = new File(outputDir, "appantechCus.html");
            logger.info("Writing HTML content to: {}", htmlFile.getAbsolutePath());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile))) {
                writer.write(template);
            }

            File imageFile = new File(outputDir, "banner.jpg");
            logger.info("Copying banner image from {} to {}", emailBannerImagePath, imageFile.getAbsolutePath());
            Files.copy(Paths.get(emailBannerImagePath), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            logger.info("Saving email log to database for customer: {}", ms.getUserCode());
            EmailLog log = new EmailLog();
            log.setSendFrom(FROM_ID);
            log.setSendTo(ms.getEmailId());
            log.setSendCc(CUSTOMER_CC_ID);
            log.setSendName("AppanTech");
            log.setSubject(CUSTOMER_SUB);
            log.setIsAttachment("Y");
            log.setBodyType("HTML");
            log.setDataBody("Customer Authorization Email");
            log.setHtmlFilePath(htmlFile.getAbsolutePath().replace("\\", "/"));
            log.setHtmlFileName(htmlFile.getName());
            log.setImageFilePath(imageFile.getAbsolutePath().replace("\\", "/"));
            log.setImageFileName(imageFile.getName());
            log.setStatus("U");
            log.setCreatedBy(ms.getCreatedBy());
            log.setCreatedDate(new Date());
            log.setServiceName("CustomerAuthorization");

            emailLogRepository.save(log);
            logger.info("Email log saved successfully with ID: {}", log.getId());

            response.setStatus(true);
            response.setMessage("Email log saved successfully.");
            response.setRespCode("00");
            response.setData("logId", log.getId());

        } catch (IOException e) {
            logger.error("IOException while processing email template: {}", e.getMessage(), e);
            response.setStatus(false);
            response.setMessage("Failed to process email template: " + e.getMessage());
            response.setRespCode("98");
        } catch (Exception e) {
            logger.error("Exception while saving email log: {}", e.getMessage(), e);
            response.setStatus(false);
            response.setMessage("Failed to save email log: " + e.getMessage());
            response.setRespCode("99");
        }

        return response;
    }
}
