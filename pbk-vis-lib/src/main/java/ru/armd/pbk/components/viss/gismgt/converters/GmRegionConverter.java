package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.domain.viss.gismgt.GmRegion;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.REGKORegion;
import ru.armd.pbk.gismgt.schema.RRegion;
import ru.armd.pbk.gismgt.schema.TRegion;
import ru.armd.pbk.mappers.viss.gismgt.GmDistrictMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmRegionMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.CountyRepository;
import ru.armd.pbk.repositories.nsi.district.DistrictRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Конвертер из TRegion в GmRegion.
 */
@Component
public class GmRegionConverter
	extends AbstractPbkGmConverter<RRegion, GmRegion, District> {

   public static final Logger LOGGER = Logger.getLogger(GmRegionConverter.class);

   @Autowired
   private GmRegionMapper mapper;

   @Autowired
   private GmDistrictConverter districtConverter;

   @Autowired
   private DistrictRepository repository;

   @Autowired
   private CountyRepository countyRepository;

   @Autowired
   private GmDistrictMapper districtMapper;

   @Autowired
   private GmRegionEgkoConverter egkoConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRegionConverter() {
	  super(VisAuditType.VIS_GISMGT_REGION);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRegion> getMapper() {
	  return mapper;
   }

   @Override
   public void convertAndSaveToDB(RRegion gis, String operation) {
	  super.convertAndSaveToDB(gis, operation);
	  if (gis.getBody().getEgkoRegions() != null) {
		 for (REGKORegion region : gis.getBody().getEgkoRegions().getRegions()) {
			egkoConverter.convertAndSaveToDB(region, OPERATION_CREATE);
		 }
	  }
   }

   @Override
   protected GmRegion convert(RRegion gis) {
	  TRegion body = gis.getBody();
	  GmRegion domain = new GmRegion();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  domain.setShortName(getStringValue(body.getShortName()));
	  domain.setOmkCode(getIntValue(body.getOmkCode()).toString());
	  domain.setTranslitName(getStringValue(body.getTranslitName()));
	  domain.setTerritorialUnitTypeCode(getStringValue(body.getTerritorialUnitTypeCode()));
	  domain.setOkatoCode(getStringValue(body.getOkatoCode()));
	  domain.setDistrictCode(getStringValue(body.getDistrictCode()));
	  if (body.getDistrict() != null) {
		 if (body.getDistrict().getValue().getBody() != null) {
			districtConverter.convertAndSaveToDB(body.getDistrict().getValue()
				, body.getDistrict().getValue().getHeader().getOperation().name());
		 } else {
			districtConverter.saveId(getId(body.getDistrict().getValue().getHeader().getIdentSet()));
		 }
		 domain.setDistrictMuid(getId(body.getDistrict().getValue().getHeader().getIdentSet()));
	  }
	  return domain;
   }

   @Override
   protected Boolean merge(GmRegion to, GmRegion from) {
	  Boolean result = super.merge(to, from);
	  if (from.getOmkCode() != null && !from.getOmkCode().equals(to.getOmkCode())) {
		 result = true;
		 to.setOmkCode(from.getOmkCode());
	  }
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getShortName() != null && !from.getShortName().equals(to.getShortName())) {
		 result = true;
		 to.setShortName(from.getShortName());
	  }
	  if (from.getTranslitName() != null && !from.getTranslitName().equals(to.getTranslitName())) {
		 result = true;
		 to.setTranslitName(from.getTranslitName());
	  }
	  if (from.getTerritorialUnitTypeCode() != null && !from.getTerritorialUnitTypeCode().equals(to.getTerritorialUnitTypeCode())) {
		 result = true;
		 to.setTerritorialUnitTypeCode(from.getTerritorialUnitTypeCode());
	  }
	  if (from.getOkatoCode() != null && !from.getOkatoCode().equals(to.getOkatoCode())) {
		 result = true;
		 to.setOkatoCode(from.getOkatoCode());
	  }
	  if (from.getDistrictCode() != null && !from.getDistrictCode().equals(to.getDistrictCode())) {
		 result = true;
		 to.setDistrictCode(from.getDistrictCode());
	  }
	  if (from.getDistrictMuid() != null && !from.getDistrictMuid().equals(to.getDistrictMuid())) {
		 result = true;
		 to.setDistrictMuid(from.getDistrictMuid());
	  }
	  return result;
   }

   protected void fillPbkDomain(District pbkDomain, GmRegion domain) {
	  pbkDomain.setGmRegionId(domain.getId());
	  pbkDomain.setName(domain.getName());
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("gmId", districtMapper.getByMuid(domain.getDistrictMuid()).getId());
	  County county = countyRepository.getDomain(params);
	  pbkDomain.setCod(domain.getMuid().toString());
	  if (county != null) {
		 pbkDomain.setCountyId(county.getHeadId());
		 pbkDomain.setPlanCountyId(county.getHeadId());
	  }
   }

   @Override
   protected void pbkUpdate(District pbkDomain, GmRegion domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new District();
	  }
	  fillPbkDomain(pbkDomain, domain);
	  repository.save(pbkDomain);
   }

   @Override
   protected void pbkInsert(District pbkDomain, GmRegion domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new District();
	  } else {
		 pbkDomain.setVersionStartDate(new Date());
	  }
	  fillPbkDomain(pbkDomain, domain);
	  repository.saveVersion(pbkDomain);
   }


   @Override
   protected IDomainRepository<District> getPbkRepository() {
	  return repository;
   }

   @Override
   protected GmRegion fillMain(Long muid) {
	  GmRegion domain = new GmRegion();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRegion toGis(ObjectMessage objectMessage) {
	  RRegion gis = new RRegion();
	  RRegion.Header header = new RRegion.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRegion());
	  gis.setHeader(header);
	  return gis;
   }

}