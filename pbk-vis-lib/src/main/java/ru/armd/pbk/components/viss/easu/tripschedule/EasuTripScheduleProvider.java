package ru.armd.pbk.components.viss.easu.tripschedule;

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
 * Провайдер расписания ЕАСУ.
 */
@Component
public class EasuTripScheduleProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(EasuTripScheduleProvider.class);

   @Autowired
   private EasuTripScheduleLoader loader;

   /**
	* Конструктор по умолчанию.
	*/
   public EasuTripScheduleProvider() {
	  super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_TRIP_SCHEDULE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  return loader.importFile(is, visExchange.getWorkDate());
   }

   @Override
   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  Pattern pattern = Pattern.compile("(avto|tram|trol)[0-9]+");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile(".+_" + df.format(visExchange.getWorkDate()) + "_.+_.+_.+\\.txt");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }
}
