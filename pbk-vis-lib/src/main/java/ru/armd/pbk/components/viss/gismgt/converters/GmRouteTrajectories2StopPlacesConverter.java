package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTrajectories2StopPlaces;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteTrajectories2StopPlaces;
import ru.armd.pbk.gismgt.schema.TRouteTrajectories2StopPlaces;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTrajectories2StopPlacesMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

import java.math.BigInteger;

/**
 * Конвертор из TRouteTrajectories2StopPlaces в RouteTrajectories2StopPlaces.
 */
@Component
public class GmRouteTrajectories2StopPlacesConverter
	extends AbstractGmConverter<RRouteTrajectories2StopPlaces, GmRouteTrajectories2StopPlaces> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteTrajectories2StopPlacesConverter.class);

   @Autowired
   private GmRouteTrajectories2StopPlacesMapper mapper;

   @Autowired
   private GmRouteTrajectoryConverter routeTrajectoryConverter;

   @Autowired
   private GmStopModeConverter stopModeConverter;

   @Autowired
   private GmStopPlaceConverter stopPlaceConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteTrajectories2StopPlacesConverter() {
	  super(VisAuditType.VIS_GISMGT_RT2SP);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteTrajectories2StopPlaces> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteTrajectories2StopPlaces convert(RRouteTrajectories2StopPlaces gis) {
	  TRouteTrajectories2StopPlaces body = gis.getBody();
	  GmRouteTrajectories2StopPlaces domain = new GmRouteTrajectories2StopPlaces();
	  saveChildren(body, domain);
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	   BigInteger version = body.getVersion();
	   if (version != null) {
		   domain.setVersion(version.intValue());
	   } else {
		   domain.setVersion(1);
	   }
	   domain.setIndx(body.getIndex().getValue().intValue());
	  domain.setIndx2(body.getIndex2().intValue());
	  domain.setLengthSector(getFloatValue(body.getLengthSector()));
	  domain.setComment(getStringValue(body.getComment()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteTrajectories2StopPlaces to, GmRouteTrajectories2StopPlaces from) {
	  Boolean result = super.merge(to, from);
	  if (from.getRouteTrajectoryMuid() != null && !from.getRouteTrajectoryMuid().equals(to.getRouteTrajectoryMuid())) {
		 result = true;
		 to.setRouteTrajectoryMuid(from.getRouteTrajectoryMuid());
	  }
	  if (from.getStopPlaceMuid() != null && !from.getStopPlaceMuid().equals(to.getStopPlaceMuid())) {
		 result = true;
		 to.setStopPlaceMuid(from.getStopPlaceMuid());
	  }
	  if (from.getModeMuid() != null && !from.getModeMuid().equals(to.getModeMuid())) {
		 result = true;
		 to.setModeMuid(from.getModeMuid());
	  }
	  if (from.getTypeMuid() != null && !from.getTypeMuid().equals(to.getTypeMuid())) {
		 result = true;
		 to.setTypeMuid(from.getTypeMuid());
	  }
	  if (from.getLengthSector() != null && !from.getLengthSector().equals(to.getLengthSector())) {
		 result = true;
		 to.setLengthSector(from.getLengthSector());
	  }
	  if (from.getComment() != null && !from.getComment().equals(to.getComment())) {
		 result = true;
		 to.setComment(from.getComment());
	  }
	  if (from.getIndx() != null && !from.getIndx().equals(to.getIndx())) {
		 result = true;
		 to.setIndx(from.getIndx());
	  }
	  if (from.getIndx2() != null && !from.getIndx2().equals(to.getIndx2())) {
		 result = true;
		 to.setIndx2(from.getIndx2());
	  }
	  return result;
   }

   protected void saveChildren(TRouteTrajectories2StopPlaces gis, GmRouteTrajectories2StopPlaces domain) {
	  if (gis.getRouteTrajectory() != null) {
		 if (gis.getRouteTrajectory().getValue().getBody() != null) {
			routeTrajectoryConverter.convertAndSaveToDB(gis.getRouteTrajectory().getValue()
				, gis.getRouteTrajectory().getValue().getHeader().getOperation().name());
		 } else {
			routeTrajectoryConverter.saveId(getId(gis.getRouteTrajectory().getValue().getHeader().getIdentSet()));
		 }
		 domain.setRouteTrajectoryMuid(getId(gis.getRouteTrajectory().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getMode() != null) {
		 if (gis.getMode().getValue().getBody() != null) {
			stopModeConverter.convertAndSaveToDB(gis.getMode().getValue()
				, gis.getMode().getValue().getHeader().getOperation().name());
		 } else {
			stopModeConverter.saveId(getId(gis.getMode().getValue().getHeader().getIdentSet()));
		 }
		 domain.setModeMuid(getId(gis.getMode().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getStopPlace() != null) {
		 if (gis.getStopPlace().getValue().getBody() != null) {
			stopPlaceConverter.convertAndSaveToDB(gis.getStopPlace().getValue()
				, gis.getStopPlace().getValue().getHeader().getOperation().name());
		 } else {
			stopPlaceConverter.saveId(getId(gis.getStopPlace().getValue().getHeader().getIdentSet()));
		 }
		 domain.setStopPlaceMuid(getId(gis.getStopPlace().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getType() != null) {
		 domain.setTypeMuid(getId(gis.getType().getValue().getHeader().getIdentSet()));
	  }
   }

   @Override
   protected GmRouteTrajectories2StopPlaces fillMain(Long muid) {
	  GmRouteTrajectories2StopPlaces domain = new GmRouteTrajectories2StopPlaces();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteTrajectories2StopPlaces toGis(ObjectMessage objectMessage) {
	  RRouteTrajectories2StopPlaces gis = new RRouteTrajectories2StopPlaces();
	  RRouteTrajectories2StopPlaces.Header header = new RRouteTrajectories2StopPlaces.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteTrajectories2StopPlaces());
	  gis.setHeader(header);
	  return gis;
   }

}
