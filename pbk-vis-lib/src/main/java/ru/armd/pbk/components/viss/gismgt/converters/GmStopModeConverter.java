package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmStopMode;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RStopMode;
import ru.armd.pbk.gismgt.schema.TStopMode;
import ru.armd.pbk.mappers.viss.gismgt.GmStopModeMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TStopMode в StopMode.
 */
@Component
public class GmStopModeConverter
	extends AbstractGmConverter<RStopMode, GmStopMode> {

   public static final Logger LOGGER = Logger.getLogger(GmStopModeConverter.class);

   @Autowired
   private GmStopModeMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmStopModeConverter() {
	  super(VisAuditType.VIS_GISMGT_STOP_MODE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmStopMode> getMapper() {
	  return mapper;
   }

   @Override
   protected GmStopMode convert(RStopMode gis) {
	  TStopMode body = gis.getBody();
	  GmStopMode domain = new GmStopMode();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setShortName(getStringValue(body.getShortName()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmStopMode to, GmStopMode from) {
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
   protected GmStopMode fillMain(Long muid) {
	  GmStopMode domain = new GmStopMode();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RStopMode toGis(ObjectMessage objectMessage) {
	  RStopMode gis = new RStopMode();
	  RStopMode.Header header = new RStopMode.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTStopMode());
	  gis.setHeader(header);
	  return gis;
   }
}
