package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.TsModel;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.TsModelMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий моделей ТС.
 */
@Repository
public class TsModelRepository
	extends BaseDomainRepository<TsModel> {

   public static final Logger LOGGER = Logger.getLogger(TsModelRepository.class);

   @Autowired
   TsModelRepository(TsModelMapper mapper) {
	  super(NsiAuditType.NSI_TS_MODEL, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить модель по названию.
	*
	* @param model название модели.
	* @return
	*/
   public TsModel getByModel(String model) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("model", model);
	  List<TsModel> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getByModel return more 1 domain");
   }
}
