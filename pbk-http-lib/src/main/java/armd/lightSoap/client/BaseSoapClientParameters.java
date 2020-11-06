package armd.lightSoap.client;

import armd.lightHttp.client.BaseHttpClientParameters;

public class BaseSoapClientParameters
	extends BaseHttpClientParameters {

   private String senderCode;
   private String senderName;

   private String recipientCode;
   private String recipientName;

   private boolean customValidation = false;
   private int delayForSyncCalls = -1;

   public String getSenderCode() {
	  return senderCode;
   }

   public void setSenderCode(String senderCode) {
	  this.senderCode = senderCode;
   }

   public String getSenderName() {
	  return senderName;
   }

   public void setSenderName(String senderName) {
	  this.senderName = senderName;
   }

   public String getRecipientCode() {
	  return recipientCode;
   }

   public void setRecipientCode(String recipientCode) {
	  this.recipientCode = recipientCode;
   }

   public String getRecipientName() {
	  return recipientName;
   }

   public void setRecipientName(String recipientName) {
	  this.recipientName = recipientName;
   }

   public boolean isCustomValidation() {
	  return customValidation;
   }

   public void setCustomValidation(boolean customValidation) {
	  this.customValidation = customValidation;
   }

   public int getDelayForSyncCalls() {
	  return delayForSyncCalls;
   }

   public void setDelayForSyncCalls(int delayForSyncCalls) {
	  this.delayForSyncCalls = delayForSyncCalls;
   }
}
