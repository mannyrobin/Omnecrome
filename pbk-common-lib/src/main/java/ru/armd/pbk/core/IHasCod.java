package ru.armd.pbk.core;

/**
 * Интерфейс, позволяющий получать код объекта. Обычно это альтернативный ключ
 * по коорому в БД сформирован уникальный индекс.
 */
public interface IHasCod {

   /**
	* Возвращает код.
	*
	* @return
	*/
   String getCod();

   /**
	* Устанавливает код.
	*
	* @param cod Код.
	*/
   void setCod(String cod);
}
