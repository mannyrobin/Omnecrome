package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmTerminalStation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RTerminalStation;
import ru.armd.pbk.gismgt.schema.TTerminalStation;
import ru.armd.pbk.mappers.viss.gismgt.GmTerminalStationMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

import java.util.Arrays;

/**
 * Конвертор из TTerminalStation в TerminalStation.
 */
@Component
public class GmTerminalStationConverter
	extends AbstractGmConverter<RTerminalStation, GmTerminalStation> {

   public static final Logger LOGGER = Logger.getLogger(GmTerminalStationConverter.class);

   @Autowired
   private GmTerminalStationMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmTerminalStationConverter() {
	  super(VisAuditType.VIS_GISMGT_TER_STATION);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmTerminalStation> getMapper() {
	  return mapper;
   }

   @Override
   protected GmTerminalStation convert(RTerminalStation gis) {
	  TTerminalStation body = gis.getBody();
	  GmTerminalStation domain = new GmTerminalStation();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(body.getName());
	  domain.setAddressString(getStringValue(body.getAddressString()));
	  domain.setGraphSectionOffset(getFloatValue(body.getGraphSectionOffset()));
	  domain.setPhoto(getByteValue(body.getPhoto()));
	  domain.setWktGeom(getStringValue(body.getWktGeom()));
	  domain.setStartDate(getDateValue(body.getStartDate()));
	  domain.setHasParking(getIntValue(body.getHasParking()));
	  domain.setHasBus(getIntValue(body.getHasBus()));
	  domain.setHasSpeedTram(getIntValue(body.getHasSpeedTram()));
	  domain.setHasTram(getIntValue(body.getHasTram()));
	  domain.setHasTrolley(getIntValue(body.getHasTrolley()));
	  domain.setPhone(getStringValue(body.getPhone()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmTerminalStation to, GmTerminalStation from) {
	  Boolean result = super.merge(to, from);
	  if (from.getWktGeom() != null && !from.getWktGeom().equals(to.getWktGeom())) {
		 result = true;
		 to.setWktGeom(from.getWktGeom());
	  }
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getPhone() != null && !from.getPhone().equals(to.getPhone())) {
		 result = true;
		 to.setPhone(from.getPhone());
	  }
	  if (from.getOrderMuid() != null && !from.getOrderMuid().equals(to.getOrderMuid())) {
		 result = true;
		 to.setOrderMuid(from.getOrderMuid());
	  }
	  if (from.getStreetMuid() != null && !from.getStreetMuid().equals(to.getStreetMuid())) {
		 result = true;
		 to.setStreetMuid(from.getStreetMuid());
	  }
	  if (from.getBuildingMuid() != null && !from.getBuildingMuid().equals(to.getBuildingMuid())) {
		 result = true;
		 to.setBuildingMuid(from.getBuildingMuid());
	  }
	  if (from.getParkMuid() != null && !from.getParkMuid().equals(to.getParkMuid())) {
		 result = true;
		 to.setParkMuid(from.getParkMuid());
	  }
	  if (from.getGraphSectionMuid() != null && !from.getGraphSectionMuid().equals(to.getGraphSectionMuid())) {
		 result = true;
		 to.setGraphSectionMuid(from.getGraphSectionMuid());
	  }
	  if (from.getPhoto() != null && !Arrays.equals(from.getPhoto(), to.getPhoto())) {
		 result = true;
		 to.setPhoto(from.getPhoto());
	  }
	  if (from.getStartDate() != null && !from.getStartDate().equals(to.getStartDate())) {
		 result = true;
		 to.setStartDate(from.getStartDate());
	  }
	  if (from.getAddressString() != null && !from.getAddressString().equals(to.getAddressString())) {
		 result = true;
		 to.setAddressString(from.getAddressString());
	  }
	  if (from.getHasParking() != null && !from.getHasParking().equals(to.getHasParking())) {
		 result = true;
		 to.setHasParking(from.getHasParking());
	  }
	  if (from.getGraphSectionOffset() != null && !from.getGraphSectionOffset().equals(to.getGraphSectionOffset())) {
		 result = true;
		 to.setGraphSectionOffset(from.getGraphSectionOffset());
	  }
	  if (from.getHasBus() != null && !from.getHasBus().equals(to.getHasBus())) {
		 result = true;
		 to.setHasBus(from.getHasBus());
	  }
	  if (from.getHasTrolley() != null && !from.getHasTrolley().equals(to.getHasTrolley())) {
		 result = true;
		 to.setHasTrolley(from.getHasTrolley());
	  }
	  if (from.getHasTram() != null && !from.getHasTram().equals(to.getHasTram())) {
		 result = true;
		 to.setHasTram(from.getHasTram());
	  }
	  if (from.getHasSpeedTram() != null && !from.getHasSpeedTram().equals(to.getHasSpeedTram())) {
		 result = true;
		 to.setHasSpeedTram(from.getHasSpeedTram());
	  }
	  return result;
   }

   @Override
   protected GmTerminalStation fillMain(Long muid) {
	  GmTerminalStation domain = new GmTerminalStation();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RTerminalStation toGis(ObjectMessage objectMessage) {
	  RTerminalStation gis = new RTerminalStation();
	  RTerminalStation.Header header = new RTerminalStation.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTTerminalStation());
	  gis.setHeader(header);
	  return gis;
   }
}
