package ru.armd.pbk.domain;

import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.IHasIsDelete;

/**
 * Базовый класс головного доменат версионного объекта.
 */
public class HeadVersionDomain
	implements IHasId, IHasIsDelete {

   private Long id;
   private int isDelete;

   @Override
   public Long getId() {
	  return id;
   }

   @Override
   public void setId(Long id) {
	  this.id = id;
   }

   @Override
   public int getIsDelete() {
	  return isDelete;
   }

   @Override
   public void setIsDelete(int isDelete) {
	  this.isDelete = isDelete;
   }
}
