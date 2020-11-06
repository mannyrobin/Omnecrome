package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.ShiftDepartment;
import ru.armd.pbk.dto.nsi.ShiftDepartmentDTO;
import ru.armd.pbk.matcher.nsi.IShiftDepartmentMatcher;
import ru.armd.pbk.repositories.nsi.ShiftDepartmentRepository;

/**
 * Сервис "Смены подразделения".
 */
@Service
public class ShiftDepartmentService
	extends BaseDomainService<ShiftDepartment, ShiftDepartmentDTO> {

   private static final Logger LOGGER = Logger
	   .getLogger(ShiftDepartmentService.class);

   /**
	* Конструктор.
	*
	* @param ShiftDepartmentRepository репозиторий.
	*/
   @Autowired
   public ShiftDepartmentService(ShiftDepartmentRepository ShiftDepartmentRepository) {
	  super(ShiftDepartmentRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public ShiftDepartment toDomain(ShiftDepartmentDTO dto) {
	  return IShiftDepartmentMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public ShiftDepartmentDTO toDTO(ShiftDepartment domain) {
	  return IShiftDepartmentMatcher.INSTANCE.toDTO(domain);
   }

}
