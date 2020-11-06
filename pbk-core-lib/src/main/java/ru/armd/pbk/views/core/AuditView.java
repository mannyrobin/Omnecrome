package ru.armd.pbk.views.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSeparatedDateTimeDetailedSerializer;

import java.util.Date;

/**
 * Класс аудита.
 */
public class AuditView
	extends BaseGridView {

   private Long id;
   private String auditTypeName;
   private Long auditLevelId;
   private String auditLevel;
   @JsonSerialize(using = DotSeparatedDateTimeDetailedSerializer.class)
   private Date createDate;
   private String createUserInfo;
   private String message;
   private String userIPAddress;

   /**
	* Конструкотор по умолчанию.
	*/
   public AuditView() {
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getAuditTypeName() {
	  return auditTypeName;
   }

   public void setAuditTypeName(String auditTypeName) {
	  this.auditTypeName = auditTypeName;
   }

   public String getAuditLevel() {
	  return auditLevel;
   }

   public void setAuditLevel(String auditLevel) {
	  this.auditLevel = auditLevel;
   }

   public Date getCreateDate() {
	  return createDate;
   }

   public void setCreateDate(Date createDate) {
	  this.createDate = createDate;
   }

   public String getCreateUserInfo() {
	  return createUserInfo;
   }

   public void setCreateUserInfo(String createUserInfo) {
	  this.createUserInfo = createUserInfo;
   }

   public String getMessage() {
	  return message;
   }

   public void setMessage(String message) {
	  this.message = message;
   }

   public String getUserIPAddress() {
	  return userIPAddress;
   }

   public void setUserIPAddress(String userIPAddress) {
	  this.userIPAddress = userIPAddress;
   }

   public Long getAuditLevelId() {
	  return auditLevelId;
   }

   public void setAuditLevelId(Long auditLevelId) {
	  this.auditLevelId = auditLevelId;
   }

}
