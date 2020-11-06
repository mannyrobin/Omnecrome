package ru.armd.pbk.components.viss.gkuop.employee;

import armd.lightHttp.client.BaseHttpClientParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
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
 * Провайдер для ГКУ ОП сотрудников.
 */
@Component
public class GkuopExchangeEmployeeProvider
	extends BaseExchangeProvider {

   @Autowired
   private GkuopEmployeeLoader loader;

   /**
	* Конструктор по умлочнаию.
	*/
   public GkuopExchangeEmployeeProvider() {
	  super(Viss.GKUOP, VisAuditType.VIS_GKUOP_EMPLOYEE);
   }

   @Override
   protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters) {
	  String url = parameters.getServiceAddress();
	  url = url.replace("{user}", parameters.getServiceUsername());
	  url = url.replace("{pass}", parameters.getServicePassword());
	  parameters.setServiceAddress(url);
	  return new DefaultRestClient(parameters);
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  Pattern pattern = Pattern.compile(df.format(visExchange.getWorkDate()) + "\\.txt");
	  Matcher matcher = pattern.matcher(name);
	  return matcher.matches();
   }
}
