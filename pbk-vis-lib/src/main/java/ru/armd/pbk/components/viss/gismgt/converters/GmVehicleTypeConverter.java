package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.TsCapacity;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.domain.viss.gismgt.GmVehicleType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RVehicleType;
import ru.armd.pbk.gismgt.schema.TVehicleType;
import ru.armd.pbk.mappers.viss.gismgt.GmTransportKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmVehicleTypeMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.TsCapacityRepository;
import ru.armd.pbk.repositories.nsi.TsTypeRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TVehicleType в VehicleType.
 */
@Component
public class GmVehicleTypeConverter
	extends AbstractPbkGmConverter<RVehicleType, GmVehicleType, TsCapacity> {

   public static final Logger LOGGER = Logger.getLogger(GmVehicleTypeConverter.class);

   @Autowired
   private GmVehicleTypeMapper mapper;

   @Autowired
   private TsCapacityRepository repository;

   @Autowired
   private TsTypeRepository tsTypeRepository;

   @Autowired
   private GmTransportKindMapper transportKindMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmVehicleTypeConverter() {
	  super(VisAuditType.VIS_GISMGT_VEHICLE_TYPE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmVehicleType> getMapper() {
	  return mapper;
   }

   @Override
   protected GmVehicleType convert(RVehicleType gis) {
	  TVehicleType body = gis.getBody();
	  GmVehicleType domain = new GmVehicleType();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  domain.setDimension(getStringValue(body.getDimension()));
	  domain.setEffectiveArea(getFloatValue(body.getEffectiveArea()));
	  domain.setFullLoadWeight(getFloatValue(body.getFullLoadWeight()));
	  domain.setHasFacilitiesForDisabled(body.getHasFacilitiesForDisabled().getValue().intValue());
	  domain.setMaxCapacity(getLongValue(body.getMaxCapacity()));
	  domain.setModel(getStringValue(body.getModel()));
	  domain.setSeatingCapacity(getLongValue(body.getSeatingCapacity()));
	  domain.setShortName(getStringValue(body.getShortName()));
	   try {
		   domain.setRouteTransportKindMuid(getId(body.getRouteTransportKind().getValue().getHeader().getIdentSet()));
	   } catch (Exception e) {
		   LOGGER.debug("Нет информации о поле.");
	   }
	  return domain;
   }

   @Override
   protected Boolean merge(GmVehicleType to, GmVehicleType from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getShortName() != null && !from.getShortName().equals(to.getShortName())) {
		 result = true;
		 to.setShortName(from.getShortName());
	  }
	  if (from.getModel() != null && !from.getModel().equals(to.getModel())) {
		 result = true;
		 to.setModel(from.getModel());
	  }
	  if (from.getRouteTransportKindMuid() != null && !from.getRouteTransportKindMuid().equals(to.getRouteTransportKindMuid())) {
		 result = true;
		 to.setRouteTransportKindMuid(from.getRouteTransportKindMuid());
	  }
	  if (from.getSeatingCapacity() != null && !from.getSeatingCapacity().equals(to.getSeatingCapacity())) {
		 result = true;
		 to.setSeatingCapacity(from.getSeatingCapacity());
	  }
	  if (from.getMaxCapacity() != null && !from.getMaxCapacity().equals(to.getMaxCapacity())) {
		 result = true;
		 to.setMaxCapacity(from.getMaxCapacity());
	  }
	  if (from.getEffectiveArea() != null && !from.getEffectiveArea().equals(to.getEffectiveArea())) {
		 result = true;
		 to.setEffectiveArea(from.getEffectiveArea());
	  }
	  if (from.getFullLoadWeight() != null && !from.getFullLoadWeight().equals(to.getFullLoadWeight())) {
		 result = true;
		 to.setFullLoadWeight(from.getFullLoadWeight());
	  }
	  if (from.getDimension() != null && !from.getDimension().equals(to.getDimension())) {
		 result = true;
		 to.setDimension(from.getDimension());
	  }
	  if (from.getHasFacilitiesForDisabled() != null && !from.getHasFacilitiesForDisabled().equals(to.getHasFacilitiesForDisabled())) {
		 result = true;
		 to.setHasFacilitiesForDisabled(from.getHasFacilitiesForDisabled());
	  }
	  return result;
   }

   @Override
   protected void pbkInsert(TsCapacity pbkDomain, GmVehicleType domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new TsCapacity();
	  }
	  pbkDomain.setCod(domain.getMuid().toString());
	  pbkDomain.setGmCapacityId(domain.getId());
	  pbkDomain.setName(domain.getName());
	  Map<String, Object> params = new HashMap<>();
	  params.put("gmId", transportKindMapper.getByMuid(domain.getRouteTransportKindMuid()).getId());
	  TsType tsType = tsTypeRepository.getDomain(params);
	  if (tsType != null) {
		 pbkDomain.setTsTypeId(tsType.getId());
	  }
	  repository.save(pbkDomain);
   }

   @Override
   protected void pbkUpdate(TsCapacity pbkDomain, GmVehicleType domain) {
	  pbkInsert(pbkDomain, domain);
   }

   @Override
   protected IDomainRepository<TsCapacity> getPbkRepository() {
	  return repository;
   }

   @Override
   protected GmVehicleType fillMain(Long muid) {
	  GmVehicleType domain = new GmVehicleType();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RVehicleType toGis(ObjectMessage objectMessage) {
	  RVehicleType gis = new RVehicleType();
	  RVehicleType.Header header = new RVehicleType.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTVehicleType());
	  gis.setHeader(header);
	  return gis;
   }

}
