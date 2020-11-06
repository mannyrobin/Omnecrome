package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.RouteTsKind;
import ru.armd.pbk.dto.nsi.RouteTsKindDTO;
import ru.armd.pbk.repositories.nsi.RouteTsKindRepository;

/**
 * Сервис типов ТС маршрутов.
 */
@Service
public class RouteTsKindService
	extends BaseDomainService<RouteTsKind, RouteTsKindDTO> {

   private static final Logger LOGGER = Logger.getLogger(RouteTsKindService.class);

   @Autowired
   RouteTsKindService(RouteTsKindRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public RouteTsKind toDomain(RouteTsKindDTO dto) {
	  RouteTsKind domain = new RouteTsKind();
	  domain.setCod(dto.getCod());
	  domain.setName(dto.getName());
	  domain.setDescription(dto.getDescription());
	  domain.setRouteTsKindId(dto.getRouteTsKindId());
	  domain.setId(dto.getId());
	  //domain.setIsDelete(dto.getIsDelete());
	  return domain;
   }

   @Override
   public RouteTsKindDTO toDTO(RouteTsKind domain) {
	  RouteTsKindDTO dto = new RouteTsKindDTO();
	  dto.setCod(domain.getCod());
	  dto.setName(domain.getName());
	  dto.setDescription(domain.getDescription());
	  dto.setRouteTsKindId(domain.getRouteTsKindId());
	  dto.setId(domain.getId());
	  //dto.setIsDelete(domain.getIsDelete());
	  return dto;
   }

}
