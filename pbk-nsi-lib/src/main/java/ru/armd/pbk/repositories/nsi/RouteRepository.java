package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.RouteMapper;
import ru.armd.pbk.mappers.nsi.venue.VenueMapper;
import ru.armd.pbk.views.nsi.route.RouteMapView;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;
import ru.armd.pbk.views.nsi.route.RouteStop;
import ru.armd.pbk.views.nsi.route.RouteTrajectory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий для работы с маршрутами.
 */
@Repository
public class RouteRepository
	extends BaseVersionDomainRepository<Route> {

   public static final Logger LOGGER = Logger.getLogger(CountyRepository.class);

   @Autowired
   private VenueMapper venueMapper;

   @Autowired
   RouteRepository(RouteMapper mapper, VenueMapper venueMapper) {
	  super(NsiAuditType.NSI_ROUTE, mapper);
	  this.venueMapper = venueMapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  params.setOrderBy(params.getOrderBy().equals("routeNumber") ? "routeNumberInt" : params.getOrderBy());
	  return super.getGridViews(params);
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getHistoryViews(Params params) {
	  params.setOrderBy(params.getOrderBy().equals("routeNumber") ? "routeNumberInt" : params.getOrderBy());
	  return super.getHistoryViews(params);
   }

   /**
	* Возвращает траектории маршрута.
	*
	* @param routeId идентификатор маршрута.
	* @return
	*/
   public List<RouteTrajectory> getRouteTrajectory(Long routeId) {
	  try {
		 return ((RouteMapper) getDomainMapper()).getRouteTrajectory(routeId);
	  } catch (Exception e) {
		 String message = "Не удалось получить траектории маршрута по идентификатору. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, routeId, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Возвращает траектории маршрута по номеру.
	*
	* @param routeNum номер маршрута.
	* @return
	*/
   public List<RouteTrajectory> getRouteTrajectoryByRouteNum(String routeNum) {
	  try {
		 return ((RouteMapper) getDomainMapper()).getRouteTrajectoryByRouteNum(routeNum);
	  } catch (Exception e) {
		 String message = "Не удалось получить траекторию маршрута по номеру. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, routeNum, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Возвращает остановки(координаты и описание) маршрута.
	*
	* @param routeId     идентификатор маршрута.
	* @param trajectType идентификатор типа траектории маршрута.
	* @return список остановки(координаты и описание).
	*/
   public List<RouteStop> getRouteStops(Long routeId, Long trajectType) {
	  try {
		 return ((RouteMapper) getDomainMapper()).getRouteStops(routeId, trajectType);
	  } catch (Exception e) {
		 String message = "Не удалось получить остановки маршрута. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, routeId, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Получение списка маршрутов для отображения в комбобоксах при создании
	* задания.
	*
	* @param params Параметры фильтрации.
	* @return список ISelectItem объектов.
	*/
   public List<RouteSelectItem> getSelectItemsForTask(BaseSelectListParams params) {
	  List<RouteSelectItem> sList = null;
	  try {
		 sList = ((RouteMapper) getDomainMapper()).getSelectItemsForTask(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получение списка маршрутов с траекторией и остановками для отображения на
	* карте сопутствующих маршрутов для мест встреч.
	*
	* @param params - параметры
	* @return
	*/
   public List<RouteMapView> getRoutesByDistrict(BaseSelectListParams params) {
	  boolean hideStops = params.getFilter().get("hideStops") != null
		  && (long) (params.getFilter().get("hideStops")) == 1;
	  return fillRouteTrajectoryAndStops(
		  params.getFilter().get("forVenue") != null && (long) (params.getFilter().get("forVenue")) == 1
			  ? venueMapper.getSelectVenueDistrictRoutes(params.getFilter())
			  : ((RouteMapper) getDomainMapper()).getSelectItemsForDistrict(params.getFilter()),
		  !hideStops);
   }

   /**
	* Получение списка маршрутов с траекторией и остановками для отображения на
	* карте сопутствующих маршрутов для мест встреч.
	*
	* @param venueId - ИД места встречи
	* @return
	*/
   public List<RouteMapView> getRoutesByVenue(Long venueId) {
	  return fillRouteTrajectory(((RouteMapper) getDomainMapper()).getSelectItemsForVenue(venueId));
   }

   /**
	* Заполнить траекторию и остановки для маршрутов из списка {@code list}.
	* В зависимости от флага {@code getStops} будет произведено заполнение остоновок.
	* Если флаг не выставлен, то метод вернет только траектории маршрутов.
	*
	* @param list     - номера маршрутов.
	* @param getStops - флаг заполнения остоновок.
	* @return
	*/
   public List<RouteMapView> fillRouteTrajectoryAndStops(List<SelectItem> list, boolean getStops) {
	  List<Long> ids = new ArrayList<Long>();
	  Map<Long, RouteMapView> cache = new HashMap<Long, RouteMapView>();
	  for (SelectItem item : list) {
		 ids.add(item.getId());
	  }
	  if (ids.isEmpty()) {
		 return new ArrayList<RouteMapView>(cache.values());
	  }
	  RouteMapper routeMapper = (RouteMapper) getDomainMapper();
	  Map<Long, ArrayList<RouteTrajectory>> trajectories = new HashMap<Long, ArrayList<RouteTrajectory>>();
	  for (RouteTrajectory trajectory : routeMapper.getRoutesTrajectory(ids)) {
		 if (!trajectories.containsKey(trajectory.getId())) {
			trajectories.put(trajectory.getId(), new ArrayList<RouteTrajectory>());
		 }
		 trajectories.get(trajectory.getId()).add(trajectory);
	  }
	  for (SelectItem item : list) {
		 RouteMapView view = new RouteMapView();
		 view.setId(item.getId());
		 view.setName(item.getName());
		 view.setRouteTrajectories(trajectories.get(item.getId()));
		 cache.put(item.getId(), view);
	  }
	  if (getStops) {
		 List<RouteStop> stops = routeMapper.getRoutesStops(ids);
		 if (stops.size() > 0) {
			RouteStop prev = stops.get(0);
			List<RouteStop> stopsTrajectory = new LinkedList<RouteStop>();
			for (RouteStop stop : stops) {
			   if (!prev.getRouteId().equals(stop.getRouteId()) || !prev.getTrajectoryType().equals(stop.getTrajectoryType())) {
				  cache.get(prev.getRouteId()).getRouteStops().put(prev.getTrajectoryType(), new ArrayList<RouteStop>(stopsTrajectory));
				  stopsTrajectory.clear();
			   }
			   stopsTrajectory.add(stop);
			   prev = stop;
			}

			//Для последней траектории положить остановки

			cache.get(prev.getRouteId()).getRouteStops().put(prev.getTrajectoryType(), new ArrayList<RouteStop>(stopsTrajectory));
		 }

	  }
	  return new ArrayList<RouteMapView>(cache.values());
   }

   /**
	* Заполнить траекторию для маршрутов.
	*
	* @param list - номера маршрутов.
	* @return
	*/
   private List<RouteMapView> fillRouteTrajectory(List<SelectItem> list) {
	  List<RouteMapView> result = new ArrayList<RouteMapView>();
	  List<Long> ids = new ArrayList<Long>();
	  for (SelectItem item : list) {
		 ids.add(item.getId());
	  }
	  if (ids.isEmpty()) {
		 return result;
	  }
	  RouteMapper routeMapper = (RouteMapper) getDomainMapper();
	  Map<Long, ArrayList<RouteTrajectory>> trajectories = new HashMap<Long, ArrayList<RouteTrajectory>>();
	  for (RouteTrajectory trajectory : routeMapper.getRoutesTrajectory(ids)) {
		 if (!trajectories.containsKey(trajectory.getId())) {
			trajectories.put(trajectory.getId(), new ArrayList<RouteTrajectory>());
		 }
		 trajectories.get(trajectory.getId()).add(trajectory);
	  }
	  for (SelectItem item : list) {
		 RouteMapView view = new RouteMapView();
		 view.setId(item.getId());
		 view.setName(item.getName());
		 view.setRouteTrajectories(trajectories.get(item.getId()));
		 result.add(view);
	  }
	  return result;
   }

   /**
	* Обновление количество выходов маршрутов на указаную дату.
	*
	* @param workDate - дата
	*/
   public void updateRouteMoveCount(Date workDate) {
	  try {
		 ((RouteMapper) getDomainMapper()).updateRouteMoveCount(workDate);
	  } catch (Exception e) {
		 String message = "Не удалось обновить количество выходов маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Получает список маршрутов района, которые еще не добавлены для текущего
	* места встречи.
	*
	* @param params параметры
	* @return список маршрутов района
	*/
   public List<SelectItem> getSelectItemsForDistrictAdd(BaseSelectListParams params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = ((RouteMapper) getDomainMapper()).getSelectItemsForDistrictAdd(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получение маршрутов для вкладки районов мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   public List<SelectItem> getLazySelectItemsForVenues(BaseSelectListParams params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = ((RouteMapper) getDomainMapper()).getLazySelectItemsForVenues(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить список ночных маршрутов.
	*
	* @param <Items> тип объектов списка
	* @param params  фильтры
	* @return
	*/
   public <Items extends ISelectItem> List<Items> getNightRoutes(BaseSelectListParams params) {
	  List<Items> sList = null;
	  try {
		 sList = ((RouteMapper) getDomainMapper()).getNightRoutes(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получения списка маршрутов из АСДУ ГТФС для фильтра.
	*
	* @param <Items> тип объектов списка
	* @param params  фильтры
	* @return
	*/
   public <Items extends ISelectItem> List<Items> getGtfsRouteLists(BaseSelectListParams params) {
	  List<Items> sList = null;
	  try {
		 sList = ((RouteMapper) getDomainMapper()).getGtfsRouteLists(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получение маршрутов округа для вкладки добавления сопутствующих маршрутов
	* мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   public List<RouteMapView> getRoutesOfCounty(BaseSelectListParams params) {
	  return fillRouteTrajectoryAndStops(((RouteMapper) getDomainMapper()).getRoutesOfCounty(params.getFilter()),
		  false);
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
	  return fillRouteTrajectoryAndStops(((RouteMapper) getDomainMapper()).getSelectItemsForVenueDistrict(params.getFilter()),
		  false);
   }
}
