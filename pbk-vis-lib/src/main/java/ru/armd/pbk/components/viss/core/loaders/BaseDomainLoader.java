package ru.armd.pbk.components.viss.core.loaders;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.exceptions.PBKException;

/**
 * Базовый загрузчик для доменых сущностей.
 *
 * @param <Domain>
 */
public abstract class BaseDomainLoader<Domain extends BaseDomain>
	extends BaseLoader {

   public static final Logger LOGGER = Logger.getLogger(BaseDomainLoader.class);

   protected IDomainRepository<Domain> domainRepository;

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   public BaseDomainLoader(IDomainRepository<Domain> domainRepository) {
	  this.domainRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected Domain importDomain(Domain newDomain) {
	  Domain existedDomain = getExistedDomain(newDomain);
	  if (existedDomain != null) {
		 updateDomain(newDomain, existedDomain);
	  }
	  return save(newDomain);
   }

   protected abstract Domain createDomain(String[] fields);

   protected abstract Domain getExistedDomain(Domain newDomain);

   protected void updateDomain(Domain newDomain, Domain existedDomain) {
	  newDomain.setId(existedDomain.getId());
	  newDomain.setCreateUserId(existedDomain.getCreateUserId());
	  newDomain.setCreateDate(existedDomain.getCreateDate());
   }

   protected Domain save(Domain domain) {
	  if (domainRepository == null) {
		 throw new PBKException("Репозиторий не задан");
	  }
	  return domainRepository.save(domain);
   }

}
