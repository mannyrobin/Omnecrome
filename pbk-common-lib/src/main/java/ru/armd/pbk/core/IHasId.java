package ru.armd.pbk.core;

/**
 * Интерфейс для классов с идентификатором id.
 */
public interface IHasId {

   /**
	* Возвращает id объекта.
	*
	* @return
	*/
   Long getId();

   /**
	* Устанавливает id объекта.
	*
	* @param id id объекта.
	*/
   void setId(Long id);

}
