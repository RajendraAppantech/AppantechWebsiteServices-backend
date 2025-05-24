package com.web.models;

import java.util.List;

import com.web.entity.PincodeMaster;

public class PincodeResponse {
   private boolean status;
   private String message;
   private String respCode;
   private List<PincodeMaster> data;

   public boolean isStatus() {
      return this.status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getRespCode() {
      return this.respCode;
   }

   public void setRespCode(String respCode) {
      this.respCode = respCode;
   }

   public List<PincodeMaster> getData() {
      return this.data;
   }

   public void setData(List<PincodeMaster> data) {
      this.data = data;
   }
}
