package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.domain.viss.gismgt.GmStop;
import ru.armd.pbk.domain.viss.gismgt.GmStopPlace;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RStopPlace;
import ru.armd.pbk.gismgt.schema.TStopPlace;
import ru.armd.pbk.mappers.viss.gismgt.GmStopMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmStopPlaceMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.stop.StopRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TStopPlace в StopPlace.
 */
@Component
public class GmStopPlaceConverter
	extends AbstractGmConverter<RStopPlace, GmStopPlace> {

   public static final Logger LOGGER = Logger.getLogger(GmStopPlaceConverter.class);

   @Autowired
   private GmStopPlaceMapper mapper;

   @Autowired
   private GmStopMapper gmStopMapper;

   @Autowired
   private StopRepository stopRepository;

   @Autowired
   private GmStopStateConverter stopStateConverter;

   @Autowired
   private GmStopConverter stopConverter;

   @Autowired
   private GmStopModeConverter stopModeConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmStopPlaceConverter() {
	  super(VisAuditType.VIS_GISMGT_STOP_PLACE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmStopPlace> getMapper() {
	  return mapper;
   }

   @Override
   protected GmStopPlace convert(RStopPlace gis) {
	  TStopPlace body = gis.getBody();
	  GmStopPlace domain = new GmStopPlace();
	  saveChildren(body, domain);
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setBuildingAddressString(getStringValue(body.getBuildingAddressString()));
	  domain.setBuildingDistance(getFloatValue(body.getBuildingDistance()));
	  domain.setChangeDate(getDateValue(body.getChangeDate()));
	  domain.setComment(getStringValue(body.getComment()));
	  domain.setDisplayPanelASUT(getStringValue(body.getDisplayPanelASUT()));
	  domain.setDisplayPanelIMEI(getStringValue(body.getDisplayPanelIMEI()));
	  domain.setDisplayPanelSim(getStringValue(body.getDisplayPanelSim()));
	  domain.setDisplayPanelType(getStringValue(body.getDisplayPanelType()));
	  domain.setEndDate(getDateValue(body.getEndDate()));
	  domain.setGraphSectionOffset(getFloatValue(body.getGraphSectionOffset()));
	  domain.setHasBay(getIntValue(body.getHasBay()));
	  domain.setHasDisplayPanel(getIntValue(body.getHasDisplayPanel()));
	  domain.setHasFacilitiesForDisabled(getIntValue(body.getHasFacilitiesForDisabled()));
	  domain.setIsTechnical(getIntValue(body.getIsTechnical()));
	  domain.setPhoto(getByteValue(body.getPhoto()));
	  domain.setStartDate(getDateValue(body.getStartDate()));
	  domain.setWktGeom(getStringValue(body.getWktGeom()));

	  domain.setHasBus(getIntValue(body.getHasBus()));
	  domain.setHasSpeedTram(getIntValue(body.getHasSpeedTram()));
	  domain.setHasTram(getIntValue(body.getHasTram()));
	  domain.setHasTrolley(getIntValue(body.getHasTrolley()));
	  domain.setGraphTramSectionOffset(getFloatValue(body.getGraphTramSectionOffset()));
	  domain.setSuffix(getStringValue(body.getSuffix()));
	  saveStopEasuId(domain);
	  return domain;
   }

   @Override
   protected Boolean merge(GmStopPlace to, GmStopPlace from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getWktGeom() != null && !from.getWktGeom().equals(to.getWktGeom())) {
		 result = true;
		 to.setWktGeom(from.getWktGeom());
	  }
	  if (from.getStopMuid() != null && !from.getStopMuid().equals(to.getStopMuid())) {
		 result = true;
		 to.setStopMuid(from.getStopMuid());
	  }
	  if (from.getOrderMuid() != null && !from.getOrderMuid().equals(to.getOrderMuid())) {
		 result = true;
		 to.setOrderMuid(from.getOrderMuid());
	  }
	  if (from.getBuildingMuid() != null && !from.getBuildingMuid().equals(to.getBuildingMuid())) {
		 result = true;
		 to.setBuildingMuid(from.getBuildingMuid());
	  }
	  if (from.getGraphSectionMuid() != null && !from.getGraphSectionMuid().equals(to.getGraphSectionMuid())) {
		 result = true;
		 to.setGraphSectionMuid(from.getGraphSectionMuid());
	  }
	  if (from.getGraphTramSectionMuid() != null && !from.getGraphTramSectionMuid().equals(to.getGraphTramSectionMuid())) {
		 result = true;
		 to.setGraphTramSectionMuid(from.getGraphTramSectionMuid());
	  }
	  if (from.getStartDate() != null && !from.getStartDate().equals(to.getStartDate())) {
		 result = true;
		 to.setStartDate(from.getStartDate());
	  }
	  if (from.getChangeDate() != null && !from.getChangeDate().equals(to.getChangeDate())) {
		 result = true;
		 to.setChangeDate(from.getChangeDate());
	  }
	  if (from.getEndDate() != null && !from.getEndDate().equals(to.getEndDate())) {
		 result = true;
		 to.setEndDate(from.getEndDate());
	  }
	  if (from.getPhoto() != null && !Arrays.equals(from.getPhoto(), to.getPhoto())) {
		 result = true;
		 to.setPhoto(from.getPhoto());
	  }
	  if (from.getIsTechnical() != null && !from.getIsTechnical().equals(to.getIsTechnical())) {
		 result = true;
		 to.setIsTechnical(from.getIsTechnical());
	  }
	  if (from.getHasFacilitiesForDisabled() != null && !from.getHasFacilitiesForDisabled().equals(to.getHasFacilitiesForDisabled())) {
		 result = true;
		 to.setHasFacilitiesForDisabled(from.getHasFacilitiesForDisabled());
	  }
	  if (from.getHasBay() != null && !from.getHasBay().equals(to.getHasBay())) {
		 result = true;
		 to.setHasBay(from.getHasBay());
	  }
	  if (from.getHasDisplayPanel() != null && !from.getHasDisplayPanel().equals(to.getHasDisplayPanel())) {
		 result = true;
		 to.setHasDisplayPanel(from.getHasDisplayPanel());
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
	  if (from.getDisplayPanelType() != null && !from.getDisplayPanelType().equals(to.getDisplayPanelType())) {
		 result = true;
		 to.setDisplayPanelType(from.getDisplayPanelType());
	  }
	  if (from.getDisplayPanelIMEI() != null && !from.getDisplayPanelIMEI().equals(to.getDisplayPanelIMEI())) {
		 result = true;
		 to.setDisplayPanelIMEI(from.getDisplayPanelIMEI());
	  }
	  if (from.getDisplayPanelSim() != null && !from.getDisplayPanelSim().equals(to.getDisplayPanelSim())) {
		 result = true;
		 to.setDisplayPanelSim(from.getDisplayPanelSim());
	  }
	  if (from.getDisplayPanelASUT() != null && !from.getDisplayPanelASUT().equals(to.getDisplayPanelASUT())) {
		 result = true;
		 to.setDisplayPanelASUT(from.getDisplayPanelASUT());
	  }
	  if (from.getBuildingAddressString() != null && !from.getBuildingAddressString().equals(to.getBuildingAddressString())) {
		 result = true;
		 to.setBuildingAddressString(from.getBuildingAddressString());
	  }
	  if (from.getComment() != null && !from.getComment().equals(to.getComment())) {
		 result = true;
		 to.setComment(from.getComment());
	  }
	  if (from.getGraphSectionOffset() != null && !from.getGraphSectionOffset().equals(to.getGraphSectionOffset())) {
		 result = true;
		 to.setGraphSectionOffset(from.getGraphSectionOffset());
	  }
	  if (from.getGraphTramSectionOffset() != null && !from.getGraphTramSectionOffset().equals(to.getGraphTramSectionOffset())) {
		 result = true;
		 to.setGraphTramSectionOffset(from.getGraphTramSectionOffset());
	  }
	  if (from.getBuildingDistance() != null && !from.getBuildingDistance().equals(to.getBuildingDistance())) {
		 result = true;
		 to.setBuildingDistance(from.getBuildingDistance());
	  }
	  if (from.getSuffix() != null && !from.getSuffix().equals(to.getSuffix())) {
		 result = true;
		 to.setSuffix(from.getSuffix());
	  }
	  if (from.getStopStateMuid() != null && !from.getStopStateMuid().equals(to.getStopStateMuid())) {
		 result = true;
		 to.setStopStateMuid(from.getStopStateMuid());
	  }
	  if (from.getStopModeMuid() != null && !from.getStopModeMuid().equals(to.getStopModeMuid())) {
		 result = true;
		 to.setStopModeMuid(from.getStopModeMuid());
	  }
	  return result;
   }

   protected void saveChildren(TStopPlace gis, GmStopPlace domain) {
	  if (gis.getBuilding() != null) {
		 domain.setBuildingMuid(getId(gis.getBuilding().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getOrder() != null) {
		 domain.setOrderMuid(getId(gis.getOrder().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getMode() != null) {
		 if (gis.getMode().getValue().getBody() != null) {
			stopModeConverter.convertAndSaveToDB(gis.getMode().getValue()
				, gis.getMode().getValue().getHeader().getOperation().name());
		 } else {
			stopModeConverter.saveId(getId(gis.getMode().getValue().getHeader().getIdentSet()));
		 }
		 domain.setStopModeMuid(getId(gis.getMode().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getGraphSection() != null) {
		 domain.setGraphSectionMuid(getId(gis.getGraphSection().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getStop() != null) {
		 if (gis.getStop().getValue().getBody() != null) {
			stopConverter.convertAndSaveToDB(gis.getStop().getValue()
				, gis.getStop().getValue().getHeader().getOperation().name());
		 } else {
			stopConverter.saveId(getId(gis.getStop().getValue().getHeader().getIdentSet()));
		 }
		 domain.setStopMuid(getId(gis.getStop().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getState() != null) {
		 if (gis.getState().getValue().getBody() != null) {
			stopStateConverter.convertAndSaveToDB(gis.getState().getValue()
				, gis.getState().getValue().getHeader().getOperation().name());
		 } else {
			stopStateConverter.saveId(getId(gis.getState().getValue().getHeader().getIdentSet()));
		 }
		 domain.setStopStateMuid(getId(gis.getState().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getGraphTramSection() != null) {
		 domain.setGraphTramSectionMuid(getId(gis.getGraphTramSection().getValue().getHeader().getIdentSet()));
	  }
   }

   @Override
   protected GmStopPlace fillMain(Long muid) {
	  GmStopPlace domain = new GmStopPlace();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RStopPlace toGis(ObjectMessage objectMessage) {
	  RStopPlace gis = new RStopPlace();
	  RStopPlace.Header header = new RStopPlace.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTStopPlace());
	  gis.setHeader(header);
	  return gis;
   }

   protected void saveStopEasuId(GmStopPlace stopDomain) {
	  try {
		 Map<String, Object> params = new HashMap<>();
		  GmStop gmStop = gmStopMapper.getByMuid(stopDomain.getStopMuid());
		  if (gmStop != null) {
			  params.put("gmId", gmStop.getId());
			  Stop domain = stopRepository.getDomain(params);
			  Long asduStop = Long.valueOf(stopDomain.getAsduId());
			  if (domain != null && !asduStop.equals(domain.getAsduStopId())) {
				 domain.setAsduStopId(asduStop);
				 stopRepository.updateAsduId(domain);
			  }
		  } else {
			  LOGGER.debug("Не найден gmStop у остановки с MUID: " + stopDomain.getMuid());
		  }
	  } catch (Exception e) {
		 LOGGER.debug("Не удалось сохранить ID ASDU у остановки с MUID: " + stopDomain.getMuid());
	  }
   }
}
