package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.Gkuop;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.GkuopMapper;

/**
 * Репозиторий ГКУ ОП.
 */
@Repository
public class GkuopRepository
	extends BaseVersionDomainRepository<Gkuop> {

   public static final Logger LOGGER = Logger.getLogger(GkuopRepository.class);

   @Autowired
   GkuopRepository(GkuopMapper mapper) {
	  super(NsiAuditType.NSI_GKUOP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
