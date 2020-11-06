package ru.armd.pbk.repositories.nsi.department;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.department.DepartmentPark;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.department.DepartmentParkMapper;

/**
 * Репозиторий для работы с парками депортаментов.
 */
@Repository
public class DepartmentParkRepository
	extends BaseVersionDomainRepository<DepartmentPark> {

   public static final Logger LOGGER = Logger.getLogger(DepartmentParkRepository.class);

   @Autowired
   DepartmentParkRepository(DepartmentParkMapper mapper) {
	  super(NsiAuditType.NSI_DEPARTMENT_PARK, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
