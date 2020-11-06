package ru.armd.pbk.components.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.services.core.AuditService;

/**
 * Класс джоба очистки логов аудита.
 */
@Component
public class AuditScheduler {

   @Autowired
   private AuditService auditService;

   /**
	* Джоб очистки логов аудита. Запускается раз в день и удаляет все записи,
	* срок хранения которых истек (определяется настройкой CLEAN_TIME_LOGS).
	*/
   @Scheduled(cron = "0 0 1 * * ?")
   public void runRemoveOldValues() {
	  auditService.removeOldValues();
   }
}
