package ru.armd.pbk.domain.authtoken;

import ru.armd.pbk.core.IHasId;

import java.util.Date;

/**
 * Сессия связанная с токеном.
 */
public class TokenSession
	implements IHasId {

   private Long id;

   private Date createDate;

   private Date updateDate;

   private String sessionId;

   private String sessionToken;

   private String userLogin;

   private String remoteAddress;

   private String ipAddress;

   private String sessionTimeZone;

   @Override
   public Long getId() {
	  return id;
   }

   @Override
   public void setId(Long id) {
	  this.id = id;
   }

   public Date getCreateDate() {
	  return createDate;
   }

   public void setCreateDate(Date createDate) {
	  this.createDate = createDate;
   }

   public Date getUpdateDate() {
	  return updateDate;
   }

   public void setUpdateDate(Date updateDate) {
	  this.updateDate = updateDate;
   }

   public String getSessionId() {
	  return sessionId;
   }

   public void setSessionId(String sessionId) {
	  this.sessionId = sessionId;
   }

   public String getSessionToken() {
	  return sessionToken;
   }

   public void setSessionToken(String sessionToken) {
	  this.sessionToken = sessionToken;
   }

   public String getUserLogin() {
	  return userLogin;
   }

   public void setUserLogin(String userLogin) {
	  this.userLogin = userLogin;
   }

   public String getRemoteAddress() {
	  return remoteAddress;
   }

   public void setRemoteAddress(String remoteAddress) {
	  this.remoteAddress = remoteAddress;
   }

   public String getIpAddress() {
	  return ipAddress;
   }

   public void setIpAddress(String ipAddress) {
	  this.ipAddress = ipAddress;
   }

   public String getSessionTimeZone() {
	  return sessionTimeZone;
   }

   public void setSessionTimeZone(String sessionTimeZone) {
	  this.sessionTimeZone = sessionTimeZone;
   }

}
