package ru.armd.pbk.components.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.services.authtoken.TokenSessionService;

/**
 * Класс джоба очистки сессий.
 */
@Component
public class SessionScheduler {

   @Autowired
   private TokenSessionService service;

   /**
	* Джоб очистки протухших сессий.
	*/
   @Scheduled(cron = "0 5 * * * ?")
   public void runRemoveOldValues() {
	  service.cleanOldSessions();
   }

}
