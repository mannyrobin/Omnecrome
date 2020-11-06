package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.TsCapacity;
import ru.armd.pbk.dto.nsi.TsCapacityDTO;
import ru.armd.pbk.repositories.nsi.TsCapacityRepository;

/**
 * Сервис для работы с вместимостью ТС.
 */
@Service
public class TsCapacityService
	extends BaseDomainService<TsCapacity, TsCapacityDTO> {

   private static final Logger LOGGER = Logger.getLogger(TsCapacityService.class);

   @Autowired
   TsCapacityService(TsCapacityRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TsCapacity toDomain(TsCapacityDTO dto) {
	  TsCapacity domain = new TsCapacity();
	  domain.setCod(dto.getCod());
	  domain.setDescription(dto.getDescription());
	  domain.setId(dto.getId());
	  //domain.setIsDelete(dto.getIsDelete());
	  domain.setName(dto.getName());
	  domain.setTsTypeId(dto.getTsTypeId());
	  return domain;
   }

   @Override
   public TsCapacityDTO toDTO(TsCapacity domain) {
	  TsCapacityDTO dto = new TsCapacityDTO();
	  dto.setCod(domain.getCod());
	  dto.setDescription(domain.getDescription());
	  dto.setId(domain.getId());
	  //dto.setIsDelete(domain.getIsDelete());
	  dto.setName(domain.getName());
	  dto.setTsTypeId(domain.getTsTypeId());
	  return dto;
   }

}
