package ru.armd.pbk.repositories.nsi.department;

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
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.department.DepartmentMapper;
import ru.armd.pbk.repositories.nsi.CountyRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий подразделений.
 */
@Repository
public class DepartmentRepository
	extends BaseVersionDomainRepository<Department> {

   public static final Logger LOGGER = Logger.getLogger(DepartmentRepository.class);

   private DepartmentMapper mapper;

   @Autowired
   private CountyRepository countyRepository;

   @Autowired
   DepartmentRepository(DepartmentMapper mapper) {
	  super(NsiAuditType.NSI_DEPARTMENT, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение списка подразделений.
	*
	* @param params параметры
	* @return
	*/
   @DepartmentAuthorization
   public List<SelectItem> getSelectItemsByName(BaseSelectListParams params) {
	  try {
		 List<SelectItem> departs = mapper.getSelectItemsByName(params.getFilter());
		 return departs;
	  } catch (Exception e) {
		 String message = "Не удалось получить список подразделений. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   @Override
   @DepartmentAuthorization
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectItems(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = getDomainMapper().getSelectItems(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  return super.getGridViews(params);
   }

   @Override
   public Department save(Department domain) {
	  Department dept = super.save(domain);
	  saveCounties(domain, false);
	  return dept;
   }

   @Override
   public Department saveVersion(Department versionDomain) {
	  Department dept = super.saveVersion(versionDomain);
	  dept.setCountyIds(versionDomain.getCountyIds());
	  saveCounties(dept, true);
	  return dept;
   }

   private void saveCounties(Department domain, boolean newVersion) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("deptId", domain.getHeadId());
	  List<County> counties = countyRepository.getDomains(params);
	  List<Long> oldCountyIds = new ArrayList<Long>();
	  for (County c : counties) {
		 oldCountyIds.add(c.getHeadId());
		 if (domain.getCountyIds() == null || !domain.getCountyIds().contains(c.getHeadId())) {
			c.setDepartmentId(null);
			saveCounty(c, domain, newVersion);
		 }
	  }
	  if (domain.getCountyIds() != null) {
		 for (Long cId : domain.getCountyIds()) {
			if (!oldCountyIds.contains(cId)) {
			   County c = countyRepository.getActual(cId);
			   c.setDepartmentId(domain.getHeadId());
			   saveCounty(c, domain, newVersion);
			}
		 }
	  }
   }

   private void saveCounty(County c, Department dept, boolean newVersion) {
	  if (newVersion) {
		 c.setVersionEndDate(dept.getVersionEndDate());
		 c.setVersionStartDate(dept.getVersionStartDate());
		 // c.setId(null);
		 countyRepository.saveVersion(c);
	  } else {
		 countyRepository.save(c);
	  }
   }

   /**
	* Получение подразделения по id расписания.
	*
	* @param scheduleId id расписания
	* @return подразделение
	*/
   public Department getDepartmentByScheduleId(Long scheduleId) {
	  Department depertment = null;
	  try {
		 depertment = mapper.getDepartmentByScheduleId(scheduleId);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, scheduleId, message, e);
		 throw new PBKException(message, e);
	  }
	  return depertment;
   }

   /**
	* Получает список подразделений для фильтра карт.
	*
	* @param params параметры
	* @return список подразделений
	*/
   public List<SelectItem> getSelectItemForMap(BaseSelectListParams params) {
	  try {
		 List<SelectItem> departs = mapper.getSelectItemForMap(params.getFilter());
		 return departs;
	  } catch (Exception e) {
		 String message = "Не удалось получить список подразделений. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   /**
	* Получает подразделение по id места встречи.
	*
	* @param venueId id места встречи
	* @return подразделение
	*/
   public Department getByVenueId(Long venueId) {
	  Department depertment = null;
	  try {
		 depertment = mapper.getByVenueId(venueId);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, venueId, message, e);
		 throw new PBKException(message, e);
	  }
	  return depertment;
   }
}
