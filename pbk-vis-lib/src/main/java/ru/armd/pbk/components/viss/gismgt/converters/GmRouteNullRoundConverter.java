package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteNullRound;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteNullRound;
import ru.armd.pbk.gismgt.schema.TRouteNullRound;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteNullRoundMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteNullRound в RouteNullRound.
 */
@Component
public class GmRouteNullRoundConverter
	extends AbstractGmConverter<RRouteNullRound, GmRouteNullRound> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteNullRoundConverter.class);

   @Autowired
   private GmRouteNullRoundMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteNullRoundConverter() {
	  super(VisAuditType.VIS_GISMGT_RNR);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteNullRound> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteNullRound convert(RRouteNullRound gis) {
	  TRouteNullRound body = gis.getBody();
	  GmRouteNullRound domain = new GmRouteNullRound();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setComment(getStringValue(body.getComment()));
	  domain.setCod(getStringValue(body.getCode()));
	  domain.setRouteVariantMuid(getId(body.getRouteVariant().getValue().getHeader().getIdentSet()));
	   try {
		   domain.setTypeMuid(getId(body.getType().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   try {
		   domain.setPark1Muid(getId(body.getPark1().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   try {
		   domain.setPark2Muid(getId(body.getPark2().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   try {
		   domain.setPark3Muid(getId(body.getPark3().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   try {
		   domain.setStopPlace1Muid(getId(body.getStopPlace1().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   try {
		   domain.setStopPlace2Muid(getId(body.getStopPlace2().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   try {
		   domain.setStopPlace3Muid(getId(body.getStopPlace3().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   return domain;
   }

   @Override
   protected Boolean merge(GmRouteNullRound to, GmRouteNullRound from) {
	  Boolean result = super.merge(to, from);
	  if (from.getCod() != null && !from.getCod().equals(to.getCod())) {
		 result = true;
		 to.setCod(from.getCod());
	  }
	  if (from.getRouteVariantMuid() != null && !from.getRouteVariantMuid().equals(to.getRouteVariantMuid())) {
		 result = true;
		 to.setRouteVariantMuid(from.getRouteVariantMuid());
	  }
	  if (from.getTypeMuid() != null && !from.getTypeMuid().equals(to.getTypeMuid())) {
		 result = true;
		 to.setTypeMuid(from.getTypeMuid());
	  }
	  if (from.getPark1Muid() != null && !from.getPark1Muid().equals(to.getPark1Muid())) {
		 result = true;
		 to.setPark1Muid(from.getPark1Muid());
	  }
	  if (from.getStopPlace1Muid() != null && !from.getStopPlace1Muid().equals(to.getStopPlace1Muid())) {
		 result = true;
		 to.setStopPlace1Muid(from.getStopPlace1Muid());
	  }
	  if (from.getPark2Muid() != null && !from.getPark2Muid().equals(to.getPark2Muid())) {
		 result = true;
		 to.setPark2Muid(from.getPark2Muid());
	  }
	  if (from.getStopPlace2Muid() != null && !from.getStopPlace2Muid().equals(to.getStopPlace2Muid())) {
		 result = true;
		 to.setStopPlace2Muid(from.getStopPlace2Muid());
	  }
	  if (from.getPark3Muid() != null && !from.getPark3Muid().equals(to.getPark3Muid())) {
		 result = true;
		 to.setPark3Muid(from.getPark3Muid());
	  }
	  if (from.getStopPlace3Muid() != null && !from.getStopPlace3Muid().equals(to.getStopPlace3Muid())) {
		 result = true;
		 to.setStopPlace3Muid(from.getStopPlace3Muid());
	  }
	  if (from.getComment() != null && !from.getComment().equals(to.getComment())) {
		 result = true;
		 to.setComment(from.getComment());
	  }
	  return result;
   }

   @Override
   protected GmRouteNullRound fillMain(Long muid) {
	  GmRouteNullRound domain = new GmRouteNullRound();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteNullRound toGis(ObjectMessage objectMessage) {
	  RRouteNullRound gis = new RRouteNullRound();
	  RRouteNullRound.Header header = new RRouteNullRound.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteNullRound());
	  gis.setHeader(header);
	  return gis;
   }
}
