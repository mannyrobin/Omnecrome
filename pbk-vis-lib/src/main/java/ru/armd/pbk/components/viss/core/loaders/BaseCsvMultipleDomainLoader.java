package ru.armd.pbk.components.viss.core.loaders;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.utils.ImportResult;
import ru.armd.pbk.utils.date.DateUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Предоставлят возможность множественной загрузки из CSV файла.
 * Данный загрузчик не проверяет на существование записи.
 *
 * @param <Domain>
 */
public abstract class BaseCsvMultipleDomainLoader<Domain extends BaseDomain>
	extends BaseMultipleDomainLoader<Domain> {

   public static final String FIELD_SEPARATOR = ";";

   private String charsetName = "UTF-8";

   /**
	* Конструктор. Принимает в качестве агрументов
	* репозиторий и количество элементов, которое
	* будут загружены за один insert.
	*
	* @param domainRepository репозиторий.
	* @param count            количество элементов.
	*/
   public BaseCsvMultipleDomainLoader(IDomainRepository<Domain> domainRepository, int count) {
	  super(domainRepository, count);
   }

   /**
	* Конструктор, который дополнительно позволяет указать кодировку файла.
	*
	* @param domainRepository репозиторий.
	* @param count            количество элементов.
	* @param charsetName      кодировка.
	*/
   public BaseCsvMultipleDomainLoader(IDomainRepository<Domain> domainRepository, int count, String charsetName) {
	  super(domainRepository, count);
	  this.charsetName = charsetName;
   }

   public AuditType getAuditType() {
	  return VisAuditType.VIS_VIS;
   }

   /**
	* Загрузить данные из входного потока {@code is}.
	*
	* @param is входной поток.
	* @return
	*/
   public ImportResult<Domain> importFile(InputStream is) {
	  ImportResult<Domain> importResult = new ImportResult<Domain>();
	  try {
		 BufferedReader brFile = new BufferedReader(new InputStreamReader(is, charsetName));
		 String line = null;
		 int i = 0;
		 while ((i < getSkipRow()) && (line = brFile.readLine()) != null) {
			i++;
		 }
		 while ((line = brFile.readLine()) != null) {
			doProcessLine(line, importResult);
			importResult.setAllCount(importResult.getAllCount() + 1);
		 }
		 save(importResult);
		 afterLastRow();
	  } catch (Exception e) {
		 throw new PBKException(e.getMessage(), e);
	  }
	  return importResult;
   }

   /**
	* Запустить имопрт файла.
	*
	* @param is   поток
	* @param date дата
	* @return
	*/
   public ImportResult<Domain> importFile(InputStream is, Date date) {
	  setWorkDate(DateUtils.shiftToDayStart(date));
	  return importFile(is);
   }

   protected void doProcessLine(String line, ImportResult<Domain> importResult) {
	  Domain newDomain = null;
	  try {
		 doProcessFields(line.split(FIELD_SEPARATOR, -1), importResult);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, newDomain, e.getMessage(), e);
	  }
	  importResult.setProcessingCount(importResult.getProcessingCount() + 1);
   }

   protected void doProcessFields(String[] fields, ImportResult<Domain> importResult) {
	  importDomain(createDomain(fields), importResult);
   }

   /**
	* Метод, который будет вызван после обработки последней строки файла.
	*/
   protected void afterLastRow() {

   }

   /**
	* Возвращает количество строк, которые будет пропущены в начале файла.
	* Данный метод необходимо переопределить, если необходимо пропустить
	* количество строк, которые отличаются от 0.
	*
	* @return
	*/
   protected int getSkipRow() {
	  return 0;
   }
}
