package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.dto.nsi.RouteDTO;
import ru.armd.pbk.matcher.nsi.RouteMatcher;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.views.nsi.route.RouteMapView;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;
import ru.armd.pbk.views.nsi.route.RouteStop;
import ru.armd.pbk.views.nsi.route.RouteTrajectory;

import java.util.Date;
import java.util.List;

/**
 * Сервис маршрутов.
 */
@Service
public class RouteService
	extends BaseVersionDomainService<Route, RouteDTO> {

   private static final Logger LOGGER = Logger.getLogger(RouteService.class);

   private RouteRepository repository;

   @Autowired
   RouteService(RouteRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Route toDomain(RouteDTO dto) {
	  return RouteMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public RouteDTO toDTO(Route domain) {
	  return RouteMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Возвращает траектории маршрута.
	*
	* @param routeId идентификатор маршрута.
	* @return
	*/
   @Transactional
   public List<RouteTrajectory> getRouteTrajectory(Long routeId) {
	  return repository.getRouteTrajectory(routeId);
   }

   /**
	* Возвращает траектории маршрута по номеру.
	*
	* @param routeNum номер маршрута.
	* @return
	*/
   @Transactional
   public List<RouteTrajectory> getRouteTrajectoryByRouteNum(String routeNum) {
	  return repository.getRouteTrajectoryByRouteNum(routeNum);
   }

   /**
	* Возвращает остановки(координаты и описание) маршрута.
	*
	* @param routeId     - идентификатор маршрута.
	* @param trajectType - идентификатор типа траектории.
	* @return
	*/
   @Transactional
   public List<RouteStop> getRouteStops(Long routeId, Long trajectType) {
	  return repository.getRouteStops(routeId, trajectType);
   }

   /**
	* Получение списка маршрутов для отображения в комбобоксах при создании
	* задания.
	*
	* @param params Параметры фильтрации.
	* @return список ISelectItem объектов.
	*/
   @Transactional
   public List<RouteSelectItem> getSelectItemsForTask(BaseSelectListParams params) {
	  List<RouteSelectItem> selectItemList = RouteMatcher.INSTANCE
		  .getRouteSelectListView(repository.getSelectItemsForTask(params));
	  return selectItemList;
   }

   /**
	* Получение списка маршрутов с траекторией и остановками для отображения на
	* карте сопутствующих маршрутов для мест встреч.
	*
	* @param params - параметры
	* @return
	*/
   @Transactional
   public List<RouteMapView> getRoutesByDistrict(BaseSelectListParams params) {
	  return repository.getRoutesByDistrict(params);
   }

   /**
	* Получение списка маршрутов с траекторией и остановками для отображения на
	* карте сопутствующих маршрутов для мест встреч.
	*
	* @param venueId - ИД места встречи
	* @return
	*/
   @Transactional
   public List<RouteMapView> getRoutesByVenue(Long venueId) {
	  return repository.getRoutesByVenue(venueId);
   }

   /**
	* Обновление количество выходов маршрутов на указаную дату.
	*
	* @param workDate - дата
	*/
   @Transactional
   public void updateRouteMoveCount(Date workDate) {
	  repository.updateRouteMoveCount(workDate);
   }

   /**
	* Получает список маршрутов района, которые еще не добавлены для текущего
	* места встречи.
	*
	* @param params параметры
	* @return список маршрутов района
	*/
   @Transactional
   public List<SelectItem> getSelectItemsForDistrictAdd(BaseSelectListParams params) {
	  return repository.getSelectItemsForDistrictAdd(params);
   }

   /**
	* Получение маршрутов для вкладки районов мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   @Transactional
   public List<SelectItem> getLazySelectItemsForVenues(BaseSelectListParams params) {
	  return repository.getLazySelectItemsForVenues(params);
   }

   /**
	* Получить список ночных маршрутов.
	*
	* @param params фильтр
	* @return
	*/
   @Transactional
   public <Items extends ISelectItem> List<Items> getNightRoutes(BaseSelectListParams params) {
	  return repository.getNightRoutes(params);
   }

   /**
	* Получить список ночных маршрутов для задания.
	*
	* @param params фильтр
	* @return
	*/
   @Transactional
   public List<RouteSelectItem> getNightRoutesForTask(BaseSelectListParams params) {
	  List<RouteSelectItem> sList = repository.getNightRoutes(params);
	  return RouteMatcher.INSTANCE.getRouteSelectListView(sList);
   }

   /**
	* Получение маршрутов округа для вкладки добавления сопутствующих маршрутов
	* мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   public List<RouteMapView> getRoutesOfCounty(BaseSelectListParams params) {
	  return repository.getRoutesOfCounty(params);
   }

   /**
	* Получение маршрутов района места встречи
	* для вкладки добавления сопутствующих маршрутов
	* мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   public List<RouteMapView> getSelectItemsForVenueDistrict(BaseSelectListParams params) {
	  return repository.getSelectItemsForVenueDistrict(params);
   }

   /**
	* Получения списка маршрутов из АСДУ ГТФС для фильтра.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   public <Items extends ISelectItem> List<Items> getGtfsRouteLists(BaseSelectListParams params) {
	  return repository.getGtfsRouteLists(params);
   }

}
