package ru.armd.pbk.components.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.jobs.PassengersJob;
import ru.armd.pbk.components.viss.jobs.StopIntervalsJob;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;

/**
 * Класс подсчета межостановочных интервалов.
 */
@Component
public class StopIntervalsScheduler
	implements IHasLogger {

   public static final Logger LOGGER = Logger.getLogger(StopIntervalsScheduler.class);

   @Autowired
   StopIntervalsJob stopIntervals;

   @Autowired
   PassengersJob passengers;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Scheduled(cron = "0 0 4 * * ?")
   //@Scheduled(fixedDelay = 1 * 60 * 60)
   public void doCalculateIntervals() {
	  stopIntervals.calculate(DateUtils.addDays(new Date(), -2), null);
   }

   @Scheduled(cron = "0 0 1 * * ?")
   //@Scheduled(fixedDelay = 1 * 60 * 60)
   public void doCalculatePassengers() {
	  passengers.calculate(DateUtils.addDays(new Date(), -2), null);
   }
}
