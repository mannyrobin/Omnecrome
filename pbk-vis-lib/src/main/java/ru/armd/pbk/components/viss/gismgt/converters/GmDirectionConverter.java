package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmDirection;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RMovementDirection;
import ru.armd.pbk.gismgt.schema.TMovementDirection;
import ru.armd.pbk.mappers.viss.gismgt.GmDirectionMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TMovementDirection в MovementDirection.
 */
@Component
public class GmDirectionConverter
	extends AbstractGmConverter<RMovementDirection, GmDirection> {

   public static final Logger LOGGER = Logger.getLogger(GmDirectionConverter.class);

   @Autowired
   private GmDirectionMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmDirectionConverter() {
	  super(VisAuditType.VIS_GISMGT_DIRECTION);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmDirection> getMapper() {
	  return mapper;
   }

   @Override
   protected GmDirection convert(RMovementDirection gis) {
	  TMovementDirection body = gis.getBody();
	  GmDirection domain = new GmDirection();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  domain.setShortName(getStringValue(body.getShortName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmDirection to, GmDirection from) {
	  Boolean result = super.merge(to, from);
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
   protected GmDirection fillMain(Long muid) {
	  GmDirection domain = new GmDirection();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RMovementDirection toGis(ObjectMessage objectMessage) {
	  RMovementDirection gis = new RMovementDirection();
	  RMovementDirection.Header header = new RMovementDirection.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTMovementDirection());
	  gis.setHeader(header);
	  return gis;
   }

}
