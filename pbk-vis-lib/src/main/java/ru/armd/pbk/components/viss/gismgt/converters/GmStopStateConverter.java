package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmStopState;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RStopState;
import ru.armd.pbk.gismgt.schema.TStopState;
import ru.armd.pbk.mappers.viss.gismgt.GmStopStateMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TStopState в StopState.
 */
@Component
public class GmStopStateConverter
	extends AbstractGmConverter<RStopState, GmStopState> {

   public static final Logger LOGGER = Logger.getLogger(GmStopStateConverter.class);

   @Autowired
   private GmStopStateMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmStopStateConverter() {
	  super(VisAuditType.VIS_GISMGT_STOP_STATE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmStopState> getMapper() {
	  return mapper;
   }

   @Override
   protected GmStopState convert(RStopState gis) {
	  TStopState body = gis.getBody();
	  GmStopState domain = new GmStopState();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmStopState to, GmStopState from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected GmStopState fillMain(Long muid) {
	  GmStopState domain = new GmStopState();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RStopState toGis(ObjectMessage objectMessage) {
	  RStopState gis = new RStopState();
	  RStopState.Header header = new RStopState.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTStopState());
	  gis.setHeader(header);
	  return gis;
   }
}
