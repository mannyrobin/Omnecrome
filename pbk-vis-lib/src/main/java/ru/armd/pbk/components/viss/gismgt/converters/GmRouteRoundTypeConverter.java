package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteRoundType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteRoundType;
import ru.armd.pbk.gismgt.schema.TRouteRoundType;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteRoundTypeMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteRoundType в RouteRoundType.
 */
@Component
public class GmRouteRoundTypeConverter
	extends AbstractGmConverter<RRouteRoundType, GmRouteRoundType> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteRoundTypeConverter.class);

   @Autowired
   private GmRouteRoundTypeMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteRoundTypeConverter() {
	  super(VisAuditType.VIS_GISMGT_RR_TYPE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteRoundType> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteRoundType convert(RRouteRoundType gis) {
	  TRouteRoundType body = gis.getBody();
	  GmRouteRoundType domain = new GmRouteRoundType();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setCod(getStringValue(body.getCode()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteRoundType to, GmRouteRoundType from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getCod() != null && !from.getCod().equals(to.getCod())) {
		 result = true;
		 to.setCod(from.getCod());
	  }
	  if (from.getShortName() != null && !from.getShortName().equals(to.getShortName())) {
		 result = true;
		 to.setShortName(from.getShortName());
	  }
	  return result;
   }

   @Override
   protected GmRouteRoundType fillMain(Long muid) {
	  GmRouteRoundType domain = new GmRouteRoundType();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteRoundType toGis(ObjectMessage objectMessage) {
	  RRouteRoundType gis = new RRouteRoundType();
	  RRouteRoundType.Header header = new RRouteRoundType.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteRoundType());
	  gis.setHeader(header);
	  return gis;
   }
}
