package ru.armd.pbk.components.viss.asdu.driver;

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
 * Провайдер взаимодействия с ВИС АСДУ-НГПТ для импорта списка водителей.
 */
@Component
public class AsduDriverExchangeProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(AsduDriverExchangeProvider.class);

   @Autowired
   private GtfsDriverLoader gtfsDriverLoader;

   /**
	* Конструктор по умолчанию.
	*/
   public AsduDriverExchangeProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_ASDU_DRIVER);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return gtfsDriverLoader.importFile(is);
   }

   /*
	* Методы для импорта с файловой системы
	*/
   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile("GTFS_DRIVERS_" + df.format(visExchange.getWorkDate()) + "\\.csv");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   /*
	* Методы для импорта по http
	*/
   @Override
   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  return true;
   }
}
