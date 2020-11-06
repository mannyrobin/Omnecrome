package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.dto.nsi.TsTypeDTO;
import ru.armd.pbk.matcher.nsi.ITsTypeMatcher;
import ru.armd.pbk.repositories.nsi.TsTypeRepository;

/**
 * Сервис Типы ТС.
 */
@Service
public class TsTypeService
	extends BaseDomainService<TsType, TsTypeDTO> {

   private static final Logger LOGGER = Logger.getLogger(TsTypeService.class);

   @Autowired
   TsTypeService(TsTypeRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TsType toDomain(TsTypeDTO dto) {
	  return ITsTypeMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public TsTypeDTO toDTO(TsType domain) {
	  return ITsTypeMatcher.INSTANCE.toDTO(domain);
   }

}
