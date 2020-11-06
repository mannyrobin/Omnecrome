package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTransportationKind;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteTransportationKind;
import ru.armd.pbk.gismgt.schema.TRouteTransportationKind;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTransportationKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteTransportationKind в RouteTransportationKind.
 */
@Component
public class GmRouteTransportationKindConverter
	extends AbstractGmConverter<RRouteTransportationKind, GmRouteTransportationKind> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteTransportationKindConverter.class);

   @Autowired
   private GmRouteTransportationKindMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteTransportationKindConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_TRNS_KIND);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteTransportationKind> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteTransportationKind convert(RRouteTransportationKind gis) {
	  TRouteTransportationKind body = gis.getBody();
	  GmRouteTransportationKind domain = new GmRouteTransportationKind();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteTransportationKind to, GmRouteTransportationKind from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected GmRouteTransportationKind fillMain(Long muid) {
	  GmRouteTransportationKind domain = new GmRouteTransportationKind();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteTransportationKind toGis(ObjectMessage objectMessage) {
	  RRouteTransportationKind gis = new RRouteTransportationKind();
	  RRouteTransportationKind.Header header = new RRouteTransportationKind.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteTransportationKind());
	  gis.setHeader(header);
	  return gis;
   }
}
