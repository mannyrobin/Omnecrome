package ru.armd.pbk.services.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.EmployeeDepartmentMove;
import ru.armd.pbk.domain.nsi.employee.PuskPlanningEmployee;
import ru.armd.pbk.dto.nsi.employee.EmployeeDTO;
import ru.armd.pbk.enums.core.SexTypes;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.mappers.nsi.employee.EmployeeDepartmentMoveMapper;
import ru.armd.pbk.mappers.nsi.employee.EmployeeMapper;
import ru.armd.pbk.matcher.nsi.IEmployeeMatcher;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис сотрудников.
 */
@Service
public class EmployeeService
	extends
	BaseVersionDomainService<Employee, EmployeeDTO> {

   private static final Logger LOGGER = Logger
	   .getLogger(EmployeeService.class);

   @Autowired
   EmployeeDepartmentMoveMapper emplDeptMoveMapper;

   private EmployeeRepository repository;

   @Autowired
   private EmployeeMapper employeeMapper;

   @Autowired
   EmployeeService(EmployeeRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public List<SelectItem> getSexTypes() {
	  return SexTypes.getSelItems();
   }

   @Override
   public Employee toDomain(EmployeeDTO dto) {
	  return IEmployeeMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public EmployeeDTO toDTO(Employee domain) {
	  EmployeeDTO dto = IEmployeeMatcher.INSTANCE.toDTO(domain);
	  EmployeeDepartmentMove edm = emplDeptMoveMapper.
		  getEmplDeptMoveByEmployeeAndDept(dto.getHeadId(), dto.getDepartmentId());
	  if (edm != null && edm.getId() != null) {
		 dto.setPeriodStartDate(edm.getPeriodStartDate());
		 dto.setPeriodEndDate(edm.getPeriodEndDate());
	  }
	  return dto;
   }

   /**
	* Получение сотрудника по заданию.
	*
	* @param taskId ИД задания
	* @return
	*/
   @Transactional
   public EmployeeDTO getEmployeeByTaskId(Long taskId) {
	  return toDTO(repository.getEmployeeByTaskId(taskId));
   }

   @Override
   @Transactional
   public EmployeeDTO restoreVersionDTO(Long id) {
	  EmployeeDTO dto = toDTO(versionDomainRepository.getById(id));
	  if (dto == null) {
		 return null;
	  }

	  Map<String, Object> filter = new HashMap<>();
	  if (dto.getPuskId() != null) {
		 filter.put("puskId", dto.getPuskId());
		 filter.put("currentId", dto.getId());
		 if (repository.checkEmployeeVersion(filter)) {
			throw new PBKValidationException("puskId",
				"ПУсК из выбранной версии уже используется другим сотрудником!");
		 }
	  }
	  if (dto.getDvrId() != null) {
		 filter.clear();
		 filter.put("dvrId", dto.getDvrId());
		 filter.put("currentId", dto.getId());
		 if (repository.checkEmployeeVersion(filter)) {
			throw new PBKValidationException("dvrId",
				"Видеорегистратор из выбранной версии уже используется другим сотрудником!");
		 }
	  }
	  if (dto.getContCardId() != null) {
		 filter.clear();
		 filter.put("contactlessCardId", dto.getContCardId());
		 filter.put("currentId", dto.getId());
		 if (repository.checkEmployeeVersion(filter)) {
			throw new PBKValidationException("contCardId",
				"БСК из выбранной версии уже используется другим сотрудником!");
		 }
	  }
	  if (dto.getOffCardId() != null) {
		 filter.clear();
		 filter.put("officialCardId", dto.getOffCardId());
		 filter.put("currentId", dto.getId());
		 if (repository.checkEmployeeVersion(filter)) {
			throw new PBKValidationException("offCardId",
				"ССК из выбранной версии уже используется другим сотрудником!");
		 }
	  }
	  dto.setId(null);
	  dto.setVersionStartDate(new Date());
	  return saveVersionDTO(dto);
   }

   public List<RouteSelectItem> getEmployeesOnDate(Long taskId) {
	  return employeeMapper.getEmployeesOnDate(taskId);
   }

   /**
	* Возвращает список сотрудников МГТ, для поля подписант.
	*
	* @param <SelectItem> тип элемента списка.
	* @param <Params>     тип параметров.
	* @param params       параметры.
	* @return
	*/
   @Transactional
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectItemsForSign(Params params) {
	  return repository.getSelectItemsForSign(params);
   }

   @Transactional
   public Employee getPhotoByDomainId(Long id) {
   		return employeeMapper.getPhotoByDomainId(id);
   }

   @Transactional
   public void deletePhoto(Long id) {
   		Employee employee = employeeMapper.getActual(id);
   		if (employee != null) employeeMapper.deletePhoto(employee.getId());
   }

	@Transactional
	public void addPhoto(Long id, byte[] photo) throws IOException {
		Employee employee = employeeMapper.getActual(id);
		if (employee != null) employeeMapper.updatePhoto(employee.getId(), photo);
	}

	@Transactional
	public List<PuskPlanningEmployee> getPlanningEmployeeForPusk() {
		return repository.getPlanningEmployeeForPusk();
	}

}
