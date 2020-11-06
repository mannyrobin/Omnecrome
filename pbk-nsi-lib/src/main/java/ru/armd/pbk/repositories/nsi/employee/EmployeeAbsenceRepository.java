package ru.armd.pbk.repositories.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsence;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.employee.EmployeeAbsenceMapper;

/**
 * Репозиторий для работы со сущностью "Рабочие время сотрудника".
 */
@Repository
public class EmployeeAbsenceRepository
	extends BaseDomainRepository<EmployeeAbsence> {

   public static final Logger LOGGER = Logger.getLogger(EmployeeAbsenceRepository.class);

   @Autowired
   EmployeeAbsenceRepository(EmployeeAbsenceMapper mapper) {
	  super(NsiAuditType.NSI_EMPLOYEE_ABSENCE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}

