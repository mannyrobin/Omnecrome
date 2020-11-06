package ru.armd.pbk.components.viss.askp.puskcheck;

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
 * Провайдер для загрузки "проверки пусков".
 */
@Component
public class AskpPuskCheckProvider
	extends BaseExchangeProvider {

   @Autowired
   private AskpPuskCheckLoader loader;

   AskpPuskCheckProvider() {
	  super(Viss.ASKP, VisAuditType.VIS_ASKP_PUSK_CHECKS);
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile("c_" + df.format(visExchange.getWorkDate()) + "\\.txt");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }

   @Override
   protected boolean checkFileNameGz(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  return name.equals("c_" + df.format(visExchange.getWorkDate()) + ".txt.gz");
   }

   @Override
   protected boolean checkFolderName(VisExchange visExchange, String name) {
	  return false;
   }
}
