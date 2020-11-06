package ru.armd.pbk.components.viss.core.loaders;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;

/**
 * Загрузчик для массива.
 *
 * @param <Domain> - домен.
 */
public abstract class BaseArrayDomainLoader<Domain extends BaseDomain>
	extends BaseDomainLoader<Domain> {

   private static final Logger LOGGER = Logger.getLogger(BaseArrayDomainLoader.class);

   /**
	* Конструктор.
	*
	* @param domainRepository - репозиторий.
	*/
   public BaseArrayDomainLoader(IDomainRepository<Domain> domainRepository) {
	  super(domainRepository);
   }

   public AuditType getAuditType() {
	  return VisAuditType.VIS_VIS;
   }


   /**
	* Загрузить массив.
	*
	* @param fields - поля.
	* @param fks    - ключи.
	* @return
	*/
   public Domain importArray(String[] fields, Long[] fks) {
	  Domain newDomain = null;
	  try {
		 newDomain = createDomain(fields);
		 updateFKeys(newDomain, fks);
		 importDomain(newDomain);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, newDomain, e.getMessage(), e);
		 throw new PBKException("Ошибка импорта домена", e);
	  }

	  return newDomain;
   }

   protected abstract void updateFKeys(Domain newDomain, Long[] fks);
}
