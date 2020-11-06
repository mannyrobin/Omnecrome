package ru.armd.pbk.components.viss.core.loaders;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

/**
 * Базовый класс импортера файлов.
 */
public abstract class BaseFileDomainLoader<Domain extends BaseDomain>
	extends BaseDomainLoader<Domain> {
   public static final Logger LOGGER = Logger.getLogger(BaseFileDomainLoader.class);

   public static final String FIELD_SEPARATOR = ";";

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий домена.
	*/
   public BaseFileDomainLoader(IDomainRepository<Domain> domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected abstract ImportResult<Domain> importFile(InputStream is);

   protected abstract void doProcessLine(String line, ImportResult<Domain> importResult);
}
