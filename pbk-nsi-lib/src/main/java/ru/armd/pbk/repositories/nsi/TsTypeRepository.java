package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.TsTypeMapper;

/**
 * Репозиторй Типы ТС.
 */
@Repository
public class TsTypeRepository
	extends BaseDomainRepository<TsType> {

   public static final Logger LOGGER = Logger.getLogger(TsTypeRepository.class);

   @Autowired
   TsTypeRepository(TsTypeMapper mapper) {
	  super(NsiAuditType.NSI_TS_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
