package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.domain.viss.gismgt.GmDistrict;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RDistrict;
import ru.armd.pbk.gismgt.schema.REGKODistrict;
import ru.armd.pbk.gismgt.schema.TDistrict;
import ru.armd.pbk.mappers.viss.gismgt.GmDistrictMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.CountyRepository;

import java.util.Date;

/**
 * Конвертор из TDistrict в GmDistrict.
 */
@Component
public class GmDistrictConverter
	extends AbstractPbkGmConverter<RDistrict, GmDistrict, County> {

   public static final Logger LOGGER = Logger.getLogger(GmDistrictConverter.class);

   @Autowired
   private GmDistrictMapper mapper;

   @Autowired
   private CountyRepository repository;

   @Autowired
   private GmDistrictEgkoConverter egkoConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmDistrictConverter() {
	  super(VisAuditType.VIS_GISMGT_DISTRICT);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmDistrict> getMapper() {
	  return mapper;
   }

   @Override
   public void convertAndSaveToDB(RDistrict gis, String operation) {
	  super.convertAndSaveToDB(gis, operation);
	  if (gis.getBody().getEgkoDistricts() != null) {
		 for (REGKODistrict distirct : gis.getBody().getEgkoDistricts().getDistricts()) {
			egkoConverter.convertAndSaveToDB(distirct, OPERATION_CREATE);
		 }
	  }
   }


   @Override
   protected GmDistrict convert(RDistrict gis) {
	  TDistrict body = gis.getBody();
	  GmDistrict domain = new GmDistrict();
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
	  return domain;
   }

   @Override
   protected Boolean merge(GmDistrict to, GmDistrict from) {
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
	  return result;
   }


   @Override
   protected void pbkUpdate(County pbkDomain, GmDistrict domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new County();
	  }
	  pbkDomain.setName(domain.getName());
	  pbkDomain.setGmDistrictId(domain.getId());
	  pbkDomain.setCod(domain.getMuid().toString());
	  pbkDomain.setShortName(domain.getShortName());
	  repository.save(pbkDomain);
   }

   @Override
   protected void pbkInsert(County pbkDomain, GmDistrict domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new County();
	  } else {
		 pbkDomain.setVersionStartDate(new Date());
	  }
	  pbkDomain.setName(domain.getName());
	  pbkDomain.setGmDistrictId(domain.getId());
	  pbkDomain.setCod(domain.getMuid().toString());
	  pbkDomain.setShortName(domain.getShortName());
	  repository.saveVersion(pbkDomain);
   }

   @Override
   protected IDomainRepository<County> getPbkRepository() {
	  return repository;
   }

   @Override
   protected GmDistrict fillMain(Long muid) {
	  GmDistrict domain = new GmDistrict();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RDistrict toGis(ObjectMessage objectMessage) {
	  RDistrict gis = new RDistrict();
	  RDistrict.Header header = new RDistrict.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTDistrict());
	  gis.setHeader(header);
	  return gis;
   }

}