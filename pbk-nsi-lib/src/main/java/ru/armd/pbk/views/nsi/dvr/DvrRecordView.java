package ru.armd.pbk.views.nsi.dvr;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

public class DvrRecordView
	extends BaseGridView {

   private Long deviceId;
   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date dateRecord;
   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
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

}
