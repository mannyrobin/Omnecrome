package ru.armd.pbk.core.mappers;

import java.util.Map;

/**
 * Интерфейс маппера для валидации объектов.
 */
public interface IValidateMapper
	extends IMapper {

   /**
	* Выполняет поиск объекта по параметрам фильтрации.
	*
	* @param params параметры фильтрации
	* @return true, если объект найден по текущим параметрам.
	*/
   boolean isExist(Map<String, Object> params);

}
