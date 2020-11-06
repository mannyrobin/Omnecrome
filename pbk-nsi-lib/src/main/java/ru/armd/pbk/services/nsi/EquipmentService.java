package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.dto.nsi.EquipmentDTO;
import ru.armd.pbk.matcher.nsi.IEquipmentMatcher;
import ru.armd.pbk.repositories.nsi.EquipmentRepository;

/**
 * Сервис бортового оборудования.
 */
@Service
public class EquipmentService
	extends BaseVersionDomainService<Equipment, EquipmentDTO> {

   private static final Logger LOGGER = Logger.getLogger(EquipmentService.class);

   @Autowired
   EquipmentService(EquipmentRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public EquipmentDTO toDTO(Equipment domain) {
	  return IEquipmentMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Equipment toDomain(EquipmentDTO dto) {
	  return IEquipmentMatcher.INSTANCE.toDomain(dto);
   }
}
