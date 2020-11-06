package ru.armd.pbk.core.domain;

import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Базовый класс всех доменов.
 */
public abstract class BaseDomain
	implements IDomain {

   private Long id;
   private Long createUserId;
   private Date createDate;
   private Long updateUserId;
   private Date updateDate;
   private int isDelete;

   /**
	* Конструктор по умолчанию.
	*/
   public BaseDomain() {
   }

   /**
	* Конструктор.
	*
	* @param id           id.
	* @param createUserId id пользователя, создавшего запись.
	* @param createDate   дата создания записи.
	* @param updateUserId id пользователя, изменившего запись.
	* @param updateDate   дата изменения записи.
	*/
   public BaseDomain(Long id, Long createUserId, Date createDate, Long updateUserId, Date updateDate) {
	  this.id = id;
	  this.createUserId = createUserId;
	  this.createDate = createDate;
	  this.updateUserId = updateUserId;
	  this.updateDate = updateDate;
   }

   @Override
   public Long getId() {
	  return id;
   }

   @Override
   public void setId(Long id) {
	  this.id = id;
   }

   @Override
   public Long getCreateUserId() {
	  return createUserId;
   }

   @Override
   public void setCreateUserId(Long createUserId) {
	  this.createUserId = createUserId;
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
   public Long getUpdateUserId() {
	  return updateUserId;
   }

   @Override
   public void setUpdateUserId(Long updateUserId) {
	  this.updateUserId = updateUserId;
   }

   @Override
   public Date getUpdateDate() {
	  return updateDate;
   }

   @Override
   public void setUpdateDate(Date updateDate) {
	  this.updateDate = updateDate;
   }

   @Override
   public int getIsDelete() {
	  return isDelete;
   }

   @Override
   public void setIsDelete(int isDelete) {
	  this.isDelete = isDelete;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder("ClassName: ").append(this.getClass().getName()).append(FIELD_SEPARATOR);
	  sb.append("id: ").append(StringUtils.nvl(getId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isDelete: ").append(getIsDelete()).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
