package ru.armd.pbk.core.mappers;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.HeadVersionDomain;

import java.util.List;

/**
 * Интерфейс маппера версионного домена с общимии методами для работы с БД.
 *
 * @param <VersionDomain> Версионный домен
 */
public interface IVersionDomainMapper<VersionDomain extends BaseVersionDomain>
	extends IDomainMapper<VersionDomain> {

   /**
	* Сохраняет головной домен версионного объекта.
	*
	* @param headDomain   Головной домен.
	* @param <HeadDomain> Тип головного домена.
	* @return Кол-во созданных записей.
	*/
   <HeadDomain extends HeadVersionDomain> int insertHead(HeadDomain headDomain);

   /**
	* Получение последней актуальной версии версионного домена для домена с domainId.
	*
	* @param headId Id домена, для которой получаем версионный объект.
	* @return Информация о версии домена.
	*/
   VersionDomain getActual(@Param("domainId") Long headId);

   /**
	* Получение списка версионных объектов истории для домена с domainId.
	*
	* @param headId Id домена, для которой получаем список истории версионных объектов.
	* @return Список истории версионных объектов.
	*/
   List<VersionDomain> getHistory(@Param("domainId") Long headId);

   /**
	* Получения постраничного списка read only view истории по заданным параметрам фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getHistoryViews(Params params);


   /**
	* Обновляет головной домен версионного объекта.
	*
	* @param headDomain   Головной домен.
	* @param <HeadDomain> Тип головного домена.
	* @return Кол-во созданных записей.
	*/
   <HeadDomain extends HeadVersionDomain> int updateHead(HeadDomain headDomain);

}
