package ru.armd.pbk.services.nsi.department;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.dto.nsi.department.DepartmentDTO;
import ru.armd.pbk.matcher.nsi.IDepartmentMatcher;
import ru.armd.pbk.repositories.nsi.CountyRepository;
import ru.armd.pbk.repositories.nsi.department.DepartmentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис подразделений.
 */
@Service
public class DepartmentService
	extends BaseVersionDomainService<Department, DepartmentDTO> {

   private static final Logger LOGGER = Logger.getLogger(DepartmentService.class);

   private DepartmentRepository repository;

   @Autowired
   private CountyRepository countyRepository;

   @Autowired
   DepartmentService(DepartmentRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Department toDomain(DepartmentDTO dto) {
	  return IDepartmentMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public DepartmentDTO toDTO(Department domain) {
	  return IDepartmentMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получение списка подразделений.
	*
	* @param params параметры
	* @return
	*/
   public List<SelectItem> getSelectItemsByName(BaseSelectListParams params) {
	  return repository.getSelectItemsByName(params);
   }

   @Override
   public DepartmentDTO saveDTO(DepartmentDTO dto) {
	  return super.saveDTO(dto);

   }

   @Override
   public DepartmentDTO getDTOById(Long id) {
	  DepartmentDTO dto = super.getDTOById(id);
	  if (dto != null) {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("deptId", id);
		 List<County> counties = countyRepository.getDomains(params);
		 List<Long> ids = new ArrayList<>();
		 if (counties != null) {
			for (County si : counties) {
			   ids.add(si.getHeadId());
			}
		 }
		 dto.setCountyIds(ids);
	  }
	  return dto;
   }

   /**
	* Получение подразделения по id расписания.
	*
	* @param scheduleId id расписания
	* @return подразделение
	*/
   @Transactional
   public DepartmentDTO getDepartmentByScheduleId(Long scheduleId) {
	  Department domain = repository.getDepartmentByScheduleId(scheduleId);
	  DepartmentDTO dto = toDTO(domain);
	  return dto;
   }

   @Transactional
   @Override
   public DepartmentDTO restoreVersionDTO(Long id) {
	  DepartmentDTO dto = toDTO(versionDomainRepository.getById(id));
	  if (dto == null) {
		 return null;
	  }
	  if (dto != null) {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("deptId", dto.getHeadId());
		 List<County> counties = countyRepository.getDomains(params);
		 List<Long> ids = new ArrayList<>();
		 if (counties != null) {
			for (County si : counties) {
			   ids.add(si.getHeadId());
			}
		 }
		 dto.setCountyIds(ids);
	  }
	  dto.setId(null);
	  dto.setVersionStartDate(new Date());
	  return saveVersionDTO(dto);
   }

   /**
	* Получает список подразделений для фильтра карт.
	*
	* @param params параметры
	* @return список подразделений
	*/
   public List<SelectItem> getSelectItemForMap(BaseSelectListParams params) {
	  return repository.getSelectItemForMap(params);
   }

   /**
	* Получает подразделение по id места встречи.
	*
	* @param venueId id места встречи
	* @return подразделение
	*/
   @Transactional
   public DepartmentDTO getByVenueId(Long venueId) {
	  Department domain = repository.getByVenueId(venueId);
	  DepartmentDTO dto = toDTO(domain);
	  return dto;
   }
}
