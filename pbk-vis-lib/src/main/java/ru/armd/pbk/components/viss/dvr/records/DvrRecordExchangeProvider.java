package ru.armd.pbk.components.viss.dvr.records;

import armd.lightHttp.client.BaseHttpClientParameters;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.components.viss.dvr.DvrExchangeProvider;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.domain.viss.VisExchangeAttempt;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.domain.viss.VisExchangeResult;
import ru.armd.pbk.domain.viss.VisFile;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeStates;
import ru.armd.pbk.enums.viss.Viss;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Провайдер для загрузки записей с видеорегистраторов.
 */
@Component
@Scope("prototype")
public class DvrRecordExchangeProvider
	extends DvrExchangeProvider {

   public static final Logger LOGGER = Logger.getLogger(DvrRecordExchangeProvider.class);

   private DvrRecordLoader loader;

   /**
	* Конструктор.
	*
	* @param loader - загрузчик.
	*/
   @Autowired
   public DvrRecordExchangeProvider(DvrRecordLoader loader) {
	  super(Viss.DVR, VisAuditType.VIS_DVR_RECORD, loader);
	  this.loader = loader;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected String getFilter(Date workDate) {
	  String result = "";
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  try {
		 result = URLEncoder.encode("{\"date_from\":\"" + df.format(workDate)
			 + " 00:00\",\"date_to\":\"" + df.format(workDate)
			 + " 23:59\",\"criteria\":\"0\"}", "UTF-8");
	  } catch (UnsupportedEncodingException e) {
		 logAudit(AuditLevel.WARNING, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + "Ошибка в генерации запроса",
			 null);
	  }
	  return result;
   }

   protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters, Integer page) {
	  String url = parameters.getServiceAddress();
	  url = url.replace("{page}", page.toString());
	  url = url.replace("{sign}", signQuery(parameters));
	  url = url.replace("{filter}", getFilter(visExchange.getWorkDate()));
	  parameters.setServiceAddress(url);
	  return new DvrRecordRestClient(parameters);
   }

   @Override
   @Transactional
   protected void doImportHttpExchange(VisExchange visExchange, VisExchangeConfig visExchangeConfig,
									   VisExchangeAttempt visExchangeAttempt)
	   throws Exception {
	  updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.IN_PROCESS.getId());

	  try {
		 BaseHttpClientParameters parameters = new BaseHttpClientParameters();
		 parameters.setServiceAddress(visExchangeConfig.getUri());
		 parameters.setServiceUsername(visExchangeConfig.getLogin());
		 parameters.setServicePassword(visExchangeConfig.getPassword());
		 parameters.setLogExchangeToFiles(true);
		 parameters.setLogExchangeDir("./http-logs/");
		 int i = 0;
		 String uri = visExchangeConfig.getUri();
		 DefaultRestClient client = createRestClient(visExchange, parameters, i);
		 while (((DvrRecordRestClient) client).callRest() != null) {
			VisExchangeResult visExchangeResult =
				createExchangeResult(visExchangeAttempt.getId(), VisExchangeStates.IN_PROCESS.getId());
			try {

			   loader.importFile(new ByteArrayInputStream(client.getRawResponse()), uri,parameters);

			   VisFile visFile = createVisFile(visExchangeConfig, visExchange, "_response.raw",
				   "Файл импортирован по протоколу http", client.getRawResponse());

			   updateExchangeResult(visExchangeResult, VisExchangeStates.SUCCESS.getId(), visFile,
				   String.format("Ответ успешно обработан"));

			   updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.SUCCESS.getId(), null,
				   String.format("Импорт успешно завершен"));
			} catch (Exception e) {
			   updateExchangeResult(visExchangeResult, VisExchangeStates.FAIL.getId(), null,
				   String.format("Ошибка обработки ответа"));
			   throw e;
			}
			parameters.setServiceAddress(uri);
			i++;
			client = createRestClient(visExchange, parameters, i);
		 }
	  } catch (Exception e) {
		 updateExchangeAttempt(visExchangeAttempt, VisExchangeStates.FAIL.getId(), null,
			 "Ошибка импорта по протоколу http: " + e.getMessage());
		 throw e;
	  }
   }
}
