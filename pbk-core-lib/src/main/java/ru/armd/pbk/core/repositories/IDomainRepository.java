package ru.armd.pbk.core.repositories;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.enums.core.AuditType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс репозитория домена с общими методами работы. Отлавливает исключения
 * и ведет аудит.
 *
 * @param <Domain> Домен.
 */
public interface IDomainRepository<Domain extends BaseDomain> {

   /**
	* Возвращает тип события аудита домена.
	*
	* @return Тип события
	*/
   AuditType getDomainAuditType();

   /**
	* Возвращает маппер домена.
	*
	* @return Маппер.
	*/
   IDomainMapper<Domain> getDomainMapper();

   /**
	* Получения постраничного списка read only view по заданным параметрам
	* фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params);

   /**
	* Получение списка ISelectItem для отображения в комбобоксах.
	*
	* @param params       Параметры фильтрации.
	* @param <SelectItem> Тип элементов.
	* @param <Params>     Тип параметров фильтрации.
	* @return Список ISelectItem объектов.
	*/
   <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectItems(
	   Params params);

   /**
	* Получение списка доменных объектов.
	*
	* @param params параметры.
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
	* Получение списка id.
	*
	* @param params параметры.
	* @return Список id.
	*/
   List<Long> getIds(Map<String, Object> params);

   /**
	* Метод сохранения домена. Если домена нет в БД ,то добавялет. Иначе
	* обновляет. Внутри реализации вызывает методы create и update.
	*
	* @param domain Домен.
	* @return Сохраненный домен.
	*/
   Domain save(Domain domain);

   /**
	* Возвращает домен по id.
	*
	* @param id id.
	* @return Домен.
	*/
   Domain getById(Long id);

   /**
	* Возвращает домен по id.
	*
	* @param hasId IHasId.
	* @return Домен.
	*/
   Domain getById(IHasId hasId);

   /**
	* Возвращает домен по коду.
	*
	* @param code Код.
	* @return Домен.
	*/
   Domain getByCode(String code);

   /**
	* Возвращает домен по коду.
	*
	* @param hasCod IHasCod.
	* @return Домен.
	*/
   Domain getByCode(IHasCod hasCod);

   /**
	* Удаляет домены по id, указыные в списке.
	*
	* @param ids Список id объектов для удаления.
	* @return Кол-во удаленных записей.
	*/
   int delete(List<Long> ids);

   /**
	* Удаление домена по id.
	*
	* @param id id.
	* @return Кол-во удаленных записей.
	*/
   int delete(Long id);

   /**
	* Удаление домена.
	*
	* @param domain Домен.
	* @return Кол-во удаленных записей.
	*/
   int delete(Domain domain);

   /**
	* Пытается удалить домены по id, указыные в списке. Если не получается,
	* проходит по списку и поштучно помечает объекты в таблице как удаленные.
	*
	* @param ids       список id.
	* @param tryDelete если true, то пробует удалить реально и при неудачной попытке
	*                  изменяет флаг isDelete, в противном случае просто изменяет
	*                  флаг.
	* @return Кол-во помеченных записей.
	*/
   int deleteSoft(List<Long> ids, Boolean tryDelete);

   /**
	* Сохранение.
	*
	* @param domains домен.
	* @return
	*/
   int save(List<Domain> domains, Date workDate);

   void execSql(String sql);
}
