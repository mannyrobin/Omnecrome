package ru.armd.pbk.executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.IHasLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Содержит методы для работы с executorService.
 * Например, осуществляет мягкую остановку executor,
 * когда сервер завершает свою работу.
 */
@Component
public class ExecutorManager
	implements IHasLogger, ApplicationListener<ContextClosedEvent> {

   public static final Logger LOGGER = Logger.getLogger(ExecutorManager.class);

   @Autowired
   private ThreadPoolTaskScheduler defaultScheduler;

   @Autowired
   private ThreadPoolTaskExecutor defaultExecutor;

   @Autowired
   private ThreadPoolTaskExecutor asduExecutor;

   @Autowired
   private ThreadPoolTaskExecutor gismgtExecutor;

   @Autowired
   private ThreadPoolTaskExecutor dvrExecutor;

   @Autowired
   private ThreadPoolTaskExecutor gkuopExecutor;

   @Autowired
   private ThreadPoolTaskExecutor auditExecutor;

   @Autowired
   private ThreadPoolTaskExecutor easufhdExecutor;

   @Autowired
   private ThreadPoolTaskExecutor gtfsExecutor;

   @Autowired
   private ThreadPoolTaskExecutor asmppExecutor;


   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public void onApplicationEvent(ContextClosedEvent event) {
	  getLogger().info(this.getClass().getCanonicalName() + " ContextClosedEvent");

	  shutdownExecutorService(auditExecutor.getThreadPoolExecutor());
	  shutdownExecutorService(defaultScheduler.getScheduledExecutor());
	  shutdownExecutorService(defaultExecutor.getThreadPoolExecutor());

	  shutdownExecutorService(asduExecutor.getThreadPoolExecutor());
	  shutdownExecutorService(gismgtExecutor.getThreadPoolExecutor());
	  shutdownExecutorService(easufhdExecutor.getThreadPoolExecutor());

	  shutdownExecutorService(gtfsExecutor.getThreadPoolExecutor());
	  shutdownExecutorService(asmppExecutor.getThreadPoolExecutor());
	  shutdownExecutorService(gkuopExecutor.getThreadPoolExecutor());
	  shutdownExecutorService(dvrExecutor.getThreadPoolExecutor());
   }

   /**
	* "Мягко" останавливает работу executorService.
	*
	* @param executorService executorService.
	*/
   protected void shutdownExecutorService(ExecutorService executorService) {
	  executorService.shutdown();
	  try {
		 if (!executorService.awaitTermination(1L, TimeUnit.SECONDS)) {
			executorService.shutdownNow();
		 }
	  } catch (InterruptedException e) {
		 executorService.shutdownNow();
	  }
   }
}
