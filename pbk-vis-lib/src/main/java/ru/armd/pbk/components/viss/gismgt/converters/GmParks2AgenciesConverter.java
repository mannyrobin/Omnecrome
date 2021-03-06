package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmParks2Agencies;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RParks2Agencies;
import ru.armd.pbk.gismgt.schema.TParks2Agencies;
import ru.armd.pbk.mappers.viss.gismgt.GmParks2AgenciesMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TParks2Agencies в Parks2Agencies.
 */
@Component
public class GmParks2AgenciesConverter
	extends AbstractGmConverter<RParks2Agencies, GmParks2Agencies> {

   public static final Logger LOGGER = Logger.getLogger(GmParks2AgenciesConverter.class);

   @Autowired
   private GmParks2AgenciesMapper mapper;

   @Autowired
   private GmAgencyConverter agencyConverter;

   @Autowired
   private GmParkConverter parkConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmParks2AgenciesConverter() {
	  super(VisAuditType.VIS_GISMGT_P2A);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmParks2Agencies> getMapper() {
	  return mapper;
   }

   @Override
   protected GmParks2Agencies convert(RParks2Agencies gis) {
	  TParks2Agencies body = gis.getBody();
	  int size = 0;
	  if (body.getAgencies() != null && body.getParks() != null) {
		 size = body.getAgencies().size();
	  }
	  for (int i = 0; i < size; i++) {
		 GmParks2Agencies domain = new GmParks2Agencies();
		 domain.setMuid(getLongValueFromString(body.getMuid()) + i);
		 domain.setIsDelete(getIntValue(body.getSignDeleted()));
		 domain.setVersion(body.getVersion().intValue());
		 saveChildren(body, domain, i);
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("parkMuid", domain.getParkMuid());
		 params.put("agencyMuid", domain.getAgencyMuid());
		 if (mapper.getDomain(params) == null) {
			save(domain, OPERATION_CREATE);
		 }
	  }
	  return null;
   }

   protected void saveChildren(TParks2Agencies gis, GmParks2Agencies domain, int i) {
	  if (gis.getAgencies().get(i) != null) {
		 if (gis.getAgencies().get(i).getBody() != null) {
			agencyConverter.convertAndSaveToDB(gis.getAgencies().get(i)
				, gis.getAgencies().get(i).getHeader().getOperation().name());
		 }
		 domain.setAgencyMuid(getId(gis.getAgencies().get(i).getHeader().getIdentSet()));
	  }
	  if (gis.getParks().get(0) != null) {
		 domain.setParkMuid(getLongValueFromString(gis.getParks().get(0).getBody().getMuid()));
	  }
   }

   @Override
   protected Boolean merge(GmParks2Agencies to, GmParks2Agencies from) {
	  Boolean result = super.merge(to, from);
	  if (from.getParkMuid() != null && !from.getParkMuid().equals(to.getParkMuid())) {
		 result = true;
		 to.setParkMuid(from.getParkMuid());
	  }
	  if (from.getAgencyMuid() != null && !from.getAgencyMuid().equals(to.getAgencyMuid())) {
		 result = true;
		 to.setAgencyMuid(from.getAgencyMuid());
	  }
	  return result;
   }

   @Override
   protected GmParks2Agencies fillMain(Long muid) {
	  GmParks2Agencies domain = new GmParks2Agencies();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RParks2Agencies toGis(ObjectMessage objectMessage) {
	  return null;
   }

}
