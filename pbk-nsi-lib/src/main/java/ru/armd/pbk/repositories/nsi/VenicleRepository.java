package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.Venicle;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.VenicleMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий ТС.
 */
@Repository
public class VenicleRepository
	extends BaseVersionDomainRepository<Venicle> {

   public static final Logger LOGGER = Logger.getLogger(VenicleRepository.class);

   @Autowired
   VenicleRepository(VenicleMapper mapper) {
	  super(NsiAuditType.NSI_TS, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить ТС по номеру.
	*
	* @param depoNumber - номер.
	* @return
	*/
   public Venicle getByDepoNumber(String depoNumber) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("depoNumber", depoNumber);
	  List<Venicle> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getByDepoNumber return more 1 domain");
   }

   /**
	* Получить ТС по ID ASDU.
	*
	* @param id - ID ASDU.
	* @return
	*/
   public Venicle getByAsduVenicleId(String id) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("asduVenicleId", id);
	  List<Venicle> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getByAsduVenicleId return more 1 domain");
   }
}