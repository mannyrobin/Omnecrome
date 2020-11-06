package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTrajectoryType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteTrajectoryType;
import ru.armd.pbk.gismgt.schema.TRouteTrajectoryType;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTrajectoryTypeMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteTrajectoryType в RouteTrajectoryType.
 */
@Component
public class GmRouteTrajectoryTypeConverter
	extends AbstractGmConverter<RRouteTrajectoryType, GmRouteTrajectoryType> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteTrajectoryTypeConverter.class);

   @Autowired
   private GmRouteTrajectoryTypeMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteTrajectoryTypeConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_TRAJ_TYPE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteTrajectoryType> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteTrajectoryType convert(RRouteTrajectoryType gis) {
	  TRouteTrajectoryType body = gis.getBody();
	  GmRouteTrajectoryType domain = new GmRouteTrajectoryType();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteTrajectoryType to, GmRouteTrajectoryType from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected GmRouteTrajectoryType fillMain(Long muid) {
	  GmRouteTrajectoryType domain = new GmRouteTrajectoryType();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteTrajectoryType toGis(ObjectMessage objectMessage) {
	  RRouteTrajectoryType gis = new RRouteTrajectoryType();
	  RRouteTrajectoryType.Header header = new RRouteTrajectoryType.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteTrajectoryType());
	  gis.setHeader(header);
	  return gis;
   }
}
