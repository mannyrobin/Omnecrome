package ru.armd.pbk.services.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.tasks.AskpContactlessCard;
import ru.armd.pbk.repositories.tasks.AskpContactlessCardRepository;

/**
 * Сервис данных БСК от АСКП.
 */
@Service
public class AskpContactlessCardService
	extends BaseDomainService<AskpContactlessCard, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(AskpContactlessCardService.class);

   AskpContactlessCardRepository repository;

   @Autowired
   AskpContactlessCardService(AskpContactlessCardRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получает заголовок вкладки "Проходы по БСК контролера" по id задания.
	*
	* @param taskId id задания
	* @return заголовок вкладки "Проходы по БСК контролера"
	*/
   public String getTitle(Long taskId) {
	  return repository.getTitle(taskId);
   }

   /**
	* Привязать проверку БСК к заданию.
	*
	* @param taskId ИД задания.
	*/
   @Transactional
   public int bind(Long taskId) {
	  return repository.bind(taskId);
   }
}
