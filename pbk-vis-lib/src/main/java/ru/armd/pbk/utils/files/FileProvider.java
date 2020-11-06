package ru.armd.pbk.utils.files;

import java.io.IOException;
import java.io.InputStream;

/**
 * Интерфейс файлового провайдера.
 */
public interface FileProvider {

   /**
	* Создание дирректории, если ее нет.
	*
	* @param path полный путь.
	* @throws IOException
	*/
   void makeDirectory(String path)
	   throws IOException;

   /**
	* Получение списка дочерних файлов.
	*
	* @param path полный путь
	* @throws IOException
	*/
   File[] listFiles(String path)
	   throws IOException;

   /**
	* Переименование файла.
	*
	* @param fileName    старое полное имя файла
	* @param newFileName новое полное имя файла
	* @throws IOException
	*/
   void rename(String fileName, String newFileName)
	   throws IOException;

   /**
	* Получение потока на чтение данных из файла.
	*
	* @param name полное имя файла.
	* @throws IOException
	*/
   InputStream openFileStream(String name)
	   throws IOException;

   /**
	* Освобождение ресурсов потока на чтение данных.
	*
	* @throws IOException
	*/
   void completeFileStream()
	   throws IOException;

   /**
	* Закрытие сессии провайдера.
	*
	* @throws IOException
	*/
   void closeProvider()
	   throws IOException;

}
