package ru.armd.pbk.core.dto;

import java.util.Date;

/**
 * ДТО версионных объектов.
 */
public class BaseVersionDTO
	extends BaseDTO {

   private Long headId;
   private Date versionStartDate;
   private Date versionEndDate;

   public Long getHeadId() {
	  return headId;
   }

   public void setHeadId(Long headId) {
	  this.headId = headId;
   }

   public Date getVersionStartDate() {
	  return versionStartDate;
   }

   public void setVersionStartDate(Date versionStartDate) {
	  this.versionStartDate = versionStartDate;
   }

   public Date getVersionEndDate() {
	  return versionEndDate;
   }

   public void setVersionEndDate(Date versionEndDate) {
	  this.versionEndDate = versionEndDate;
   }
}
