package com.web.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.models.CommonResponse;
import com.web.models.CustomerOnboardRequest;
import com.web.services.SendEmailToSupportService;

@RestController
@RequestMapping("mailLogger")
public class MailLoggerController {

	@Autowired
	private SendEmailToSupportService emailLogService;

	@PostMapping("/log")
	public CommonResponse logEmailRequest(@RequestBody CustomerOnboardRequest request) {
		return emailLogService.saveSupportEmailLog(request);
	}
}