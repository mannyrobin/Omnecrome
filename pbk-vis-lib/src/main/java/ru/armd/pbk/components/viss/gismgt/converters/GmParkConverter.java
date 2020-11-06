package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.Park;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.domain.viss.gismgt.GmPark;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RPark;
import ru.armd.pbk.gismgt.schema.TPark;
import ru.armd.pbk.mappers.viss.gismgt.GmParkMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmTransportKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.ParkRepository;
import ru.armd.pbk.repositories.nsi.TsTypeRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TPark в Park.
 */
@Component
public class GmParkConverter
	extends AbstractPbkGmConverter<RPark, GmPark, Park> {

   public static final Logger LOGGER = Logger.getLogger(GmParkConverter.class);

   @Autowired
   private GmParkMapper mapper;

   @Autowired
   private ParkRepository repository;

   @Autowired
   private TsTypeRepository tsTypeRepository;

   @Autowired
   private GmTransportKindMapper gmTransportKindMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmParkConverter() {
	  super(VisAuditType.VIS_GISMGT_PARK);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmPark> getMapper() {
	  return mapper;
   }

   @Override
   protected GmPark convert(RPark gis) {
	  TPark body = gis.getBody();
	  GmPark domain = new GmPark();
	  saveChildren(body, domain);
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setGraphSectionOffset(getFloatValue(body.getGraphSectionOffset()));
	  domain.setNumber(getIntValue(body.getNumber()));
	  domain.setPhone(getStringValue(body.getPhone()));
	  domain.setShortName(getStringValue(body.getShortName()));
	  domain.setAddressString(getStringValue(body.getAddressString()));
	  domain.setWktGeom(getStringValue(body.getWktGeom()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	   try {
		   domain.setTransportKindMuid(getId(gis.getBody().getRouteTransportKind().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	   return domain;
   }

   @Override
   protected Boolean merge(GmPark to, GmPark from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getWktGeom() != null && !from.getWktGeom().equals(to.getWktGeom())) {
		 result = true;
		 to.setWktGeom(from.getWktGeom());
	  }
	  if (from.getShortName() != null && !from.getShortName().equals(to.getShortName())) {
		 result = true;
		 to.setShortName(from.getShortName());
	  }
	  if (from.getNumber() != null && !from.getNumber().equals(to.getNumber())) {
		 result = true;
		 to.setNumber(from.getNumber());
	  }
	  if (from.getPhone() != null && !from.getPhone().equals(to.getPhone())) {
		 result = true;
		 to.setPhone(from.getPhone());
	  }
	  if (from.getTransportKindMuid() != null && !from.getTransportKindMuid().equals(to.getTransportKindMuid())) {
		 result = true;
		 to.setTransportKindMuid(from.getTransportKindMuid());
	  }
	  if (from.getGraphSectionMuid() != null && !from.getGraphSectionMuid().equals(to.getGraphSectionMuid())) {
		 result = true;
		 to.setGraphSectionMuid(from.getGraphSectionMuid());
	  }
	  if (from.getAddressString() != null && !from.getAddressString().equals(to.getAddressString())) {
		 result = true;
		 to.setAddressString(from.getAddressString());
	  }
	  if (from.getGraphSectionOffset() != null && !from.getGraphSectionOffset().equals(to.getGraphSectionOffset())) {
		 result = true;
		 to.setGraphSectionOffset(from.getGraphSectionOffset());
	  }
	  if (from.getBuildingMuid() != null && !from.getBuildingMuid().equals(to.getBuildingMuid())) {
		 result = true;
		 to.setBuildingMuid(from.getBuildingMuid());
	  }
	  return result;
   }

   protected void saveChildren(TPark gis, GmPark domain) {
	  if (gis.getBuilding() != null) {
		 domain.setBuildingMuid(getId(gis.getBuilding().getValue().getHeader().getIdentSet()));
	  }
	  if (gis.getGraphSection() != null) {
		 domain.setGraphSectionMuid(getId(gis.getGraphSection().getValue().getHeader().getIdentSet()));
	  }
   }

   @Override
   protected void pbkInsert(Park pbkDomain, GmPark domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new Park();
	  } else {
		 pbkDomain.setVersionStartDate(new Date());
	  }
	  fillPbkDomain(pbkDomain, domain);
	  repository.saveVersion(pbkDomain);
   }

   protected void fillPbkDomain(Park pbkDomain, GmPark domain) {
	  pbkDomain.setName(domain.getName());
	  pbkDomain.setParkNumber(domain.getNumber());
	  pbkDomain.setShortName(domain.getShortName());
	  pbkDomain.setGmParkId(domain.getId());
	  Map<String, Object> params = new HashMap<>();
	  params.put("gmId", gmTransportKindMapper.getByMuid(domain.getTransportKindMuid()).getId());
	  pbkDomain.setAsduDepotId(domain.getAsduId());
	  TsType tsType = tsTypeRepository.getDomain(params);
	  if (tsType != null) {
		 pbkDomain.setTsKindId(tsType.getId());
	  }
   }

   @Override
   protected void pbkUpdate(Park pbkDomain, GmPark domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new Park();
	  }
	  fillPbkDomain(pbkDomain, domain);
	  repository.save(pbkDomain);
   }

   @Override
   protected IDomainRepository<Park> getPbkRepository() {
	  return repository;
   }

   @Override
   protected GmPark fillMain(Long muid) {
	  GmPark domain = new GmPark();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RPark toGis(ObjectMessage objectMessage) {
	  RPark gis = new RPark();
	  RPark.Header header = new RPark.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTPark());
	  gis.setHeader(header);
	  return gis;
   }
}
