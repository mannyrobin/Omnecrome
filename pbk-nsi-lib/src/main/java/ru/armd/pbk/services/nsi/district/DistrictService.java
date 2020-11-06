package ru.armd.pbk.services.nsi.district;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.dto.nsi.district.DistrictDTO;
import ru.armd.pbk.matcher.nsi.IDistrictMatcher;
import ru.armd.pbk.repositories.nsi.district.DistrictRepository;

import java.util.List;

/**
 * Сервис районов.
 */
@Service
public class DistrictService
	extends BaseVersionDomainService<District, DistrictDTO> {

   private static final Logger LOGGER = Logger.getLogger(DistrictService.class);

   private DistrictRepository repository;

   @Autowired
   DistrictService(DistrictRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public District toDomain(DistrictDTO dto) {
	  return IDistrictMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public DistrictDTO toDTO(District domain) {
	  return IDistrictMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Получение районнов по ИД места встречи.
	*
	* @param venueId ИД
	* @return
	*/
   public List<ISelectItem> getDistrictsByVenueId(Long venueId) {
	  return repository.getDistrictsByVenueId(venueId);
   }

   /**
	* Получить территорию района.
	*
	* @param id - ИД района.
	* @return
	*/
   public List<String> getEgko(Long id) {
	  return repository.getEgko(id);
   }
}
