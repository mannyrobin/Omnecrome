package ru.armd.pbk.core;

import java.util.Date;

/**
 * Интерфейс, позволяющий получить информацию о пользвоателе и дате создания
 * записи.
 */
public interface IHasCreater {

   /**
	* Возвращает дату создания записи.
	*
	* @return
	*/
   Date getCreateDate();

   /**
	* Устанавливает дату создания записи.
	*
	* @param createDate Дата.
	*/
   void setCreateDate(Date createDate);

   /**
	* Возвращает Id пользваотеля, создавшего запись.
	*
	* @return
	*/
   Long getCreateUserId();

   /**
	* Устанавливает Id пользваотеля, создавшего запись.
	*
	* @param createUserId Id пользваотеля, создавшего запись.
	*/
   void setCreateUserId(Long createUserId);

}
