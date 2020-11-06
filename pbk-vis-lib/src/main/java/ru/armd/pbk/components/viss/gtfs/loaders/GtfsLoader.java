package ru.armd.pbk.components.viss.gtfs.loaders;

import ru.armd.pbk.components.viss.core.loaders.BaseCsvMultipleDomainLoader;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.utils.ImportResult;
import ru.armd.pbk.utils.date.DateUtils;

import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

/**
 * Базовый загрузчик для ГТФС.
 *
 * @param <Domain>
 */
public abstract class GtfsLoader<Domain extends BaseDomain>
	extends BaseCsvMultipleDomainLoader<Domain> {

   private static final Integer COUNT_CACHE = 150;

   private Boolean isOneDateTable;
   private Date date;

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   public GtfsLoader(IDomainRepository<Domain> domainRepository, Boolean isOneDateTable) {
	  super(domainRepository, COUNT_CACHE);
	  this.isOneDateTable = isOneDateTable;
   }

   @Override
   public ImportResult<Domain> importFile(InputStream is, Date date) {
	  this.date = date;
	  return super.importFile(is, isOneDateTable ? date : null);
   }

   protected Boolean filter(String field, DateFormat df) {
	  Date fd = getDateValue(field, df);
	  if (fd == null || !getWorkDate().equals(DateUtils.shiftToDayStart(fd))) {
		 return false;
	  }
	  return true;
   }

   @Override
   protected int getSkipRow() {
	  return 1;
   }

   @Override
   protected Date getWorkDate() {
	  return isOneDateTable ? super.getWorkDate() : date;
   }

}
