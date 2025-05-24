package com.web.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.web.HtmlTemplateProcessor;
import com.web.entity.EmailLog;
import com.web.models.CommonResponse;
import com.web.models.CustomerOnboardRequest;
import com.web.repositories.Repositories.EmailLogRepository;

@Service
public class SendEmailToSupportService {

	@Autowired
	private EmailLogRepository emailLogRepository;

	@Value("${email.log.upload-dir}")
	private String uploadDir;

	@Value("${FROM_ID}")
	private String FROM_ID;

	@Value("${CUSTOMER_TO_ID}")
	private String CUSTOMER_TO_ID;

	@Value("${CUSTOMER_CC_ID}")
	private String CUSTOMER_CC_ID;

	@Value("${SUPPORT_TO_ID}")
	private String SUPPORT_TO_ID;

	@Value("${SUPPORT_CC_ID}")
	private String SUPPORT_CC_ID;

	@Value("${CUSTOMER_SUB}")
	private String CUSTOMER_SUB;

	@Value("${SUPPORT_SUB}")
	private String SUPPORT_SUB;
	
	@Autowired
    private ResourceLoader resourceLoader;

	public CommonResponse saveSupportEmailLog(CustomerOnboardRequest request) {

		try {

			// SEND SUPPORT TEAM
			EmailLog log = new EmailLog();
			log.setSendFrom(FROM_ID);
			log.setSendTo(SUPPORT_TO_ID);
			log.setSendCc(SUPPORT_CC_ID);
			log.setSendName("");
			log.setSubject(SUPPORT_SUB);
			log.setIsAttachment("N");
			log.setBodyType("HTML");
			log.setDataBody("");

			// Folder based on timestamp to avoid overwrite
			String folderName = "log_" + System.currentTimeMillis();
			File targetDir = new File(uploadDir, folderName);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}

			Resource resource = resourceLoader.getResource("classpath:static/appantechLogo.jpg");

			 File targetFile = new File(targetDir, "appantechLogo.jpg");

	            try (InputStream in = resource.getInputStream(); OutputStream out = new FileOutputStream(targetFile)) {
	                byte[] buffer = new byte[1024];
	                int bytesRead;

	                while ((bytesRead = in.read(buffer)) != -1) {
	                    out.write(buffer, 0, bytesRead);
	                }
	            }
			
			HtmlTemplateProcessor processor = new HtmlTemplateProcessor();

			Map<String, String> data = new HashMap<>();
			data.put("companyName", request.getCompanyName());
			data.put("spocEmail", request.getEmailId());
			data.put("address1", request.getAddress1());
			data.put("address2", request.getAddress2());
			data.put("spocNo", request.getSpocContactNo());
			data.put("spocName", request.getSpocName());
			data.put("pincode", request.getPincode());
			data.put("city", request.getCity());
			data.put("entityId", request.getEntityId());
			data.put("state", request.getState());
			data.put("senderId", request.getSendorId());
			data.put("country", request.getCountry());
			data.put("templateId", request.getTemplateId());
			data.put("template", request.getTempateBody());

			processor.generateHtmlFromTemplate("src/main/resources/templates/supportEmailBody.html", // template input
					data, targetDir + "/appantech.html"); // output file path

			log.setHtmlFilePath(targetDir+"/appantech.html");
			log.setHtmlFileName("appantech.html");
			log.setImageFilePath(targetDir+"/appantechLogo.jpg");
			log.setImageFileName("appantechLogo.jpg");
			
			log.setStatus("U");
			log.setRemark("Email queued for sending");
			log.setCreatedBy("WEBSITE");
			log.setCreatedDate(new Date());
			log.setServiceName("APPANTECH-WEB-ONBOARD-SMS");
			emailLogRepository.save(log);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}