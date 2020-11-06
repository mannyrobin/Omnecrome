package ru.armd.pbk.repositories.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsenceType;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.employee.EmployeeAbsenceTypeMapper;

/**
 * Репозиторий для работы со сущностью "Рабочие время сотрудника".
 */
@Repository
public class EmployeeAbsenceTypeRepository
	extends BaseDomainRepository<EmployeeAbsenceType> {

   public static final Logger LOGGER = Logger.getLogger(EmployeeAbsenceTypeRepository.class);

   @Autowired
   EmployeeAbsenceTypeRepository(EmployeeAbsenceTypeMapper mapper) {
	  super(NsiAuditType.NSI_EMPLOYEE_ABSENCE_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}