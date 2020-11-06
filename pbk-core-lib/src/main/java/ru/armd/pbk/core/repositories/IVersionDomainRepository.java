package ru.armd.pbk.core.repositories;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

import java.util.List;

/**
 * Интерфейс репозитория версонного домена с общими методами работы. Отлавливает исключения и ведет аудит.
 *
 * @param <VersionDomain> Версионный домен.
 */
public interface IVersionDomainRepository<VersionDomain extends BaseVersionDomain>
	extends IDomainRepository<VersionDomain> {

   /**
	* Возвращает маппер домена.
	*
	* @return Маппер.
	*/
   IVersionDomainMapper<VersionDomain> getVersionDomainMapper();

   /**
	* Получение последней актуальной версии версионного домена для домена с domainId.
	*
	* @param headId Id домена, для которой получаем версионный объект.
	* @return Информация о версии домена.
	*/
   VersionDomain getActual(Long headId);

   /**
	* Получение списка версионных объектов истории для домена с domainId.
	*
	* @param headId Id домена, для которой получаем список истории версионных объектов.
	* @return Список истории версионных объектов.
	*/
   List<VersionDomain> getHistory(Long headId);

   /**
	* Метод сохранения версии объекта. Если передать новый объект, то отработает как обычное сохранение.
	*
	* @param versionDomain Версия домена.
	* @return Информация о версии домена.
	*/
   VersionDomain saveVersion(VersionDomain versionDomain);

   /**
	* Получения постраничного списка read only view истории по заданным параметрам фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getHistoryViews(Params params);
}
