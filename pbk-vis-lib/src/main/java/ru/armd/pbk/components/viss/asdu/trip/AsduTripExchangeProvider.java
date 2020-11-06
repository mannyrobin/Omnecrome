package ru.armd.pbk.components.viss.asdu.trip;

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
public class AsduTripExchangeProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(AsduTripExchangeProvider.class);

   @Autowired
   private AsduTripLoader loader;

   /**
	* Конструктор по умолчанию.
	*/
   public AsduTripExchangeProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_ASDU_TRIP);
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
