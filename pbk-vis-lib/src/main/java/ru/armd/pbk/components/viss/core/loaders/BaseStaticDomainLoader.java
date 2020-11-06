package ru.armd.pbk.components.viss.core.loaders;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;

public abstract class BaseStaticDomainLoader<Domain extends BaseDomain>
	extends BaseDomainLoader<Domain> {

   private static final Logger LOGGER = Logger.getLogger(BaseStaticDomainLoader.class);

   public BaseStaticDomainLoader(IDomainRepository<Domain> domainRepository) {
	  super(domainRepository);
   }

   public AuditType getAuditType() {
	  return VisAuditType.VIS_VIS;
   }

   public Domain importDomain() {
	  Domain domain = null;
	  try {
		 domain = getExistedDomain(null);
		 if (domain == null) {
			domain = createDomain(null);
			save(domain);
		 }
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, domain, e.getMessage(), e);
		 throw new PBKException("Ошибка импорта домена", e);
	  }

	  return domain;
   }
}
