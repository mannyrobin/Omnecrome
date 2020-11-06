package ru.armd.pbk.repositories.nsi.stop;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.stop.StopMapper;

/**
 * Репозиторий остановок.
 */
@Repository
public class StopRepository
	extends BaseVersionDomainRepository<Stop> {

   public static final Logger LOGGER = Logger.getLogger(StopRepository.class);

   private StopMapper stopMapper;

   @Autowired
   StopRepository(StopMapper mapper) {
	  super(NsiAuditType.NSI_STOP, mapper);
	  this.stopMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public Stop updateAsduId(Stop domain) {
	  try {
		 initUpdaterInfo(domain);
		 stopMapper.updateAsduId(domain);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, domain, "Успешное обновление записи", null);
	  } catch (Exception e) {
		 String message = "Не удалось обновить запись. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, domain, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

}
