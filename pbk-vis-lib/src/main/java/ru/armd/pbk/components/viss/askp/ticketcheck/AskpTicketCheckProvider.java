package ru.armd.pbk.components.viss.askp.ticketcheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

@Component
@Scope("prototype")
public class AskpTicketCheckProvider
	extends BaseExchangeProvider {

   @Autowired
   private AskpTicketCheckLoader loader;

   AskpTicketCheckProvider() {
	  super(Viss.ASKP, VisAuditType.VIS_ASKP_TICKET_CHECKS);
   }

   @Override
   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  loader.prepareDate(visExchange.getWorkDate());
	  ImportResult<?> result = loader.importFile(is, visExchange.getWorkDate());
	  loader.flush(visExchange.getWorkDate());
	  return result;
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile("v_" + df.format(visExchange.getWorkDate()) + "\\.txt");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   @Override
   protected boolean checkFileNameGz(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  return name.equals("v_" + df.format(visExchange.getWorkDate()) + ".txt.gz");
   }

   @Override
   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  return false;
   }
}
