package ru.armd.pbk.repositories.nsi.district;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.district.DistrictMapper;

import java.util.List;

/**
 * Репозиторий районов.
 */
@Repository
public class DistrictRepository
	extends BaseVersionDomainRepository<District> {

   public static final Logger LOGGER = Logger.getLogger(DistrictRepository.class);

   private DistrictMapper mapper;

   @Autowired
   DistrictRepository(DistrictMapper mapper) {
	  super(NsiAuditType.NSI_DISTRICT, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение районнов, которые можно привязать к месту встречи.
	*
	* @param venueId ИД
	* @return
	*/
   public List<ISelectItem> getDistrictsByVenueId(Long venueId) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = mapper.getDistrictsByVenueId(venueId);
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получение привязаных районов к месту встречи.
	*
	* @param venueId ИД
	* @return
	*/
   public List<ISelectItem> getDistrictsBelongsVenue(Long venueId) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = mapper.getDistrictsBelongsVenue(venueId);
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получить территорию района.
	*
	* @param id - ИД района.
	* @return
	*/
   public List<String> getEgko(Long id) {
	  return mapper.getEgko(id);
   }
}
