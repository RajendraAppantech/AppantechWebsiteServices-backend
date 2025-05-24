package com.web.controlles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.CommonResponse;
import com.web.models.CustomerOnboardRequest;
import com.web.models.PincodeResponse;
import com.web.services.FetchPincodedataService;
import com.web.services.OnboardCustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("appantech")
public class ReqController {

	private static final Logger Logger = LoggerFactory.getLogger(OnboardCustomerService.class);
	
	@Autowired
	private OnboardCustomerService onboardCustomerService;

	@Autowired
	private FetchPincodedataService fetchPincodedataService;

	@GetMapping("/test")
	@CrossOrigin(origins = "*")
	public String getTest() {
		return "tested...";
	}

	@GetMapping({"/pincode/{code}" })
	@CrossOrigin(origins = "*")
	public PincodeResponse pincode(@PathVariable("code") String pincode) {
		return this.fetchPincodedataService.pincode(pincode);
	}

	@PostMapping("/onbordCustomer")
	@CrossOrigin(origins = "*")
	public CommonResponse onboardCustomer(@Valid @RequestBody CustomerOnboardRequest request) {
		return onboardCustomerService.onboardCustomer(request);
	}
}
