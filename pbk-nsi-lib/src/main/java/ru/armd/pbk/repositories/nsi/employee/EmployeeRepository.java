package ru.armd.pbk.repositories.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.PuskPlanningEmployee;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.employee.EmployeeMapper;
import ru.armd.pbk.repositories.nsi.device.history.ContactlessCardHistoryRepository;
import ru.armd.pbk.repositories.nsi.device.history.DvrHistoryRepository;
import ru.armd.pbk.repositories.nsi.device.history.OfficialCardHistoryRepository;
import ru.armd.pbk.repositories.nsi.device.history.PuskHistoryRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Репозиторий сотрудников.
 */
@Repository
public class EmployeeRepository
	extends BaseVersionDomainRepository<Employee> {

   public static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class);

   @Autowired
   private OfficialCardHistoryRepository officialCardHistoryRepository;
   @Autowired
   private ContactlessCardHistoryRepository contactlessCardHistoryRepository;
   @Autowired
   private PuskHistoryRepository puskHistoryRepository;
   @Autowired
   private DvrHistoryRepository dvrHistoryRepository;


   @Autowired
   EmployeeRepository(EmployeeMapper mapper) {
	  super(NsiAuditType.NSI_EMPLOYEE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  params.setOrderBy(params.getOrderBy().equals("commonNumber") ? "puskId" : params.getOrderBy());
	  return super.getGridViews(params);
   }

   /**
	* Получить  сотрудника по заданию.
	*
	* @param taskId ИД задания
	* @return
	*/
   public Employee getEmployeeByTaskId(Long taskId) {
	  Employee domain = null;
	  try {
		 domain = ((EmployeeMapper) getDomainMapper()).getEmployeeByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить домен. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   /**
	* Получить пользователя по устройству.
	*
	* @param contId ИД
	* @param puskId ИД
	* @param offId  ИД
	* @param dvrId  ИД
	* @return
	*/
   public Employee getEmployeeByDeviceId(Long contId, Long puskId, Long offId, Long dvrId) {
	  Employee domain = null;
	  try {
		 domain = ((EmployeeMapper) getDomainMapper()).getEmployeeByDeviceId(contId, puskId, offId, dvrId);
	  } catch (Exception e) {
		 String message = "Не удалось получить домен. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   /**
	* Проверяет возможность восстановления версии сотрудника (проверяет
	* используются ли устрайства из данной версии).
	*
	* @param params параметры
	* @return истина, если устройства уже используются
	*/
   public boolean checkEmployeeVersion(Map<String, Object> params) {
	  boolean result = false;
	  try {
		 result = ((EmployeeMapper) getDomainMapper()).checkEmployeeVersion(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить результат проверки версии сотрудника. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получение предпоследней актуальной версии сотрудника,
	* используется в востановлении сотрудника после увольнения.
	*
	* @param domainId индефикатор сотрудника
	* @return
	*/
   public Employee getLastButOneFireActual(Long domainId) {
	  Employee domain = null;
	  try {
		 domain = ((EmployeeMapper) getDomainMapper()).getLastButOneFireActual(domainId);
	  } catch (Exception e) {
		 String message = "Не удалось получить домен. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   @Override
   public Employee save(Employee domain) {
	  changeDevices(domain);
	  Employee oldDomain = getActual(domain.getHeadId());
	  if (oldDomain != null) domain.setPhoto(oldDomain.getPhoto());
	  return super.save(domain);
   }

   @Override
   public Employee saveVersion(Employee versionDomain) {
	  changeDevices(versionDomain);
	  Employee oldDomain = getActual(versionDomain.getHeadId());
	  if (oldDomain != null) versionDomain.setPhoto(oldDomain.getPhoto());
	  return super.saveVersion(versionDomain);
   }

   protected void changeDevices(Employee newDomain) {
	  Employee oldDomain = getActual(newDomain.getHeadId());
	  if (oldDomain == null) {
		 return;
	  }
	  if (!Objects.equals(newDomain.getContCardId(), oldDomain.getContCardId())) {
		 saveDeviceHistory(contactlessCardHistoryRepository, newDomain.getContCardId(), oldDomain.getContCardId(), newDomain.getHeadId(), newDomain.getVersionStartDate());
	  }
	  if (!Objects.equals(newDomain.getOffCardId(), oldDomain.getOffCardId())) {
		 saveDeviceHistory(officialCardHistoryRepository, newDomain.getOffCardId(), oldDomain.getOffCardId(), newDomain.getHeadId(), newDomain.getVersionStartDate());
	  }
	  if (!Objects.equals(newDomain.getPuskId(), oldDomain.getPuskId())) {
		 saveDeviceHistory(puskHistoryRepository, newDomain.getPuskId(), oldDomain.getPuskId(), newDomain.getHeadId(), newDomain.getVersionStartDate());
	  }
	  if (!Objects.equals(newDomain.getDvrId(), oldDomain.getDvrId())) {
		 saveDeviceHistory(dvrHistoryRepository, newDomain.getDvrId(), oldDomain.getDvrId(), newDomain.getHeadId(), newDomain.getVersionStartDate());
	  }
   }

   protected void saveDeviceHistory(BaseVersionDomainRepository<BaseDeviceOwnerHistoryDomain> deviceRepository, Long newDeviceId, Long oldDeviceId,
									Long employeeId, Date versionStartDate) {
	  if (oldDeviceId != null) {
		 deviceRepository.saveVersion(createDeviceHistory(oldDeviceId, null, versionStartDate));
	  }
	  if (newDeviceId != null) {
		 deviceRepository.saveVersion(createDeviceHistory(newDeviceId, employeeId, versionStartDate));
	  }
   }

   protected BaseDeviceOwnerHistoryDomain createDeviceHistory(Long deviceId, Long employeeId, Date versionStartDate) {
	  BaseDeviceOwnerHistoryDomain domain = new BaseDeviceOwnerHistoryDomain();
	  domain.setVersionStartDate(versionStartDate);
	  domain.setDeviceId(deviceId);
	  domain.setEmployeeId(employeeId);
	  return domain;
   }

   /**
	* Возвращает список сотрудников МГТ, для поля подписант.
	*
	* @param <SelectItem> тип элемента списка.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectItemsForSign(Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = ((EmployeeMapper) getDomainMapper()).getSelectItemsForSign(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }
   public List<PuskPlanningEmployee> getPlanningEmployeeForPusk() {
	   return ((EmployeeMapper) getDomainMapper()).getPlanningEmployeeForPusk();
   }
}
