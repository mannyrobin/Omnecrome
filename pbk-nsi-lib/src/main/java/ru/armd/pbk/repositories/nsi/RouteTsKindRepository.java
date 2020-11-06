package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.RouteTsKind;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.RouteTsKindMapper;

/**
 * Репозиторий типов ТС маршрутов.
 */
@Repository
public class RouteTsKindRepository
	extends BaseDomainRepository<RouteTsKind> {

   public static final Logger LOGGER = Logger.getLogger(CountyRepository.class);

   @Autowired
   RouteTsKindRepository(RouteTsKindMapper mapper) {
	  super(NsiAuditType.NSI_ROUTE_KIND, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
