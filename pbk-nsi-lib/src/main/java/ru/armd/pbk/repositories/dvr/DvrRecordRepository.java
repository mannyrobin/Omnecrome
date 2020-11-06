package ru.armd.pbk.repositories.dvr;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.dvr.DvrRecord;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.dvr.DvrRecordMapper;

/**
 * Репозиторий для записи с видеорегистратора.
 */
@Repository
public class DvrRecordRepository
	extends BaseDomainRepository<DvrRecord> {

   public static final Logger LOGGER = Logger.getLogger(DvrRecordRepository.class);
   private DvrRecordMapper mapper;

   @Autowired
   DvrRecordRepository(DvrRecordMapper mapper) {
	  super(NsiAuditType.NSI_DVR_RECORD, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Привязать проверку записи с ведеорегистраторов к заданию.
	*
	* @param taskId - ИД задания.
	* @return количество привзяных.
	*/
   public int bind(Long taskId) {
	  try {
		 return mapper.bind(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось привязать проверку ПУсК к заданию. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.OTHER, null, message, e);
		 throw new PBKException(message, e);
	  }
   }
}
