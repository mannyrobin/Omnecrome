package ru.armd.pbk.services.nsi.venue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.dto.nsi.district.DistrictVenueDTO;
import ru.armd.pbk.dto.nsi.venue.VenueDTO;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.matcher.nsi.IVenueMatcher;
import ru.armd.pbk.repositories.nsi.district.DistrictRepository;
import ru.armd.pbk.repositories.nsi.venue.VenueRepository;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.nsi.venue.VenueHistoryView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Сервис мест встреч.
 */
@Service
public class VenueService
	extends BaseVersionDomainService<Venue, VenueDTO> {

   private static final Logger LOGGER = Logger.getLogger(VenueService.class);

   private VenueRepository repository;

   @Autowired
   private DistrictRepository districtRepository;

   @Autowired
   VenueService(VenueRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Venue toDomain(VenueDTO dto) {
	  return IVenueMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public VenueDTO toDTO(Venue domain) {
	  return IVenueMatcher.INSTANCE.toDTO(domain, districtRepository.getActual(domain.getDistrictId()));
   }

   /**
	* Удалить районы.
	*
	* @param ids ИД районов.
	*/
   @Transactional
   public void removeDistricts(List<Long> ids) {
	  repository.removeDistricts(ids);
   }

   /**
	* Добавить район.
	*
	* @param dto дто
	*/
   @Transactional
   public void addDistrict(DistrictVenueDTO dto) {
	  Venue domain = repository.getById(dto.getVenueId());
	  domain.setDistrictId(dto.getDistrictId());
	  repository.save(domain);
   }

   /**
	* Прикрепить район к месту встречи.
	*
	* @param dto дто.
	*/
   @Transactional
   public void linkDistrict(DistrictVenueDTO dto) {
	  Venue domain = repository.getActual(dto.getVenueId());
	  domain.setDistrictId(dto.getDistrictId());
	  domain.setCreateDate(null);
	  domain.setCreateUserId(null);
	  initCreaterInfo(domain);
	  initUpdaterInfo(domain);
	  domain.setVersionStartDate(new Date());
	  domain.setVersionEndDate(DateUtils.getVersionEndDate());
	  repository.linkDistrict(domain);
   }

   /**
	* Получение списка мест встреч для отображения в комбобоксах планов.
	*
	* @param params параметры фильтрации.
	* @return список мест встреч объектов.
	*/
   public List<ISelectItem> getSelectItemsForPlan(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = repository.getSelectItemsForPlan(params);
	  return selectItemList;
   }

   @Transactional
   public JsonGridData getVenueDistrictsHistoryViews(BaseGridViewParams params) {
	  List<VenueHistoryView> views = repository.getVenueDistrictsHistoryViews(params);
	  return createJsonGridData(views, params.getPage(), params.getCount());
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
	  List<ISelectItem> selectItemList = repository.getSelectDistricts(params);
	  return selectItemList;
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
	  List<SelectItem> selectItemList = repository.getSelectVenueDistrictRoutes(params);
	  return selectItemList;
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
	  List<SelectItem> selectItemList = repository.getSelectStops(params);
	  return selectItemList;
   }

   /**
	* Востановить место встречи после удаления.
	*
	* @param dto - ДТО места встречи.
	* @return
	*/
   @Transactional
   public VenueDTO restoreVenue(Long id, Date versionStartDate) {
	  Venue actual = versionDomainRepository.getActual(id);
	  if (actual != null && actual.getVersionStartDate().after(versionStartDate)) {
		 throw new PBKValidationException("version start date",
			 "Дата начала новой версии должна быть позже даты начала актуальной!");
	  }
	  actual.setVersionStartDate(versionStartDate);
	  Venue resultDomain = repository.restoreVenue(actual);
	  return toDTO(resultDomain);
   }

   @Override
   public VenueDTO saveVersionDTO(VenueDTO dto) {
	  List<ISelectItem> oldDepts = dto.getHeadId() == null ? new ArrayList<ISelectItem>()
		  : repository.getSelectDepts(dto.getHeadId());
	  dto = super.saveVersionDTO(dto);
	  processVenueDepts(dto, oldDepts, dto.getVersionStartDate());
	  return dto;
   }

   @Transactional
   @Override
   public VenueDTO saveDTO(VenueDTO dto) {
	  List<ISelectItem> oldDepts = dto.getHeadId() == null ? new ArrayList<ISelectItem>()
		  : repository.getSelectDepts(dto.getHeadId());
	  dto = super.saveDTO(dto);
	  processVenueDepts(dto, oldDepts, new Date());
	  return dto;
   }

   private void processVenueDepts(VenueDTO dto, List<ISelectItem> oldDepts, Date versionDate) {
	  for (ISelectItem oldDept : oldDepts) {
		 if (dto.getDeptIds() != null && dto.getDeptIds().contains(oldDept.getId())) {
			continue;
		 }
		 Venue v = new Venue();
		 v.setHeadId(dto.getHeadId());
		 v.setDeptId(oldDept.getId());
		 v.setVersionEndDate(versionDate);
		 initUpdaterInfo(v);
		 repository.unlinkDepartment(v);
	  }
	  if (dto.getDeptIds() != null) {
		 for (Long newDept : dto.getDeptIds()) {
			boolean b = false;
			for (ISelectItem oldDept : oldDepts) {
			   if (newDept.equals(oldDept.getId())) {
				  b = true;
				  break;
			   }
			}
			if (!b) {
			   Venue v = new Venue();
			   v.setHeadId(dto.getHeadId());
			   v.setDeptId(newDept);
			   initCreaterInfo(v);
			   initUpdaterInfo(v);
			   v.setVersionStartDate(versionDate);
			   v.setVersionEndDate(DateUtils.getVersionEndDate());
			   repository.linkDepartment(v);
			}
		 }
	  }
   }
}