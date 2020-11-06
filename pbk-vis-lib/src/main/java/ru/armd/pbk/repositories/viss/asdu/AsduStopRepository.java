package ru.armd.pbk.repositories.viss.asdu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.asdu.AsduStop;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.asdu.AsduStopMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Маппер для нарядов ЕАСУ.
 */
@Repository
public class AsduStopRepository
	extends BaseDomainRepository<AsduStop> {

   public static final Logger LOGGER = Logger.getLogger(AsduStopRepository.class);

   @Autowired
   AsduStopRepository(AsduStopMapper mapper) {
	  super(VisAuditType.VIS_ASDU_STOP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить остановку АСДУ по ИД.
	*
	* @param asduStopId - ИД.
	* @return
	*/
   public AsduStop getByAsduStopId(Long asduStopId) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("asduStopId", asduStopId);
	  List<AsduStop> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getByAsduStopId return more 1 domain");
   }
}
