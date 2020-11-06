package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.Telematics;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.TelematicsMapper;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий телематики.
 */
@Repository
public class TelematicsRepository
	extends BaseDomainRepository<Telematics> {

   public static final Logger LOGGER = Logger.getLogger(TelematicsRepository.class);

   private static final Integer BULK_COUNT = 419;

   private TelematicsMapper mapper;

   @Autowired
   TelematicsRepository(TelematicsMapper mapper) {
	  super(NsiAuditType.NSI_ASDU_TELEMATICS, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Множественная вставка данных на дату.
	*
	* @param list     данные.
	* @param workDate дата.
	*/
   public void insertBulk(List<Telematics> list, Date workDate) {
	  while (list.size() > BULK_COUNT) {
		 mapper.insertChunckOnDate(list.subList(0, Math.min(list.size(), BULK_COUNT)), workDate);
		 list.subList(0, Math.min(list.size(), BULK_COUNT)).clear();
	  }
	  mapper.insertChunckOnDate(list, workDate);
   }
}