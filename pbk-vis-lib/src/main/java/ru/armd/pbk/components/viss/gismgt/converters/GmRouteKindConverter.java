package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteKind;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteKind;
import ru.armd.pbk.gismgt.schema.TRouteKind;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteKind в RouteKind.
 */
@Component
public class GmRouteKindConverter
	extends AbstractGmConverter<RRouteKind, GmRouteKind> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteKindConverter.class);

   @Autowired
   private GmRouteKindMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteKindConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_KIND);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteKind> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteKind convert(RRouteKind gis) {
	  TRouteKind body = gis.getBody();
	  GmRouteKind domain = new GmRouteKind();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteKind to, GmRouteKind from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected GmRouteKind fillMain(Long muid) {
	  GmRouteKind domain = new GmRouteKind();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteKind toGis(ObjectMessage objectMessage) {
	  RRouteKind gis = new RRouteKind();
	  RRouteKind.Header header = new RRouteKind.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteKind());
	  gis.setHeader(header);
	  return gis;
   }
}
