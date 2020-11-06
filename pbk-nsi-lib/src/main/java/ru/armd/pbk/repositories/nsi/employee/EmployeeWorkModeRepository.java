package ru.armd.pbk.repositories.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.employee.EmployeeWorkModeMapper;

/**
 * Репозиторий для работы со сущностью "Рабочeе время сотрудника".
 */
@Repository
public class EmployeeWorkModeRepository
	extends BaseDomainRepository<EmployeeWorkMode> {

   public static final Logger LOGGER = Logger.getLogger(EmployeeWorkModeRepository.class);

   @Autowired
   EmployeeWorkModeRepository(EmployeeWorkModeMapper mapper) {
	  super(NsiAuditType.NSI_EMPLOYEE_WORK_MODE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}