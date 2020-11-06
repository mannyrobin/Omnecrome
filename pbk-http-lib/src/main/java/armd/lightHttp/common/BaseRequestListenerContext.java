package armd.lightHttp.common;

import armd.integration.ICallContextInfo;
import armd.integration.IntegrationException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Map;

public class BaseRequestListenerContext
	implements IRequestListenerContext {

   protected BigInteger recordId = null;
   protected Long clientId = null;
   protected Timestamp requestStart = null;
   protected Timestamp requestEnd = null;
   protected String rawRequest = null;
   protected String rawResponse = null;
   protected Object headerObject = null;
   protected Object requestObject = null;
   protected Object responseObject = null;
   protected String action = null;
   protected Map<String, Object> anyObjects;

   protected IRequestLoggerAction afterRegisterRequest = null;
   protected IRequestLoggerAction afterRegisterResponse = null;
   protected IRequestLoggerAction afterRegisterBeginCall = null;
   protected IRequestLoggerAction afterRegisterEndCall = null;

   public BaseRequestListenerContext(Long clientId, IRequestLoggerAction afterRegisterRequest, IRequestLoggerAction afterRegisterResponse, IRequestLoggerAction afterRegisterBeginCall, IRequestLoggerAction afterRegisterEndCall) {
	  this.clientId = clientId;
	  this.afterRegisterRequest = afterRegisterRequest;
	  this.afterRegisterResponse = afterRegisterResponse;
	  this.afterRegisterBeginCall = afterRegisterBeginCall;
	  this.afterRegisterEndCall = afterRegisterEndCall;
   }

   @Override
   public void setCallContextInfo(ICallContextInfo cci) {
   }

   @Override
   public ICallContextInfo getCallContextInfo() {
	  return null;
   }

   @Override
   public BigInteger getRecordId() {
	  return recordId;
   }

   @Override
   public Long getClientId() {
	  return clientId;
   }


   @Override
   public void setRequestStart(Timestamp requestStart) {
	  this.requestStart = requestStart;
   }

   @Override
   public Timestamp getRequestStart() {
	  return this.requestStart;
   }

   @Override
   public void setRequestEnd(Timestamp requestEnd) {
	  this.requestEnd = requestEnd;
   }

   @Override
   public Timestamp getRequestEnd() {
	  return this.requestEnd;
   }

   @Override
   public void setRawRequest(String content) {
	  this.rawRequest = content;
   }

   @Override
   public void setRawResponse(String content) {
	  this.rawResponse = content;
   }

   @Override
   public void setHeaderObject(Object obj) {
	  this.headerObject = obj;
   }

   @Override
   public void setRequestObject(Object obj) {
	  this.requestObject = obj;
   }

   @Override
   public void setResponseObject(Object obj) {
	  this.responseObject = obj;
   }

   @Override
   public String getRawRequest() {
	  return this.rawRequest;
   }

   @Override
   public String getRawResponse() {
	  return this.rawResponse;
   }

   @Override
   public Object getHeaderObject() {
	  return this.headerObject;
   }

   @Override
   public Object getRequestObject() {
	  return this.requestObject;
   }

   @Override
   public Object getResponseObject() {
	  return this.responseObject;
   }

   @Override
   public void setAction(String action) {
	  this.action = action;
   }

   @Override
   public String getAction() {
	  return this.action;
   }

   @Override
   public void registerRequest()
	   throws IntegrationException {
	  if (afterRegisterRequest != null) {
		 afterRegisterRequest.action(this);
	  }
   }

   @Override
   public void registerResponse()
	   throws IntegrationException {
	  if (afterRegisterResponse != null) {
		 afterRegisterResponse.action(this);
	  }
   }

   @Override
   public void registerEndCall()
	   throws IntegrationException {
	  if (afterRegisterEndCall != null) {
		 afterRegisterEndCall.action(this);
	  }
   }

   @Override
   public void registerBeginCall()
	   throws IntegrationException {
	  if (afterRegisterBeginCall != null) {
		 afterRegisterBeginCall.action(this);
	  }
   }

   @Override
   public Map<String, Object> getAnyObjects() {
	  return anyObjects;
   }

   @Override
   public void setAnyObjects(Map<String, Object> anyObjects) {
	  this.anyObjects = anyObjects;
   }
}

