package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.dto.nsi.CountyDTO;
import ru.armd.pbk.matcher.nsi.ICountyMatcher;
import ru.armd.pbk.repositories.nsi.CountyRepository;

import java.util.List;

/**
 * Сервис округов.
 */
@Service
public class CountyService
	extends BaseVersionDomainService<County, CountyDTO> {

   private static final Logger LOGGER = Logger.getLogger(CountyService.class);

   private CountyRepository repository;

   @Autowired
   CountyService(CountyRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public County toDomain(CountyDTO dto) {
	  return ICountyMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public CountyDTO toDTO(County domain) {
	  return ICountyMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получить территорию округа.
	*
	* @param id - ИД округа.
	* @return
	*/
   public List<String> getEgko(Long id) {
	  return repository.getEgko(id);
   }
}
