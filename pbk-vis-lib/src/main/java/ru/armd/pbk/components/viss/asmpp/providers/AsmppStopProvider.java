package ru.armd.pbk.components.viss.asmpp.providers;

import armd.lightHttp.client.BaseHttpClientParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.asmpp.loaders.AsmppStopLoader;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Провайдер для загрузчика остановок из АСМПП.
 */
@Component
public class AsmppStopProvider
	extends BaseExchangeProvider {

   @Autowired
   private AsmppStopLoader loader;

   AsmppStopProvider() {
	  super(Viss.ASMPP, VisAuditType.VIS_ASMPP);
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters) {
	  DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	  parameters.setServiceAddress(parameters.getServiceAddress().replace("{dateFrom}", df.format(visExchange.getWorkDate())));
	  parameters.setServiceAddress(parameters.getServiceAddress().replace("{dateTo}", df.format(visExchange.getWorkDate())));
	  return new DefaultRestClient(parameters);
   }
}
