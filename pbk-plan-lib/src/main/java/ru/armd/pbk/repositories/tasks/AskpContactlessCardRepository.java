package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.tasks.AskpContactlessCard;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.tasks.AskpContactlessCardMapper;

import java.util.Date;

/**
 * Репозиторий данных БСК от АСКП.
 */
@Repository
public class AskpContactlessCardRepository
	extends BaseDomainRepository<AskpContactlessCard> {

   public static final Logger LOGGER = Logger.getLogger(AskpContactlessCardRepository.class);

   private AskpContactlessCardMapper mapper;

   @Autowired
   AskpContactlessCardRepository(AskpContactlessCardMapper mapper) {
	  super(NsiAuditType.NSI_ASKP_CONTACTLESS_CARDS, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public void updateTasks(Date date) {
	  mapper.updateTasks(date);
   }

   /**
	* Получает заголовок вкладки "Проходы по БСК контролера" по id задания.
	*
	* @param taskId id задания
	* @return заголовок вкладки "Проходы по БСК контролера"
	*/
   public String getTitle(Long taskId) {
	  String title = null;
	  try {
		 title = mapper.getTitle(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить заголовок. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return title;
   }

   /**
	* Привязать проверку БСК к заданию.
	*
	* @param taskId - ИД задания.
	* @return количество привзяных.
	*/
   public int bind(Long taskId) {
	  try {
		 return mapper.bind(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось привязать проверку БСК к заданию. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.OTHER, null, message, e);
		 throw new PBKException(message, e);
	  }
   }
}
