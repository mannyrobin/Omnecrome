package ru.armd.pbk.core.services;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.utils.json.JsonGridData;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс сервиса домена и ДТО с общими методами работы.
 *
 * @param <Domain> Домен.
 * @param <DTO>    ДТО
 */
public interface IDomainService<Domain extends BaseDomain, DTO extends BaseDTO> {

   /**
	* Получения постраничного списка read only view по заданным параметрам
	* фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getGridViews(Params params);

   /**
	* Получение списка ISelectItem для отображения в комбобоксах.
	*
	* @param params       Параметры фильтрации.
	* @param <SelectItem> Тип элементов.
	* @param <Params>     Тип параметров фильтрации.
	* @return Список ISelectItem объектов.
	*/
   <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectList(Params params);

   /**
	* Получение списка доменных объектов.
	*
	* @param params параметры.
	* @return Список Domain объектов.
	*/
   List<Domain> getDomains(Map<String, Object> params);

   /**
	* Получение списка id.
	*
	* @param params параметры.
	* @return Список id.
	*/
   List<Long> getIds(Map<String, Object> params);

   /**
	* Сохранение ДТО. Конвертирует ДТО в домен/домены и работает с методами
	* save у репозиториев.
	*
	* @param dto ДТО
	* @return Сохраненный ДТО.
	*/
   DTO saveDTO(DTO dto);

   /**
	* Получение ДТО по id.
	*
	* @param id id объекта.
	* @return ДТО
	*/
   DTO getDTOById(Long id);

   /**
	* Удаляет объекты по id, указыные в списке.
	*
	* @param ids Список id объектов для удаления.
	* @return Кол-во удаленных записей.
	*/
   int delete(List<Long> ids);

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
	* Метод создания и инициализации домена по данным ДТО.
	*
	* @param dto ДТО.
	* @return Домен.
	*/
   Domain toDomain(DTO dto);

   /**
	* Метод создания и инициализации ДТО по данным домена.
	*
	* @param domain Домен.
	* @return ДТО.
	*/
   DTO toDTO(Domain domain);
}
