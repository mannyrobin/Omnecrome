package armd.lightHttp.common;


import armd.integration.ICallContextInfo;
import armd.integration.IntegrationException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Map;

public interface IRequestListenerContext {

   void setCallContextInfo(ICallContextInfo cci);

   ICallContextInfo getCallContextInfo();

   BigInteger getRecordId();

   Long getClientId();


   void setRequestStart(Timestamp requestStart);

   Timestamp getRequestStart();

   void setRequestEnd(Timestamp requestStart);

   Timestamp getRequestEnd();

   void setRawRequest(String content);

   void setRawResponse(String content);

   void setHeaderObject(Object obj);

   void setRequestObject(Object obj);

   void setResponseObject(Object obj);

   String getRawRequest();

   String getRawResponse();

   Object getHeaderObject();

   Object getRequestObject();

   Object getResponseObject();

   void setAction(String action);

   String getAction();

   void registerRequest()
	   throws IntegrationException;

   void registerResponse()
	   throws IntegrationException;

   void registerEndCall()
	   throws IntegrationException;

   void registerBeginCall()
	   throws IntegrationException;

   Map<String, Object> getAnyObjects();

   void setAnyObjects(Map<String, Object> anyObjects);
}
