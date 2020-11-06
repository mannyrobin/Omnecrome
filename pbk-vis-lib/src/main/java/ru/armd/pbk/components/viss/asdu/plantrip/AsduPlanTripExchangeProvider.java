package ru.armd.pbk.components.viss.asdu.plantrip;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightRest.client.BaseRestClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.mappers.nsi.asdu.AsduRouteMoveTripMapper;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Провайдер взаимодействия с ВИС АСДУ-НГПТ для импорта списка водителей.
 */
@Component
public class AsduPlanTripExchangeProvider
	extends BaseExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(AsduPlanTripExchangeProvider.class);

   @Autowired
   private AsduPlanTripLoader loader;

   @Autowired
   private AsduRouteMoveTripMapper tripMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public AsduPlanTripExchangeProvider() {
	  super(Viss.ASDU, VisAuditType.VIS_ASDU_PLANTRIP);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  ImportResult<?> result = importStream(is);
	  insertAggRouteMove(visExchange.getWorkDate());
	  return result;
   }

   @Override
   protected ImportResult<?> importStream(InputStream is) {
	  return loader.importFile(is);
   }

   @Override
   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  return true;
   }

   @Override
   protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  parameters.setServiceAddress(parameters.getServiceAddress().replace("{date}", df.format(visExchange.getWorkDate())));
	  return new DefaultRestClient(parameters);
   }

   private void insertAggRouteMove(Date date) {
	  try {
		 tripMapper.deleteByWorkDate(date);
		 tripMapper.insertByWorkDate(date);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Ошибка агрегации маршрутов " + date, e);
	  }
   }
}
