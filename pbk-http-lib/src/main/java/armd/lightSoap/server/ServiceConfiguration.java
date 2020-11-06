package armd.lightSoap.server;


public class ServiceConfiguration {
   private String typeName;
   private String path;
   private String certThumbprint;
   private String certPassword;
   private String unregisteredClientIpRegexp;
   private Boolean testMode;
   private Boolean customValidation;

   public String getTypeName() {
	  return typeName;
   }

   public void setTypeName(String typeName) {
	  this.typeName = typeName;
   }

   public String getPath() {
	  return path;
   }

   public void setPath(String path) {
	  this.path = path;
   }

   public String getCertThumbprint() {
	  return certThumbprint;
   }

   public void setCertThumbprint(String certThumbprint) {
	  this.certThumbprint = certThumbprint;
   }

   public String getCertPassword() {
	  return certPassword;
   }

   public void setCertPassword(String certPassword) {
	  this.certPassword = certPassword;
   }

   public String getUnregisteredClientIpRegexp() {
	  return unregisteredClientIpRegexp;
   }

   public void setUnregisteredClientIpRegexp(String unregisteredClientIpRegexp) {
	  this.unregisteredClientIpRegexp = unregisteredClientIpRegexp;
   }

   public Boolean getTestMode() {
	  return testMode;
   }

   public void setTestMode(Boolean testMode) {
	  this.testMode = testMode;
   }

   public Boolean getCustomValidation() {
	  return customValidation;
   }

   public void setCustomValidation(Boolean customValidation) {
	  this.customValidation = customValidation;
   }
}
