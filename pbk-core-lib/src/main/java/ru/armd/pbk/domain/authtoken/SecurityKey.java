package ru.armd.pbk.domain.authtoken;

import ru.armd.pbk.core.IHasId;

import java.util.Date;

/**
 * Доменный объект ключа, содержит сериализованный ключ.
 */
public class SecurityKey
	implements IHasId {

   private Long id;

   private Date createDate;

   private byte[] keyContent;

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

   public byte[] getKeyContent() {
	  return keyContent;
   }

   public void setKeyContent(byte[] keyContent) {
	  this.keyContent = keyContent;
   }
}
