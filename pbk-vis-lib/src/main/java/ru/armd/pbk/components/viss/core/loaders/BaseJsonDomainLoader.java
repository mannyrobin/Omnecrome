package ru.armd.pbk.components.viss.core.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.utils.ImportResult;

import java.io.IOException;
import java.io.InputStream;

/**
 * Загрузчик для JSON.
 *
 * @param <Domain> - домен.
 */
public abstract class BaseJsonDomainLoader<Domain extends BaseDomain>
	extends BaseDomainLoader<Domain> {

   private static final Logger LOGGER = Logger.getLogger(BaseJsonDomainLoader.class);

   /**
	* Конструктор.
	*
	* @param domainRepository - репозиторий.
	*/
   public BaseJsonDomainLoader(IDomainRepository<Domain> domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public AuditType getAuditType() {
	  return VisAuditType.VIS_VIS;
   }

   protected abstract JsonArray getDomainsArray(JsonObject object);

   protected abstract Domain createDomain(JsonObject element);

   /**
	* Загрузить данные из входного потока.
	*
	* @param is - входной поток.
	* @return
	*/
   public ImportResult<Domain> importFile(InputStream is) {
	  ImportResult<Domain> importResult = new ImportResult<Domain>();
	  try {
		 JsonParser parser = new JsonParser();
		 JsonElement jsonElement = parser.parse(IOUtils.toString(is, "UTF-8"));
		 doProcessJsonObject(jsonElement.getAsJsonObject(), importResult);
	  } catch (JsonSyntaxException | IOException e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, null, e.getMessage(), e);
	  }
	  return importResult;
   }


   protected void doProcessJsonObject(JsonObject object, ImportResult<Domain> importResult) {
	  JsonArray array = getDomainsArray(object);
	  for (JsonElement element : array) {
		 doProcessJsonElement(element, importResult);
		 importResult.setAllCount(importResult.getAllCount() + 1);
	  }
   }

   protected void doProcessJsonElement(JsonElement element, ImportResult<Domain> importResult) {
	  Domain newDomain = null;
	  try {
		 importDomain(createDomain(element.getAsJsonObject()));
		 importResult.setSuccessCount(importResult.getSuccessCount() + 1);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, getAuditType(), AuditObjOperation.OTHER, newDomain, e.getMessage(), e);
	  }
	  importResult.setProcessingCount(importResult.getProcessingCount() + 1);
   }

   @Override
   protected Domain createDomain(String[] fields) {
	  throw new UnsupportedOperationException(
		  "Метод createDomain для класса " + this.getClass().getName() + " не реализован.");
   }

   protected String getStringValue(JsonElement element) {
	  return element.isJsonNull() ? null : element.getAsString();
   }
}
