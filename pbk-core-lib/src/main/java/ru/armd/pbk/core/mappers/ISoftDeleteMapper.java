package ru.armd.pbk.core.mappers;

import java.util.List;

/**
 * Интерфейс маппера с общими методами "мягкого удаления".
 */
public interface ISoftDeleteMapper
	extends IMapper {
   /**
	* Пометить объекты в таблице как удаленные.
	*
	* @param ids список id.
	* @return Кол-во помеченных записей.
	*/
   int deleteSoft(List<Long> ids);
}
