package ru.armd.pbk.components.viss.gtfs.providers;

import armd.lightRest.client.BaseRestClient;
import org.springframework.beans.factory.ObjectFactory;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsLoader;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;

/**
 * Базовый провайдер для ГТФС.
 * Т.к ГТФС работает в разных потоках, то в конструктор
 * в качестве параметра передается фабрика загрузчиков,
 * чтобы избежать конфликтов при загрузке данных.
 *
 * @param <Domain> домен
 */
public abstract class GtfsBaseProvider<Domain extends BaseDomain>
	extends BaseExchangeProvider {

   private ObjectFactory<? extends GtfsLoader<Domain>> factory;

   /**
	* Конструктор.
	*
	* @param factory   фабрика загрузчиков.
	* @param auditType тип аудита.
	*/
   public GtfsBaseProvider(ObjectFactory<? extends GtfsLoader<Domain>> factory, AuditType auditType) {
	  super(Viss.GTFS, auditType);
	  this.factory = factory;
   }

   @Override
   protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
	  return factory.getObject().importFile(is, visExchange.getWorkDate());
   }

   @Override
   protected boolean checkFileName(VisExchange visExchange, String name) {
	  return true;
   }

   @Override
   protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
	  String address = client.getClientParameters().getServiceAddress();
	  String[] type = address.split("type=");
	  if (type.length > 1) {
		 return "ZIP".equals(type[1]);
	  }
	  return false;
   }
}
