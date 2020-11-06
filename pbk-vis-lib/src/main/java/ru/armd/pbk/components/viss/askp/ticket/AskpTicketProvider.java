package ru.armd.pbk.components.viss.askp.ticket;

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
 * Провайдер для загрузчика билетов из АСКП.
 */
@Component
public class AskpTicketProvider
	extends BaseExchangeProvider {

   @Autowired
   private AskpTicketLoader loader;

   AskpTicketProvider() {
	  super(Viss.ASKP, VisAuditType.VIS_ASKP_TICKET);
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile("t_" + df.format(visExchange.getWorkDate()) + "\\.txt");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   @Override
   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  return false;
   }
}
