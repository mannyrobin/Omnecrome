package ru.armd.pbk.components.viss.asdu.equipment;

import armd.lightRest.client.BaseRestClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

/**
 * Провайдер взаимодействия с ВИС АСДУ-НГПТ для импорта списка водителей.
 */
@Component
public class AsduEquipmentExchangeProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(AsduEquipmentExchangeProvider.class);

   @Autowired
   private AsduEquipmentLoader loader;

   /**
	* Конструктор по умолчанию.
	*/
   public AsduEquipmentExchangeProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_ASDU_EQUIPMENT);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  return true;
   }
}
