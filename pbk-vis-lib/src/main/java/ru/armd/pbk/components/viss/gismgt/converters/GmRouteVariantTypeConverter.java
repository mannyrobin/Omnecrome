package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteVariantType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteVariantType;
import ru.armd.pbk.gismgt.schema.TRouteVariantType;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteVariantTypeMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteVariantType в RouteVariantType.
 */
@Component
public class GmRouteVariantTypeConverter
	extends AbstractGmConverter<RRouteVariantType, GmRouteVariantType> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteVariantTypeConverter.class);

   @Autowired
   private GmRouteVariantTypeMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteVariantTypeConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_VAR_TYPE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteVariantType> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteVariantType convert(RRouteVariantType gis) {
	  TRouteVariantType body = gis.getBody();
	  GmRouteVariantType domain = new GmRouteVariantType();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteVariantType to, GmRouteVariantType from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected GmRouteVariantType fillMain(Long muid) {
	  GmRouteVariantType domain = new GmRouteVariantType();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteVariantType toGis(ObjectMessage objectMessage) {
	  RRouteVariantType gis = new RRouteVariantType();
	  RRouteVariantType.Header header = new RRouteVariantType.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteVariantType());
	  gis.setHeader(header);
	  return gis;
   }
}
