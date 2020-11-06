package ru.armd.pbk.repositories.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.employee.EmployeeLastUpdate;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.employee.EmployeeLastUpdateMapper;

@Repository
public class EmployeeLastUpdateRepository
	extends BaseDomainRepository<EmployeeLastUpdate> {

   public static final Logger LOGGER = Logger.getLogger(EmployeeLastUpdateRepository.class);

   @Autowired
   EmployeeLastUpdateRepository(EmployeeLastUpdateMapper mapper) {
	  super(NsiAuditType.NSI_EMPLOYEE_LAST_UPDATE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
