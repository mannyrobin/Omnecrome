package ru.armd.pbk.repositories.nsi.venue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.venue.VenueMapper;
import ru.armd.pbk.repositories.nsi.district.DistrictRepository;
import ru.armd.pbk.views.nsi.venue.VenueHistoryView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Репозиторий мест втреч.
 */
@Repository
public class VenueRepository
	extends BaseVersionDomainRepository<Venue> {

   public static final Logger LOGGER = Logger.getLogger(DistrictRepository.class);

   private VenueMapper mapper;

   @Autowired
   private VenueRouteRepository venueRouteRepository;

   @Autowired
   VenueRepository(VenueMapper mapper) {
	  super(NsiAuditType.NSI_VENUE, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Удалить районы у мест встреч.
	*
	* @param ids - ИД связки места встречи и района.
	*/
   public void removeDistricts(List<Long> ids) {
	  for (Long id : ids) {
		 Venue domain = new Venue();
		 domain.setId(id);
		 initUpdaterInfo(domain);
		 domain.setVersionEndDate(new Date());
		 mapper.unlinkDistrict(domain);
	  }
	  venueRouteRepository.unlinkRoutes(ids);
   }

   /**
	* Получение списка мест встреч для отображения в комбобоксах планов.
	*
	* @param params параметры фильтрации.
	* @return список мест встреч объектов.
	*/
   public List<ISelectItem> getSelectItemsForPlan(BaseSelectListParams params) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = mapper.getSelectItemsForPlan(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Прикрепить район к месту встречи.
	*
	* @param dto ДТО
	*/
   public void linkDistrict(Venue dto) {
	  mapper.linkDistrict(dto);
   }

   public List<VenueHistoryView> getVenueDistrictsHistoryViews(BaseGridViewParams params) {
	  List<VenueHistoryView> list = new ArrayList<>(5);
	  try {
		 for (VenueHistoryView view : mapper.getVenueDistrictsHistoryViews(params)) {
			if (view.isActive()) {
			   list.add(view);
			}
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список HistoryView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return list;

   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  return super.getGridViews(params);
   }

   /**
	* Получить список объектов для выпадаюшего списка фильтра райнов места
	* встречи.
	*
	* @param params фильтры.
	* @return список объектов для выпадаюшего списка фильтра райнов места
	* встречи
	*/
   public List<ISelectItem> getSelectDistricts(BaseSelectListParams params) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = mapper.getSelectDistricts(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить список объектов для выпадаюшего списка фильтра маршрутов места
	* встречи.
	*
	* @param params фильтры.
	* @return список объектов для выпадаюшего списка фильтра маршрутов места
	* встречи
	*/
   public List<SelectItem> getSelectVenueDistrictRoutes(BaseSelectListParams params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getSelectVenueDistrictRoutes(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить список объектов для выпадаюшего списка фильтра остановок места
	* встречи.
	*
	* @param params фильтры.
	* @return список объектов для выпадаюшего списка фильтра остановок места
	* встречи
	*/
   public List<SelectItem> getSelectStops(BaseSelectListParams params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = mapper.getSelectStops(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Востановить место встречи после удаления.
	*
	* @param domain место встречи
	* @return
	*/
   public Venue restoreVenue(Venue domain) {
	  Venue result = saveVersion(domain);
	  domain.setIsDelete(0);
	  updateHead(domain);
	  return result;
   }

   @Override
   public Venue getActual(Long headId) {
	  Venue v = super.getActual(headId);
	  List<ISelectItem> depts = mapper.getSelectDepts(v.getHeadId());
	  List<Long> deptIds = new ArrayList<Long>();
	  for (ISelectItem dept : depts) {
		 deptIds.add(dept.getId());
	  }
	  v.setDeptIds(deptIds);
	  return v;
   }

   /**
	* Привязать подразделние к месту встречи.
	*
	* @param dto местовстречи
	*/
   public void linkDepartment(Venue dto) {
	  mapper.linkDepartment(dto);
   }

   /**
	* Отвязать подразделение от места встречи.
	*
	* @param dto местовстречи
	*/
   public void unlinkDepartment(Venue dto) {
	  mapper.unlinkDepartment(dto);
   }

   public List<ISelectItem> getSelectDepts(Long venueId) {
	  return mapper.getSelectDepts(venueId);
   }
}