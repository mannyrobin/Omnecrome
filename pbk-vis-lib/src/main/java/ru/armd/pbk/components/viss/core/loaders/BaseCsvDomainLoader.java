package ru.armd.pbk.components.viss.core.loaders;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.utils.ImportResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Базовый загрузчик CSV файлов.
 *
 * @param <Domain> домен.
 */
public abstract class BaseCsvDomainLoader<Domain extends BaseDomain>
	extends BaseFileDomainLoader<Domain> {

   private Boolean skipFirstLine = true;
   private String charsetName = "UTF-8";

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий
	*/
   public BaseCsvDomainLoader(IDomainRepository<Domain> domainRepository) {
	  super(domainRepository);
   }


   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий
	* @param skipFirstLine    признак пропуска первой строки
	*/
   public BaseCsvDomainLoader(IDomainRepository<Domain> domainRepository, Boolean skipFirstLine) {
	  super(domainRepository);
	  this.skipFirstLine = skipFirstLine;
   }

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий
	* @param skipFirstLine    признак пропуска первой строки
	* @param charsetName      кодировка
	*/
   public BaseCsvDomainLoader(IDomainRepository<Domain> domainRepository, Boolean skipFirstLine, String charsetName) {
	  this(domainRepository, skipFirstLine);
	  this.charsetName = charsetName;
   }

   public AuditType getAuditType() {
	  return VisAuditType.VIS_VIS;
   }

   @Override
   public ImportResult<Domain> importFile(InputStream is) {
	  ImportResult<Domain> importResult = new ImportResult<Domain>();
	  try {
		 BufferedReader brFile = new BufferedReader(new InputStreamReader(is, charsetName));
		 String line = null;
		 if (skipFirstLine) {
			brFile.readLine(); // skip first line
		 }
		 int numRow = 1;
		 while ((line = brFile.readLine()) != null) {
		 	numRow++;
			doProcessLine(line, importResult);
			importResult.setAllCount(importResult.getAllCount() + 1);
		 }
		 afterLastRow();
	  } catch (Exception e) {
		 throw new PBKException(e.getMessage(), e);
	  }
	  return importResult;
   }

   @Override
   protected void doProcessLine(String line, ImportResult<Domain> importResult) {
	  Domain newDomain = null;
	  try {
		 doProcessFields(line.split(FIELD_SEPARATOR));
		 importResult.setSuccessCount(importResult.getSuccessCount() + 1);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, newDomain, e.getMessage(), e);
	  }
	  importResult.setProcessingCount(importResult.getProcessingCount() + 1);
   }

   protected void doProcessFields(String[] fields) {
	  importDomain(createDomain(fields));
   }

   protected void afterLastRow() {
   }
}
