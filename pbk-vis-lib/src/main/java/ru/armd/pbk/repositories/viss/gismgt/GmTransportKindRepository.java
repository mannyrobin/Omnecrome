package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmTransportKind;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmTransportKindMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ ТС Тип".
 */
@Repository
public class GmTransportKindRepository
	extends BaseDomainRepository<GmTransportKind> {

   public static final Logger LOGGER = Logger.getLogger(GmTransportKindRepository.class);

   @Autowired
   GmTransportKindRepository(GmTransportKindMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_TS_KIND, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
