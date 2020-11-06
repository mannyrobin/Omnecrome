package ru.armd.pbk.core.views;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * Базовый версионный view.
 */
public abstract class BaseVersionView
	extends BaseGridView {

   private Long id;
   private String createUser;
   private Boolean isActive;

   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date createDate;

   private String updateUser;

   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date updateDate;

   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date versionStartDate;

   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date versionEndDate;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getCreateUser() {
	  return createUser;
   }

   public void setCreateUser(String createUser) {
	  this.createUser = createUser;
   }

   public Date getCreateDate() {
	  return createDate;
   }

   public void setCreateDate(Date createDate) {
	  this.createDate = createDate;
   }

   public String getUpdateUser() {
	  return updateUser;
   }

   public void setUpdateUser(String updateUser) {
	  this.updateUser = updateUser;
   }

   public Date getUpdateDate() {
	  return updateDate;
   }

   public void setUpdateDate(Date updateDate) {
	  this.updateDate = updateDate;
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

   /**
	* Устанавливает окончание периода актуальности.
	* Устанавливает признак актуальности версии в зависимости от даты.
	*
	* @param versionEndDate окончание периода актуальности.
	*/
   public void setVersionEndDate(Date versionEndDate) {
	  this.versionEndDate = versionEndDate;
	  isActive = DateUtils.compareWithVersionEndDate(versionEndDate);
   }

   public boolean isActive() {
	  return isActive;
   }

   public void setActive(boolean isDelete) {
	  this.isActive = isDelete;
   }

}