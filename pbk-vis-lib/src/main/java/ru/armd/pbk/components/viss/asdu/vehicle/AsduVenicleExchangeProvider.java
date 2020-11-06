package ru.armd.pbk.components.viss.asdu.vehicle;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Провайдер взаимодействия с ВИС АСДУ-НГПТ для импорта списка ТС.
 */
@Component
public class AsduVenicleExchangeProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(AsduVenicleExchangeProvider.class);

   @Autowired
   private GtfsVehicleLoader gtfsVenicleLoader;

   /**
	* Конструктор по умолчанию.
	*/
   public AsduVenicleExchangeProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_ASDU_VENICLE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile("GTFS_VEHICLES_" + df.format(visExchange.getWorkDate()) + "\\.csv");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return gtfsVenicleLoader.importFile(is);
   }

   /*
	* Методы для импорта по http
	*/
   @Override
   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  return true;
   }
}
