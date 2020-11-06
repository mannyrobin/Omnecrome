package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.TsModel;
import ru.armd.pbk.dto.nsi.TSModelDTO;
import ru.armd.pbk.matcher.nsi.ITSModelMatcher;
import ru.armd.pbk.repositories.nsi.TsModelRepository;

/**
 * Сервис моделей ТС.
 */
@Service
public class TSModelService
	extends BaseDomainService<TsModel, TSModelDTO> {

   private static final Logger LOGGER = Logger.getLogger(TSModelService.class);

   @Autowired
   TSModelService(TsModelRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TSModelDTO toDTO(TsModel domain) {
	  return ITSModelMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public TsModel toDomain(TSModelDTO dto) {
	  return ITSModelMatcher.INSTANCE.toDomain(dto);
   }
}
