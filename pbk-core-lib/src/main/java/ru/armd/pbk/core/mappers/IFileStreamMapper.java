package ru.armd.pbk.core.mappers;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.FileStream;

import java.util.List;

/**
 * Маппер файла.
 */
@IsMapper
public interface IFileStreamMapper {

   /**
	* Выбрать domain по id.
	*
	* @param id id.
	* @return domain.
	*/
   FileStream getById(String id);


   /**
	* Выбрать domain по name.
	*
	* @param name name.
	* @return domain.
	*/
   FileStream getByName(String name);

   /**
	* Вставить объект в таблицу.
	*
	* @param domain domain.
	* @return Кол-во созданных записей.
	*/
   int insert(FileStream domain);


   /**
	* Удалить объекты из таблицы по id.
	*
	* @param ids список id.
	* @return Кол-во удаленных записей.
	*/
   int delete(List<String> ids);
}
