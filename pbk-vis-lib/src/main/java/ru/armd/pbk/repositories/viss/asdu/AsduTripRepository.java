package ru.armd.pbk.repositories.viss.asdu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.asdu.AsduTrip;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.asdu.AsduTripMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Маппер для нарядов ЕАСУ.
 */
@Repository
public class AsduTripRepository
	extends BaseDomainRepository<AsduTrip> {

   public static final Logger LOGGER = Logger.getLogger(AsduTripRepository.class);

   @Autowired
   AsduTripRepository(AsduTripMapper mapper) {
	  super(VisAuditType.VIS_ASDU_TRIP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить наряд из АСДУ по ИД.
	*
	* @param asduTripId - ИД наряда.
	* @return
	*/
   public AsduTrip getByAsduTripId(Long asduTripId) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("asduTripId", asduTripId);
	  List<AsduTrip> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getByAsduTripId return more 1 domain");
   }
}
