package ru.armd.pbk.services.nsi.department;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.department.DepartmentPark;
import ru.armd.pbk.dto.nsi.department.DepartmentParkDTO;
import ru.armd.pbk.repositories.nsi.department.DepartmentParkRepository;

import java.util.Date;
import java.util.List;

/**
 * Сервис для работы с парками подраздилений.
 */
@Service
public class DepartmentParkService
	extends BaseDomainService<DepartmentPark, DepartmentParkDTO> {

   private static final Logger LOGGER = Logger.getLogger(DepartmentParkService.class);

   private DepartmentParkRepository departmnentparkRepository;

   @Autowired
   DepartmentParkService(DepartmentParkRepository repository) {
	  super(repository);
	  departmnentparkRepository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Transactional
   @Override
   public DepartmentParkDTO saveDTO(DepartmentParkDTO dto) {
	  DepartmentPark domain = toDomain(dto);
	  departmnentparkRepository.saveVersion(domain);
	  return dto;
   }

   @Transactional
   @Override
   public int delete(List<Long> ids) {
	  int count = 0;
	  for (Long id : ids) {
		 DepartmentPark domain = departmnentparkRepository.getById(id);
		 domain.setVersionEndDate(new Date());
		 departmnentparkRepository.save(domain);
		 count++;
	  }
	  return count;
   }

   @Override
   public DepartmentPark toDomain(DepartmentParkDTO dto) {
	  DepartmentPark domain = new DepartmentPark();
	  domain.setId(dto.getId());
	  domain.setDepartmentId(dto.getDepartmentId());
	  domain.setParkId(dto.getParkId());
	  return domain;
   }

   @Override
   public DepartmentParkDTO toDTO(DepartmentPark domain) {
	  DepartmentParkDTO dto = new DepartmentParkDTO();
	  dto.setId(domain.getId());
	  dto.setDepartmentId(domain.getDepartmentId());
	  dto.setParkId(domain.getParkId());
	  return dto;
   }
}