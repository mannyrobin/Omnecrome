package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteRound;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteRound;
import ru.armd.pbk.gismgt.schema.TRouteRound;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteRoundMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteRound в RouteRound.
 */
@Component
public class GmRouteRoundConverter
	extends AbstractGmConverter<RRouteRound, GmRouteRound> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteRoundConverter.class);

   @Autowired
   private GmRouteRoundMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteRoundConverter() {
	  super(VisAuditType.VIS_GISMGT_RR);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteRound> getMapper() {
	  return mapper;
   }

	@Override
	protected GmRouteRound convert(RRouteRound gis) {
		TRouteRound body = gis.getBody();
		GmRouteRound domain = new GmRouteRound();
		domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
		domain.setMuid(getLongValueFromString(body.getMuid()));
		domain.setVersion(body.getVersion().intValue());
		domain.setIsDelete(getIntValue(body.getSignDeleted()));
		domain.setRouteVariantMuid(getId(body.getRouteVariant().getValue().getHeader().getIdentSet()));
		domain.setCod(getStringValue(body.getCode()));
		domain.setTypeMuid(getId(body.getType().getValue().getHeader().getIdentSet()));
		try {
			domain.setStopPlaceAMuid(getId(body.getStopPlaceA().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		try {
			domain.setStopPlaceBMuid(getId(body.getStopPlaceB().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		try {
			domain.setTerminalStationAMuid(getId(body.getTerminalStationA().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		try {
			domain.setTerminalPointZoneAMuid(getId(body.getTerminalPointZoneA().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		try {
			domain.setTerminalPointZoneBMuid(getId(body.getTerminalPointZoneB().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		domain.setAverageLengthFixed(getFloatValue(body.getAverageLengthFixed()));
		domain.setComment(getStringValue(body.getComment()));
		domain.setSpecification1(getStringValue(body.getSpecification1()));
		domain.setSpecification2(getStringValue(body.getSpecification2()));
		return domain;
	}

   @Override
   protected Boolean merge(GmRouteRound to, GmRouteRound from) {
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
	  if (from.getTerminalPointZoneAMuid() != null && !from.getTerminalPointZoneAMuid().equals(to.getTerminalPointZoneAMuid())) {
		 result = true;
		 to.setTerminalPointZoneAMuid(from.getTerminalPointZoneAMuid());
	  }
	  if (from.getTerminalPointZoneBMuid() != null && !from.getTerminalPointZoneBMuid().equals(to.getTerminalPointZoneBMuid())) {
		 result = true;
		 to.setTerminalPointZoneBMuid(from.getTerminalPointZoneBMuid());
	  }
	  if (from.getTerminalStationAMuid() != null && !from.getTerminalStationAMuid().equals(to.getTerminalStationAMuid())) {
		 result = true;
		 to.setTerminalStationAMuid(from.getTerminalStationAMuid());
	  }
	  if (from.getTerminalStationBMuid() != null && !from.getTerminalStationBMuid().equals(to.getTerminalStationBMuid())) {
		 result = true;
		 to.setTerminalStationBMuid(from.getTerminalStationBMuid());
	  }
	  if (from.getTerminalStationCMuid() != null && !from.getTerminalStationCMuid().equals(to.getTerminalStationCMuid())) {
		 result = true;
		 to.setTerminalStationCMuid(from.getTerminalStationCMuid());
	  }
	  if (from.getStopPlaceAMuid() != null && !from.getStopPlaceAMuid().equals(to.getStopPlaceAMuid())) {
		 result = true;
		 to.setStopPlaceAMuid(from.getStopPlaceAMuid());
	  }
	  if (from.getStopPlaceBMuid() != null && !from.getStopPlaceBMuid().equals(to.getStopPlaceBMuid())) {
		 result = true;
		 to.setStopPlaceBMuid(from.getStopPlaceBMuid());
	  }
	  if (from.getAverageLength() != null && !from.getAverageLength().equals(to.getAverageLength())) {
		 result = true;
		 to.setAverageLength(from.getAverageLength());
	  }
	  if (from.getAverageLengthFixed() != null && !from.getAverageLengthFixed().equals(to.getAverageLengthFixed())) {
		 result = true;
		 to.setAverageLengthFixed(from.getAverageLengthFixed());
	  }
	  if (from.getSpecification1() != null && !from.getSpecification1().equals(to.getSpecification1())) {
		 result = true;
		 to.setSpecification1(from.getSpecification1());
	  }
	  if (from.getSpecification2() != null && !from.getSpecification2().equals(to.getSpecification2())) {
		 result = true;
		 to.setSpecification2(from.getSpecification2());
	  }
	  if (from.getComment() != null && !from.getComment().equals(to.getComment())) {
		 result = true;
		 to.setComment(from.getComment());
	  }
	  return result;
   }

   @Override
   protected GmRouteRound fillMain(Long muid) {
	  GmRouteRound domain = new GmRouteRound();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteRound toGis(ObjectMessage objectMessage) {
	  RRouteRound gis = new RRouteRound();
	  RRouteRound.Header header = new RRouteRound.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteRound());
	  gis.setHeader(header);
	  return gis;
   }
}
