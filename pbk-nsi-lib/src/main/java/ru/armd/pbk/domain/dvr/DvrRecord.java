package ru.armd.pbk.domain.dvr;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен записи с видеорегистратора.
 */
public class DvrRecord
	extends BaseDomain {

   private Long deviceId;
   private Date dateRecord;
   private Date dateSession;
   private String descriptionSession;
   private String desecriptionRecord;
   private String duration;
   private String fileSize;
   private Long dvrId;
   private String mime;
   private String ownerName;
   private Long storeId;
   private String url;
   private Long taskId;

   public Long getDeviceId() {
	  return deviceId;
   }

   public void setDeviceId(Long deviceId) {
	  this.deviceId = deviceId;
   }

   public Date getDateRecord() {
	  return dateRecord;
   }

   public void setDateRecord(Date dateRecord) {
	  this.dateRecord = dateRecord;
   }

   public Date getDateSession() {
	  return dateSession;
   }

   public void setDateSession(Date dateSession) {
	  this.dateSession = dateSession;
   }

   public String getDescriptionSession() {
	  return descriptionSession;
   }

   public void setDescriptionSession(String descriptionSession) {
	  this.descriptionSession = descriptionSession;
   }

   public String getDesecriptionRecord() {
	  return desecriptionRecord;
   }

   public void setDesecriptionRecord(String desecriptionRecord) {
	  this.desecriptionRecord = desecriptionRecord;
   }

   public String getDuration() {
	  return duration;
   }

   public void setDuration(String duration) {
	  this.duration = duration;
   }

   public String getFileSize() {
	  return fileSize;
   }

   public void setFileSize(String fileSize) {
	  this.fileSize = fileSize;
   }

   public Long getDvrId() {
	  return dvrId;
   }

   public void setDvrId(Long dvrId) {
	  this.dvrId = dvrId;
   }

   public String getMime() {
	  return mime;
   }

   public void setMime(String mime) {
	  this.mime = mime;
   }

   public String getOwnerName() {
	  return ownerName;
   }

   public void setOwnerName(String ownerName) {
	  this.ownerName = ownerName;
   }

   public Long getStoreId() {
	  return storeId;
   }

   public void setStoreId(Long storeId) {
	  this.storeId = storeId;
   }

   public String getUrl() {
	  return url;
   }

   public void setUrl(String url) {
	  this.url = url;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("deviceId: ").append(StringUtils.nvl(getDeviceId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dateRecord: ").append(StringUtils.nvl(getDateRecord(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dateSession: ").append(StringUtils.nvl(getDateSession(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("descriptionSession: ").append(StringUtils.nvl(getDescriptionSession(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("desecriptionRecord: ").append(StringUtils.nvl(getDesecriptionRecord(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("duration: ").append(StringUtils.nvl(getDuration(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fileSize: ").append(StringUtils.nvl(getFileSize(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dvrId: ").append(StringUtils.nvl(getDvrId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("mime: ").append(StringUtils.nvl(getMime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("ownerName: ").append(StringUtils.nvl(getOwnerName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("storeId: ").append(StringUtils.nvl(getStoreId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("url: ").append(StringUtils.nvl(getUrl(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}
