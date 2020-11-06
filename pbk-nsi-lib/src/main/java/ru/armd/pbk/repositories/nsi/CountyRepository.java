package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.CountyMapper;

import java.util.List;

/**
 * Репозиторий округов.
 */
@Repository
public class CountyRepository
	extends BaseVersionDomainRepository<County> {

   public static final Logger LOGGER = Logger.getLogger(CountyRepository.class);

   private CountyMapper mapper;

   @Autowired
   CountyRepository(CountyMapper mapper) {
	  super(NsiAuditType.NSI_COUNTY, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить территорию округа.
	*
	* @param id - ИД округа.
	* @return
	*/
   public List<String> getEgko(Long id) {
	  return mapper.getEgko(id);
   }
}
