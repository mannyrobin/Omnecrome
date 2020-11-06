package ru.armd.pbk.components.viss.askp.contactlesscard;

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

/**
 * Провайдер проходов БСК.
 */
@Component
public class AskpContactlessCardProvider
	extends BaseExchangeProvider {

   @Autowired
   private AskpContactlessCardLoader loader;

   AskpContactlessCardProvider() {
	  super(Viss.ASKP, VisAuditType.VIS_ASKP_CONTACTLESS_CARDS);
   }

   @Override
   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  return loader.importFile(is, visExchange.getWorkDate());
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  try {
		 if (name.endsWith(".xlsx")) {
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			String sub = name.substring(29, name.length() - 5);
			String[] dates = sub.split(" ");
			if (dates.length == 2) {
			   return sub.endsWith(df.format(visExchange.getWorkDate()));
			} else if (dates.length == 4) {
			   return df.parse(dates[1]).compareTo(visExchange.getWorkDate()) * visExchange.getWorkDate().compareTo(df.parse(dates[3])) >= 0;
			}
		 }
		 return false;
	  } catch (Exception e) {
		 return false;
	  }

   }

   @Override
   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  return name.equals("BSK");
   }
}
