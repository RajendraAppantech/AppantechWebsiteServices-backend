package com.web.utils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;
import com.web.repositories.Repositories.CustomerDetailsRepository;

@Component
public class MyUtils {
	
	private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final ThreadLocal<SimpleDateFormat> rrnSuffixFormat = ThreadLocal
			.withInitial(() -> new SimpleDateFormat("yyyyDDDHH"));
    
    public String getTxnId() {
		return rrnSuffixFormat.get().format(new Date()).substring(3) + String.format("%06d", random.nextInt(999999));
	}
    
    public String hashSHA256(String username, String password) {
        return Hashing.sha256().hashString(username + password + "smsgateway", StandardCharsets.UTF_8).toString();
     }
    
    public String generateOTP() {
        StringBuilder generatedToken = new StringBuilder();

        try {
           SecureRandom number = SecureRandom.getInstance("SHA1PRNG");

           for(int i = 0; i < 4; ++i) {
              generatedToken.append(number.nextInt(9));
           }
        } catch (NoSuchAlgorithmException var4) {
           var4.printStackTrace();
        }

        return generatedToken.toString();
     }

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;

	public String getNextCustId() {
		String nextMid = "";
		try {
			String op = customerDetailsRepository.getLastCustId();
			if (op == null) {
				op = "00000";
			}
			op = op.substring(op.length() - 4);
			int lastMid = Integer.parseInt(op);
			nextMid = "C" + String.format("%04d", (lastMid + 1));
			return nextMid;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String generateAlphanumericString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHANUMERIC_CHARACTERS.length());
            sb.append(ALPHANUMERIC_CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
	
	public Date getExpiryDt() {
	      try {
	         int expiryDays = 60;
	         Calendar cal = Calendar.getInstance();
	         cal.setTime(new Date());
	         cal.add(5, expiryDays);
	         Date exDate = cal.getTime();
	         return exDate;
	      } catch (Exception var4) {
	         var4.printStackTrace();
	         return null;
	      }
	   }
}
