package ru.armd.pbk.core.services;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.utils.json.JsonGridData;

/**
 * Интерфейс сервиса версионных домена и ДТО с общими методами работы.
 *
 * @param <DomainVersion> Домен.
 * @param <DTOVersion>    ДТО.
 */
public interface IVersionDomainService<DomainVersion extends BaseVersionDomain, DTOVersion extends BaseVersionDTO>
	extends IDomainService<DomainVersion, DTOVersion> {

   /**
	* Получения постраничного списка read only view истории по заданным
	* параметрам фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getHistoryViews(Params params);

   /**
	* Сохраняет новую версию домена, если домен новый отрабатывает как обычное
	* сохранение и создаёт первую версию.
	*
	* @param dto
	* @return
	*/
   DTOVersion saveVersionDTO(DTOVersion dto);

   /**
	* Восстановить версию объекта.
	*
	* @param id версии
	* @return версия объекста
	*/
   DTOVersion restoreVersionDTO(Long id);
}
