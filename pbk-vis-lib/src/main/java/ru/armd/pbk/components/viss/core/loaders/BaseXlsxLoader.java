package ru.armd.pbk.components.viss.core.loaders;

import com.aspose.cells.Row;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Базовый лоадер для xlsx файлов.
 *
 * @param <Domain> - домен.
 */
public abstract class BaseXlsxLoader<Domain extends BaseDomain>
	extends BaseDomainLoader<Domain> {

   private static final Logger LOGGER = Logger.getLogger(BaseXlsxLoader.class);

   private AuditType auditType;

   /**
	* Конструктор.
	*
	* @param domainRepository - репозиторий.
	* @param auditType        - тип аудита.
	*/
   public BaseXlsxLoader(IDomainRepository<Domain> domainRepository, AuditType auditType) {
	  super(domainRepository);
	  this.auditType = auditType;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public AuditType getAuditType() {
	  return auditType;
   }

   /**
	* Имортировать файл.
	*
	* @param is - входной поток файла.
	* @return
	*/
   public ImportResult<Domain> importFile(InputStream is) {
	  ImportResult<Domain> importResult = new ImportResult<Domain>();
	  try {
		 Workbook workbook = new Workbook(is);
		 Iterator<?> pageIterator = workbook.getWorksheets().iterator();
		 for (int j = 0; j < getPage(); ++j) {
			if (pageIterator.hasNext()) {
			   pageIterator.next(); //Пропускаем первые страницы.
			}
		 }
		 while (pageIterator.hasNext()) {
			Worksheet worksheet = (Worksheet) pageIterator.next();
			if (!checkPageName(worksheet.getName())) {
			   continue;
			}
			Iterator<?> iterator = worksheet.getCells().getRows().iterator();
			for (int i = 0; i < getFirstSkipRows(); ++i) {
			   if (iterator.hasNext()) {
				  iterator.next(); //Пропускаем первую строку.
			   }
			}
			while (iterator.hasNext()) {
			   doProcessRow((Row) iterator.next(), importResult);
			   importResult.setAllCount(importResult.getAllCount() + 1);
			}
			afterLastRow();
		 }
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, null, e.getMessage(), e);
	  }
	  return importResult;
   }

   protected abstract Domain createDomain(Row row);

   @Override
   protected Domain createDomain(String[] fields) {
	  throw new UnsupportedOperationException(
		  "Метод createDomain для класса " + this.getClass().getName() + " не реализован.");
   }

   protected void doProcessRow(Row row, ImportResult<Domain> importResult) {
	  Domain domain = null;
	  try {
		 doProcessElement(row);
		 importResult.setSuccessCount(importResult.getSuccessCount() + 1);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, domain, e.getMessage(), e);
	  }
	  importResult.setProcessingCount(importResult.getProcessingCount() + 1);
   }

   protected Domain doProcessElement(Row row) {
	  Domain domain = createDomain(row);
	  return domain == null ? null : importDomain(domain);
   }

   protected void afterLastRow() {
   }

   protected int getFirstSkipRows() {
	  return 1;
   }

   protected int getPage() {
	  return 0;
   }

   protected Boolean checkPageName(String name) {
	  return true;
   }
}
