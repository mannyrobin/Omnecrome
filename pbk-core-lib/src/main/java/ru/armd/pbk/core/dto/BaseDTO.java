package ru.armd.pbk.core.dto;

import ru.armd.pbk.core.IHasId;

/**
 * Базовый класс всех ДТО.
 */
public abstract class BaseDTO
	implements IDTO, IHasId {

   private Long id;

   private int isDelete;

   /**
	* Конструктор по умолчанию.
	*/
   public BaseDTO() {
   }

   /**
	* Конструктор.
	*
	* @param id id.
	*/
   public BaseDTO(Long id) {
	  this.id = id;
   }

   @Override
   public Long getId() {
	  return id;
   }

   @Override
   public void setId(Long id) {
	  this.id = id;
   }

   public int getIsDelete() {
	  return isDelete;
   }

   public void setIsDelete(int isDelete) {
	  this.isDelete = isDelete;
   }

}
