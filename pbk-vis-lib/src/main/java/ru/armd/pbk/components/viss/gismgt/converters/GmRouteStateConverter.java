package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteState;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteState;
import ru.armd.pbk.gismgt.schema.TRouteState;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteStateMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteState в RouteState.
 */
@Component
public class GmRouteStateConverter
	extends AbstractGmConverter<RRouteState, GmRouteState> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteStateConverter.class);

   @Autowired
   private GmRouteStateMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteStateConverter() {
	  super(VisAuditType.VIS_GISMGT_RR);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteState> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteState convert(RRouteState gis) {
	  TRouteState body = gis.getBody();
	  GmRouteState domain = new GmRouteState();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteState to, GmRouteState from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected GmRouteState fillMain(Long muid) {
	  GmRouteState domain = new GmRouteState();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteState toGis(ObjectMessage objectMessage) {
	  RRouteState gis = new RRouteState();
	  RRouteState.Header header = new RRouteState.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteState());
	  gis.setHeader(header);
	  return gis;
   }
}
