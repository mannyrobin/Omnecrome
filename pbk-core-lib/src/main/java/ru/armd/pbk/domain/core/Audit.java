package ru.armd.pbk.domain.core;

import ru.armd.pbk.core.IHasCreater;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Класс аудита.
 */
public class Audit
	extends BaseDomain
	implements IHasCreater {

   private Long auditTypeId;
   private Long auditLevelId;
   private Long objOperationId;
   private String objInfo;
   private Date createDate;
   private Long createUserId;
   private String message;
   private String stackTrace;
   private String userIPAddress;
   private String threadInfo;
   private String createUserInfo;

   /**
	* Конструктор по умолчанию.
	*/
   public Audit() {
   }

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

   @Override
   public Date getCreateDate() {
	  return createDate;
   }

   @Override
   public void setCreateDate(Date createDate) {
	  this.createDate = createDate;
   }

   @Override
   public Long getCreateUserId() {
	  return createUserId;
   }

   @Override
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

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("auditLevel: ").append(StringUtils.nvl(AuditLevel.getEnumById(auditLevelId).toString(), FIELD_NULL))
		  .append(FIELD_SEPARATOR);
	  sb.append("auditType: ").append(StringUtils.nvl(AuditType.getEnumById(auditTypeId).toString(), FIELD_NULL))
		  .append(FIELD_SEPARATOR);
	  sb.append("objOperationId: ")
		  .append(StringUtils.nvl(AuditObjOperation.getEnumById(objOperationId).toString(), FIELD_NULL))
		  .append(FIELD_SEPARATOR);
	  sb.append("objInfo: ").append(StringUtils.nvl(objInfo, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("createDate: ").append(StringUtils.nvl(createDate, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("createUserId: ").append(StringUtils.nvl(createUserId, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("userIPAddress: ").append(StringUtils.nvl(userIPAddress, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("message: ").append(StringUtils.nvl(message, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stackTrace: ").append(StringUtils.nvl(stackTrace, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("threadInfo: ").append(StringUtils.nvl(threadInfo, FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
