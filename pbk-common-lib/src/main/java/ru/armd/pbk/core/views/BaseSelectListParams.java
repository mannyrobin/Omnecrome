package ru.armd.pbk.core.views;

import java.util.Map;

/**
 * Базовые параметры для выпадающего списка.
 */
public class BaseSelectListParams
	implements IParams {

   private Map<String, Object> filter;

   /**
	* Конструкор.
	*
	* @param filter Фильтры
	*/
   public BaseSelectListParams(Map<String, Object> filter) {
	  this.filter = filter;
   }

   public Map<String, Object> getFilter() {
	  return filter;
   }

   public void setFilter(Map<String, Object> filter) {
	  this.filter = filter;
   }
}
