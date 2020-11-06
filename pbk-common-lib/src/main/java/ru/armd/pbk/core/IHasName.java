package ru.armd.pbk.core;

/**
 * Интерфейс, позволяющий получить название объекта. Обычно содержит название
 * для отображения на клиенте.
 */
public interface IHasName {

   /**
	* Возвращает название.
	*
	* @return
	*/
   String getName();

   /**
	* Устанавливает название.
	*
	* @param name Название.
	*/
   void setName(String name);
}
