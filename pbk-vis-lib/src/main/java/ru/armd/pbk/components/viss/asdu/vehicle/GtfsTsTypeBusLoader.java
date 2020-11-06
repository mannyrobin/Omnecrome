package ru.armd.pbk.components.viss.asdu.vehicle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseStaticDomainLoader;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.TsTypeRepository;

/**
 * Лоадер для ТС.
 */
@Component
public class GtfsTsTypeBusLoader
	extends BaseStaticDomainLoader<TsType> {

   private static final Logger LOGGER = Logger.getLogger(GtfsTsTypeBusLoader.class);

   private TsTypeRepository tsTypeRepository;

   @Autowired
   GtfsTsTypeBusLoader(TsTypeRepository domainRepository) {
	  super(domainRepository);
	  tsTypeRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_VENICLE;
   }

   @Override
   protected TsType createDomain(String[] fields) {
	  TsType tsType = new TsType();
	  initCreaterInfo(tsType);
	  initUpdaterInfo(tsType);
	  tsType.setCod("1");
	  tsType.setName("Автобус");
	  tsType.setDescription("Тип ТС, созданный автоматически при импорте справочника ТС");

	  return tsType;
   }

   @Override
   protected TsType getExistedDomain(TsType newDomain) {
	  return tsTypeRepository.getByCode("1");
   }
}
