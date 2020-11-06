package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteNullRoundType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteNullRoundType;
import ru.armd.pbk.gismgt.schema.TRouteNullRoundType;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteNullRoundTypeMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteNullRoundType в RouteNullRoundType.
 */
@Component
public class GmRouteNullRoundTypeConverter
	extends AbstractGmConverter<RRouteNullRoundType, GmRouteNullRoundType> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteNullRoundTypeConverter.class);

   @Autowired
   private GmRouteNullRoundTypeMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteNullRoundTypeConverter() {
	  super(VisAuditType.VIS_GISMGT_RNRT);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteNullRoundType> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteNullRoundType convert(RRouteNullRoundType gis) {
	  TRouteNullRoundType body = gis.getBody();
	  GmRouteNullRoundType domain = new GmRouteNullRoundType();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setCod(getStringValue(body.getCode()));
	  domain.setShortName(getStringValue(body.getShortName()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteNullRoundType to, GmRouteNullRoundType from) {
	  Boolean result = super.merge(to, from);
	  if (from.getCod() != null && !from.getCod().equals(to.getCod())) {
		 result = true;
		 to.setCod(from.getCod());
	  }
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getShortName() != null && !from.getShortName().equals(to.getShortName())) {
		 result = true;
		 to.setShortName(from.getShortName());
	  }
	  return result;
   }

   @Override
   protected GmRouteNullRoundType fillMain(Long muid) {
	  GmRouteNullRoundType domain = new GmRouteNullRoundType();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteNullRoundType toGis(ObjectMessage objectMessage) {
	  RRouteNullRoundType gis = new RRouteNullRoundType();
	  RRouteNullRoundType.Header header = new RRouteNullRoundType.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteNullRoundType());
	  gis.setHeader(header);
	  return gis;
   }
}
