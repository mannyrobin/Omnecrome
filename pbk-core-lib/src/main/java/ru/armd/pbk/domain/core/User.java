package ru.armd.pbk.domain.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class User
	extends BaseDomain
	implements Serializable {

   private static final long serialVersionUID = -5103427742809684027L;

   private String login;
   private String password;
   private String name;
   private Boolean isLdap;
   private Boolean isActive;
   private Date expirationDate;
   private Date ldapCreateDate;
   private Date loginAttemptDate;
   private Integer loginAttemptCount;
   private String reportsAuth;

   public static long getSerialVersionUID() {
	  return serialVersionUID;
   }
	
	/*public boolean compare(User user) {
		return user != null &&
				user.login != null && user.login.equals(login) &&
				user.name != null && user.name.equals(name) &&
				user.expirationDate != null && user.expirationDate.compareTo(expirationDate) == 0 &&
				user.ldapCreateDate != null && user.ldapCreateDate.compareTo(ldapCreateDate) == 0;
	}*/


   @Override
   public boolean equals(Object o) {
	  if (this == o) {
		 return true;
	  }

	  if (o == null || getClass() != o.getClass()) {
		 return false;
	  }

	  User user = (User) o;

	  return new EqualsBuilder().append(login, user.login).append(name, user.name).append(expirationDate, user.expirationDate)
		  .append(ldapCreateDate, user.ldapCreateDate).isEquals();
   }

   @Override
   public int hashCode() {
	  return new HashCodeBuilder(17, 37).append(login).append(name).append(expirationDate).append(ldapCreateDate).toHashCode();
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public String getPassword() {
	  return password;
   }

   public void setPassword(String password) {
	  this.password = password;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Date getExpirationDate() {
	  return expirationDate;
   }

   public void setExpirationDate(Date expirationDate) {
	  this.expirationDate = expirationDate;
   }

   public Boolean isLdap() {
	  return isLdap;
   }

   public Boolean getIsLdap() {
	  return isLdap;
   }

   public void setIsLdap(Boolean isLdap) {
	  this.isLdap = isLdap;
   }

   public Date getLdapCreateDate() {
	  return ldapCreateDate;
   }

   public void setLdapCreateDate(Date ldapCreateDate) {
	  this.ldapCreateDate = ldapCreateDate;
   }

   public Boolean getIsActive() {
	  return isActive;
   }

   public void setIsActive(Boolean isActive) {
	  this.isActive = isActive;
   }

   public Date getLoginAttemptDate() {
	  return loginAttemptDate;
   }

   public void setLoginAttemptDate(Date loginAttemptDate) {
	  this.loginAttemptDate = loginAttemptDate;
   }

   public Integer getLoginAttemptCount() {
	  return loginAttemptCount;
   }

   public void setLoginAttemptCount(Integer loginAttemptCount) {
	  this.loginAttemptCount = loginAttemptCount;
   }

   public String getReportsAuth() {
	  return reportsAuth;
   }

   public void setReportsAuth(String reportsAuth) {
	  this.reportsAuth = reportsAuth;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("login: ").append(StringUtils.nvl(login, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(name, FIELD_NULL)).append(FIELD_SEPARATOR);
	  // TODO: !!!

	  return sb.toString();
   }
}
