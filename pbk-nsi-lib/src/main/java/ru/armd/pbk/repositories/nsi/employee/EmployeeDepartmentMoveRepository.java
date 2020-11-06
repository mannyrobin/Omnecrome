package ru.armd.pbk.repositories.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.employee.EmployeeDepartmentMove;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.employee.EmployeeDepartmentMoveMapper;

/**
 * Репозиторий для работы со сущностью "Рабочие время сотрудника".
 */
@Repository
public class EmployeeDepartmentMoveRepository
	extends BaseDomainRepository<EmployeeDepartmentMove> {

   public static final Logger LOGGER = Logger.getLogger(EmployeeDepartmentMoveRepository.class);

   @Autowired
   EmployeeDepartmentMoveRepository(EmployeeDepartmentMoveMapper mapper) {
	  super(NsiAuditType.NSI_EMPLOYEE_DEPT_MOVE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение последнего подразделения, в котором работает сотрудник.
	*
	* @param employeeId ИД сотрудника.
	* @return
	*/
   public EmployeeDepartmentMove getLastDepartmentMove(Long employeeId) {
	  EmployeeDepartmentMove result = null;
	  try {
		 result = ((EmployeeDepartmentMoveMapper) getDomainMapper()).getLastDepartmentMove(employeeId);
	  } catch (Exception e) {
		 String message = "Не удалось получить последнее подразделениее. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, employeeId, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

}