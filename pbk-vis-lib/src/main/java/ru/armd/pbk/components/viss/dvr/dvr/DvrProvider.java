package ru.armd.pbk.components.viss.dvr.dvr;

import armd.lightHttp.client.BaseHttpClientParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.components.viss.dvr.DvrExchangeProvider;
import ru.armd.pbk.components.viss.dvr.records.DvrRecordRestClient;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;

/**
 * Провайдер для загрузчика видеорегистраторов.
 */
@Component
@Scope("prototype")
public class DvrProvider
	extends DvrExchangeProvider {

   @Autowired
   DvrProvider(DvrLoader loader) {
	  super(Viss.DVR, VisAuditType.VIS_DVR_DVR, loader);
   }

   @Override
   protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters) {
	  String url = parameters.getServiceAddress();
	  url = url.replace("{sign}", signQuery(parameters));
	  parameters.setServiceAddress(url);
	  return new DvrRecordRestClient(parameters);
   }
}
