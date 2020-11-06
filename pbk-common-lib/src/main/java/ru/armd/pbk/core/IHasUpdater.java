package ru.armd.pbk.core;

import java.util.Date;

/**
 * Интерфейс, позволяющий получить информацию о пользвоателе и дате обновления
 * записи.
 */
public interface IHasUpdater {

   /**
	* Возвращает дату обновления записи.
	*
	* @return
	*/
   Date getUpdateDate();

   /**
	* Устанавливает дату Обновления записи.
	*
	* @param updateDate Дата.
	*/
   void setUpdateDate(Date updateDate);

   /**
	* Возвращает Id пользваотеля, изменившего запись.
	*
	* @return
	*/
   Long getUpdateUserId();

   /**
	* Устанавливает Id пользваотеля, изменившего запись.
	*
	* @param updateUserId Id пользваотеля, изменившего запись.
	*/
   void setUpdateUserId(Long updateUserId);
}
