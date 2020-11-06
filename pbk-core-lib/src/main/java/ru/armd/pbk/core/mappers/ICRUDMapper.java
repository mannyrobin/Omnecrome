package ru.armd.pbk.core.mappers;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.List;

/**
 * Интерфейс маппера с общими методами CRUD операций.
 */
public interface ICRUDMapper<Domain extends BaseDomain>
	extends IMapper {

   /**
	* Вставить объект в таблицу.
	*
	* @param domain domain.
	* @return Кол-во созданных записей.
	*/
   int insert(Domain domain);

   /**
	* Обновить объект в таблице.
	*
	* @param domain domain.
	* @return Кол-во обновленных записей.
	*/
   int update(Domain domain);

   /**
	* Выбрать domain по id.
	*
	* @param id id.
	* @return domain.
	*/
   Domain getById(Long id);

   /**
	* Удалить объекты из таблицы по id.
	*
	* @param ids список id.
	* @return Кол-во удаленных записей.
	*/
   int delete(List<Long> ids);

}
