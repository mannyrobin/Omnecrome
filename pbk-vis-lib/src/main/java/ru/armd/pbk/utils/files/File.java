package ru.armd.pbk.utils.files;

/**
 * Интерфейса файла провайдера.
 */
public interface File {

   /**
	* Является ли дирректорией.
	*/
   boolean isDirectory();

   /**
	* Получение имени файла.
	*/
   String getName();
}
