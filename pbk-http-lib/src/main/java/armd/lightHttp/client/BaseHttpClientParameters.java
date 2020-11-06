package armd.lightHttp.client;

public class BaseHttpClientParameters
	implements IHttpClientParameters {

   private Long clientId;

   private String serviceName = null;
   private String serviceCode = null;
   private String serviceAddress;

   private boolean serviceUseProxy = false;
   private String serviceProxyAddress = null;

   private boolean serviceHasAuth = false;
   private String serviceUsername = null;
   private String servicePassword = null;

   private boolean active = false;
   private boolean testMode = true;

   private boolean logExchangeToFiles = true;
   private String logExchangeDir = null;

   private int httpSoTimeout = -1;
   private int httpConnectionTimeout = -1;

   public Long getClientId() {
	  return clientId;
   }

   public void setClientId(Long clientId) {
	  this.clientId = clientId;
   }

   public String getServiceName() {
	  return serviceName;
   }

   public void setServiceName(String serviceName) {
	  this.serviceName = serviceName;
   }

   public String getServiceCode() {
	  return serviceCode;
   }

   public void setServiceCode(String serviceCode) {
	  this.serviceCode = serviceCode;
   }

   public String getServiceAddress() {
	  return serviceAddress;
   }

   public void setServiceAddress(String serviceAddress) {
	  this.serviceAddress = serviceAddress;
   }

   public boolean isServiceUseProxy() {
	  return serviceUseProxy;
   }

   public void setServiceUseProxy(boolean serviceUseProxy) {
	  this.serviceUseProxy = serviceUseProxy;
   }

   public String getServiceProxyAddress() {
	  return serviceProxyAddress;
   }

   public void setServiceProxyAddress(String serviceProxyAddress) {
	  this.serviceProxyAddress = serviceProxyAddress;
   }

   public boolean isServiceHasAuth() {
	  return serviceHasAuth;
   }

   public void setServiceHasAuth(boolean serviceHasAuth) {
	  this.serviceHasAuth = serviceHasAuth;
   }

   public String getServiceUsername() {
	  return serviceUsername;
   }

   public void setServiceUsername(String serviceUsername) {
	  this.serviceUsername = serviceUsername;
   }

   public String getServicePassword() {
	  return servicePassword;
   }

   public void setServicePassword(String servicePassword) {
	  this.servicePassword = servicePassword;
   }

   public boolean isActive() {
	  return active;
   }

   public void setActive(boolean active) {
	  this.active = active;
   }

   public boolean isTestMode() {
	  return testMode;
   }

   public void setTestMode(boolean testMode) {
	  this.testMode = testMode;
   }

   public boolean isLogExchangeToFiles() {
	  return logExchangeToFiles;
   }

   public void setLogExchangeToFiles(boolean logExchangeToFiles) {
	  this.logExchangeToFiles = logExchangeToFiles;
   }

   public String getLogExchangeDir() {
	  return logExchangeDir;
   }

   public void setLogExchangeDir(String logExchangeDir) {
	  this.logExchangeDir = logExchangeDir;
   }

   public int getHttpSoTimeout() {
	  return httpSoTimeout;
   }

   public void setHttpSoTimeout(int httpSoTimeout) {
	  this.httpSoTimeout = httpSoTimeout;
   }

   public int getHttpConnectionTimeout() {
	  return httpConnectionTimeout;
   }

   public void setHttpConnectionTimeout(int httpConnectionTimeout) {
	  this.httpConnectionTimeout = httpConnectionTimeout;
   }
}
