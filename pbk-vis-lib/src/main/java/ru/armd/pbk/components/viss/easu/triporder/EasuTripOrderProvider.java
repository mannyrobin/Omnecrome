package ru.armd.pbk.components.viss.easu.triporder;

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
 * Провайдер нарядов ЕАСУ.
 */
@Component
public class EasuTripOrderProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(EasuTripOrderProvider.class);

   @Autowired
   private EasuTripOrderLoader loader;

   /**
	* Конструктор по умолчанию.
	*/
   public EasuTripOrderProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_EASUFHD_TRIP_ORDER);
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
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  return true;
   }

   @Override
   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile(df.format(visExchange.getWorkDate()));
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

}
