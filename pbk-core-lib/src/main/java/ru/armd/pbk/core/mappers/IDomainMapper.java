package ru.armd.pbk.core.mappers;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.sql.SQLAdapter;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс маппера домена с общимии методами для работы с БД.
 *
 * @param <Domain> Домен.
 */
public interface IDomainMapper<Domain extends BaseDomain>
	extends ICRUDMapper<Domain>, IViewMapper, IDictionaryMapper<Domain>, IImportMapper<Domain>, ISoftDeleteMapper, IValidateMapper {

   /**
	* Получение списка доменных объектов.
	*
	* @param params Параметры фильтрации.
	* @return Список Domain объектов.
	*/
   List<Domain> getDomains(Map<String, Object> params);

   /**
	* TODO: Возможно этот метод не нужен
	* Получение доменного объекта с параметрами фльтрации.
	*
	* @param params Параметры фильтрации
	* @return домен
	*/
   Domain getDomain(Map<String, Object> params);

   /**
	* Получение списка id доменов.
	*
	* @param params Параметры фильтрации.
	* @return Список id доменов.
	*/
   List<Long> getIds(Map<String, Object> params);

   void execSql(SQLAdapter sql);

}
