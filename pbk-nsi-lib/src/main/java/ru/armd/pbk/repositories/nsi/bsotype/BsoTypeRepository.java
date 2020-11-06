package ru.armd.pbk.repositories.nsi.bsotype;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.bsotype.BsoType;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.bsotype.BsoTypeMapper;

/**
 * Репозиторий типов БСО.
 */
@Repository
public class BsoTypeRepository
	extends BaseDomainRepository<BsoType> {

   public static final Logger LOGGER = Logger.getLogger(BsoTypeRepository.class);

   @Autowired
   BsoTypeRepository(BsoTypeMapper mapper) {
	  super(NsiAuditType.NSI_BSO_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
