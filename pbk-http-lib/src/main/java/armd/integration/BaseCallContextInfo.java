package armd.integration;

import java.math.BigInteger;

public class BaseCallContextInfo
	implements ICallContextInfo {
   protected boolean asyncCall;
   protected AsyncRequestStage asyncStage;
   protected int requestType;
   protected BigInteger callerDepartmentId;
   protected int callerUserId;
   protected BigInteger appControlRegisterId;
   protected BigInteger integrationStorageEntityId;
   protected IntegratedSoapClient caller;

   public BaseCallContextInfo(int requestType, boolean asyncCall, AsyncRequestStage asyncStage, BigInteger callerDepartmentId, int callerUserId) {
	  this.requestType = requestType;
	  this.asyncCall = asyncCall;
	  this.asyncStage = asyncStage;
	  this.callerDepartmentId = callerDepartmentId;
	  this.callerUserId = callerUserId;
   }

   public int getRequestType() {
	  return requestType;
   }

   public void setRequestType(int requestType) {
	  this.requestType = requestType;
   }

   public BigInteger getCallerDepartmentId() {
	  return callerDepartmentId;
   }

   public void setCallerDepartmentId(BigInteger callerDepartmentId) {
	  this.callerDepartmentId = callerDepartmentId;
   }

   public int getCallerUserId() {
	  return callerUserId;
   }

   public void setCallerUserId(int callerUserId) {
	  this.callerUserId = callerUserId;
   }

   public boolean isAsyncCall() {
	  return asyncCall;
   }

   public void setAsyncCall(boolean asyncCall) {
	  this.asyncCall = asyncCall;
   }

   public AsyncRequestStage getRequestStage() {
	  return asyncStage;
   }

   public void setRequestStage(AsyncRequestStage asyncStage) {
	  this.asyncStage = asyncStage;
   }

   public BigInteger getAppControlRegisterId() {
	  return appControlRegisterId;
   }

   public void setAppControlRegisterId(BigInteger appControlRegisterId) {
	  this.appControlRegisterId = appControlRegisterId;
   }

   public BigInteger getIntegrationStorageEntityId() {
	  return integrationStorageEntityId;
   }

   public void setIntegrationStorageEntityId(BigInteger integrationStorageEntityId) {
	  this.integrationStorageEntityId = integrationStorageEntityId;
   }

   public IntegratedSoapClient getCaller() {
	  return caller;
   }

   public void setCaller(IntegratedSoapClient caller) {
	  this.caller = caller;
   }
}
