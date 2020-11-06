package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.domain.viss.gismgt.GmStop;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RStop;
import ru.armd.pbk.gismgt.schema.TStop;
import ru.armd.pbk.mappers.viss.gismgt.GmStopMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.stop.StopRepository;

import java.util.Date;

/**
 * Конвертор из TStop в Stop.
 */
@Component
public class GmStopConverter
	extends AbstractPbkGmConverter<RStop, GmStop, Stop> {

   public static final Logger LOGGER = Logger.getLogger(GmStopConverter.class);

   @Autowired
   private GmStopMapper mapper;

   @Autowired
   private StopRepository stopRepository;

   /**
	* Конструктор по умолчанию.
	*/
   public GmStopConverter() {
	  super(VisAuditType.VIS_GISMGT_STOP);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmStop> getMapper() {
	  return mapper;
   }

   @Override
   protected GmStop convert(RStop gis) {
	  TStop body = gis.getBody();
	  GmStop domain = new GmStop();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(body.getName());
	  domain.setNameInEnglish(body.getNameInEnglish());
	  domain.setNameFormTerminalPoint(body.getNameForTermnalPoint());
	  return domain;
   }

   @Override
   protected Boolean merge(GmStop to, GmStop from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getNameInEnglish() != null && !from.getNameInEnglish().equals(to.getNameInEnglish())) {
		 result = true;
		 to.setNameInEnglish(from.getNameInEnglish());
	  }
	  if (from.getNameFormTerminalPoint() != null && !from.getNameFormTerminalPoint().equals(to.getNameFormTerminalPoint())) {
		 result = true;
		 to.setNameFormTerminalPoint(from.getNameFormTerminalPoint());
	  }
	  if (from.getStreetMuid() != null && !from.getStreetMuid().equals(to.getStreetMuid())) {
		 result = true;
		 to.setStreetMuid(from.getStreetMuid());
	  }
	  if (from.getDirectionMuid() != null && !from.getDirectionMuid().equals(to.getDirectionMuid())) {
		 result = true;
		 to.setDirectionMuid(from.getDirectionMuid());
	  }
	  if (from.getDistrictMuid() != null && !from.getDistrictMuid().equals(to.getDistrictMuid())) {
		 result = true;
		 to.setDistrictMuid(from.getDistrictMuid());
	  }
	  if (from.getRegionMuid() != null && !from.getRegionMuid().equals(to.getRegionMuid())) {
		 result = true;
		 to.setRegionMuid(from.getRegionMuid());
	  }
	  return result;
   }

   @Override
   protected void pbkUpdate(Stop pbkDomain, GmStop domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new Stop();
	  }
	  fillPbkDomain(pbkDomain, domain);
	  stopRepository.save(pbkDomain);
   }

   @Override
   protected void pbkInsert(Stop pbkDomain, GmStop domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new Stop();
	  } else {
		 pbkDomain.setVersionStartDate(new Date());
	  }
	  fillPbkDomain(pbkDomain, domain);
	  stopRepository.saveVersion(pbkDomain);
   }

   protected void fillPbkDomain(Stop pbkDomain, GmStop domain) {
	  pbkDomain.setName(domain.getName());
	  pbkDomain.setGmStopId(domain.getId());
   }

   @Override
   protected IDomainRepository<Stop> getPbkRepository() {
	  return stopRepository;
   }

   @Override
   protected GmStop fillMain(Long muid) {
	  GmStop domain = new GmStop();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RStop toGis(ObjectMessage objectMessage) {
	  RStop gis = new RStop();
	  RStop.Header header = new RStop.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTStop());
	  gis.setHeader(header);
	  return gis;
   }
}
