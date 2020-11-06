package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.VisExchangeConfigMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий конфигураций обмена.
 */
@Repository
public class VisExchangeConfigRepository
	extends BaseDomainRepository<VisExchangeConfig> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeConfigRepository.class);

   /**
	* Конструктор.
	*
	* @param visExchangeConfigMapper Маппер репозитория.
	*/
   @Autowired
   public VisExchangeConfigRepository(VisExchangeConfigMapper visExchangeConfigMapper) {
	  super(VisAuditType.VIS_EXCHANGE_CONFIG, visExchangeConfigMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить активную конфигурацию для импорта из ВИС.
	*
	* @param visExchangeObject - Тип запросов к ВИС
	* @return
	*/
   public VisExchangeConfig getActiveImportConfig(VisExchangeObjects visExchangeObject) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("isDelete", 0);
	  params.put("isActive", 1);
	  params.put("exchangeObjectId", visExchangeObject.getId());
	  params.put("exchangeOperationId", VisExchangeOperations.IMPORT.getId());
	  List<VisExchangeConfig> configs = getDomains(params);
	  if (configs.size() == 0) {
		 throw new PBKException("Не найдено активных конфигураций для объекта обмена '" + visExchangeObject.getName() + "' для импорта");
	  }
	  if (configs.size() > 1) {
		 throw new PBKException("Найдено более одной активной конфигурации для объекта обмена '" + visExchangeObject.getName() + "' для импорта");
	  }
	  return configs.get(0);
   }

	/**
	 * Получить список активных конфигураций для импорта из ВИС.
	 *
	 * @param visExchangeObject - Тип запросов к ВИС
	 * @return
	 */
	public List<VisExchangeConfig> getActiveImportConfigs(VisExchangeObjects visExchangeObject) {
		Map<String, Object> params = new HashMap<>();
		params.put("isDelete", 0);
		params.put("isActive", 1);
		params.put("exchangeObjectId", visExchangeObject.getId());
		params.put("exchangeOperationId", VisExchangeOperations.IMPORT.getId());
		List<VisExchangeConfig> configs = getDomains(params);
		if (configs.size() == 0) {
			throw new PBKException("Не найдено активных конфигураций для объекта обмена '" + visExchangeObject.getName() + "' для импорта");
		}
		return configs;
	}

   /**
	* Получить активную конфигурацию для экспорта.
	*
	* @param visExchangeObject - Тип запросов к ВИС
	* @return
	*/
   public List<VisExchangeConfig> getActiveExportConfigs(VisExchangeObjects visExchangeObject) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("isDelete", 0);
	  params.put("isActive", 1);
	  params.put("exchangeObjectId", visExchangeObject.getId());
	  params.put("exchangeOperationId", VisExchangeOperations.EXPORT.getId());
	  List<VisExchangeConfig> configs = getDomains(params);
	  if (configs.size() == 0) {
		 throw new PBKException("Не найдено активных конфигураций для объекта обмена '" + visExchangeObject.getName() + "' для экспорта");
	  }
	  return configs;
   }
}
