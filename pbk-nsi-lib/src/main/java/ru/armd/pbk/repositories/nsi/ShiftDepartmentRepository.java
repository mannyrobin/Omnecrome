package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.ShiftDepartment;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.ShiftDepartmentMapper;

/**
 * Репозиторий "Смены подразделения".
 */
@Repository
public class ShiftDepartmentRepository
	extends BaseDomainRepository<ShiftDepartment> {

   public static final Logger LOGGER = Logger.getLogger(ShiftDepartmentRepository.class);

   @Autowired
   ShiftDepartmentRepository(ShiftDepartmentMapper mapper) {
	  super(NsiAuditType.NSI_SHIFT_DEPARTMENT, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
