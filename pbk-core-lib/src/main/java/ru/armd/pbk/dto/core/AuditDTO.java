package ru.armd.pbk.dto.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.DotSeparatedDateTimeDetailedSerializer;

import java.util.Date;

/**
 *
 */
public class AuditDTO
	extends BaseDTO {

   private Long auditTypeId;
   private Long auditLevelId;
   private Long objOperationId;
   private String objInfo;
   @JsonSerialize(using = DotSeparatedDateTimeDetailedSerializer.class)
   private Date createDate;
   private Long createUserId;
   private String message;
   private String stackTrace;
   private String userIPAddress;
   private String threadInfo;
   private String createUserInfo;


   public String getCreateUserInfo() {
	  return createUserInfo;
   }

   public void setCreateUserInfo(String createUserInfo) {
	  this.createUserInfo = createUserInfo;
   }

   public Long getAuditTypeId() {
	  return auditTypeId;
   }

   public void setAuditTypeId(Long auditTypeId) {
	  this.auditTypeId = auditTypeId;
   }

   public Long getAuditLevelId() {
	  return auditLevelId;
   }

   public void setAuditLevelId(Long auditLevelId) {
	  this.auditLevelId = auditLevelId;
   }

   public Long getObjOperationId() {
	  return objOperationId;
   }

   public void setObjOperationId(Long objOperationId) {
	  this.objOperationId = objOperationId;
   }

   public String getObjInfo() {
	  return objInfo;
   }

   public void setObjInfo(String objInfo) {
	  this.objInfo = objInfo;
   }

   public Date getCreateDate() {
	  return createDate;
   }

   public void setCreateDate(Date createDate) {
	  this.createDate = createDate;
   }

   public Long getCreateUserId() {
	  return createUserId;
   }

   public void setCreateUserId(Long createUserId) {
	  this.createUserId = createUserId;
   }

   public String getMessage() {
	  return message;
   }

   public void setMessage(String message) {
	  this.message = message;
   }

   public String getStackTrace() {
	  return stackTrace;
   }

   public void setStackTrace(String stackTrace) {
	  this.stackTrace = stackTrace;
   }

   public String getUserIPAddress() {
	  return userIPAddress;
   }

   public void setUserIPAddress(String userIPAddress) {
	  this.userIPAddress = userIPAddress;
   }

   public String getThreadInfo() {
	  return threadInfo;
   }

   public void setThreadInfo(String threadInfo) {
	  this.threadInfo = threadInfo;
   }
}
