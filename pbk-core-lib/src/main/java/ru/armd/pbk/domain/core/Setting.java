package ru.armd.pbk.domain.core;

import ru.armd.pbk.core.domain.BaseDictionaryDomain;

/**
 * Доменный объект системной настройки.
 */
public class Setting
	extends BaseDictionaryDomain {

   private String value;

   /**
	* Конструктор по умолчанию.
	*/
   public Setting() {
   }

   public String getValue() {
	  return value;
   }

   public void setValue(String value) {
	  this.value = value;
   }

}
