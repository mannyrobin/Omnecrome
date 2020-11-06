package ru.armd.pbk.components.viss.core.loaders;

import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.utils.ImportResult;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Предоставлят возможность множественной загрузки.
 * Данный загрузчик не проверяет на существование записи.
 *
 * @param <Domain>
 */
public abstract class BaseMultipleDomainLoader<Domain extends BaseDomain>
	extends BaseLoader {

   public static final Logger LOGGER = Logger.getLogger(BaseMultipleDomainLoader.class);

   public static Object lock = new Object();

   protected IDomainRepository<Domain> domainRepository;

   private int count;
   private List<Domain> cacheQueue = null;
   private Date workDate;

   /**
	* Конструктор. Принимает в качестве агрументов
	* репозиторий и количество элементов, которое
	* будут загружены за один insert.
	*
	* @param domainRepository репозиторий.
	* @param count            количество элементов.
	*/
   public BaseMultipleDomainLoader(IDomainRepository<Domain> domainRepository, int count) {
	  this.domainRepository = domainRepository;
	  this.count = count;
	  this.cacheQueue = new LinkedList<>();
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected void importDomain(Domain newDomain, ImportResult<Domain> importResult) {
	  if (newDomain == null) {
		 return;
	  }
	  cacheQueue.add(newDomain);
	  if (cacheQueue.size() >= count) {
		 save(importResult);
	  }
   }

   protected abstract Domain createDomain(String[] fields);

   protected String createSql(Domain domain) {
	  throw new UnsupportedOperationException(
		  "Метод createSql для класса " + this.getClass().getName() + " не реализован.");
   }

   protected void save(ImportResult<Domain> importResult) {
	  try {
		 if (cacheQueue.size() > 0) {
			domainRepository.save(cacheQueue, workDate);
			importResult.setSuccessCount(importResult.getSuccessCount() + cacheQueue.size());
		 }
	  } catch (Exception e) {
		 LOGGER.error(e.getMessage(), e);
	  } finally {
		 cacheQueue.clear();
	  }
   }

   protected Date getWorkDate() {
	  return workDate;
   }

   protected void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

}
