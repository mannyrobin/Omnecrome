package ru.armd.pbk.core;

import org.apache.log4j.Logger;

/**
 * Интерфейс, позволяющий получить класс логирования.
 */
public interface IHasLogger {

   /**
	* Возвращает логгер.
	*
	* @return
	*/
   Logger getLogger();

}
