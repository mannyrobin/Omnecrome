package ru.armd.pbk.core.views;

/**
 * Базовое View для постраничного отображения.
 */
public class BaseGridView
	implements IView, IPageSupport {

   Long cnt; // Количество записей в выборке

   @Override
   public Long getCnt() {
	  return cnt;
   }

   @Override
   public void setCnt(Long cnt) {
	  this.cnt = cnt;
   }
}
