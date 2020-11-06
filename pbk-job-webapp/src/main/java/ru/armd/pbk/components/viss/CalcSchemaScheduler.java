package ru.armd.pbk.components.viss;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.jobs.CalcSchemaJob;

import java.util.Calendar;

/**
 * Шкедулер, который создает схему для временных данных,
 * необходимых для ПП. Схема создается на месяц вперед,
 * при запуске сервера, схема создается на текущий день.
 */
@Component
public class CalcSchemaScheduler
	implements InitializingBean {

   @Autowired
   private CalcSchemaJob job;

   @Override
   public void afterPropertiesSet()
	   throws Exception {
	  job.createSchema(Calendar.getInstance().getTime());
   }

   @Scheduled(cron = "0 0 1 * * ?")
   public void schedule() {
	  Calendar start = Calendar.getInstance();
	  Calendar end = Calendar.getInstance();
	  end.setTime(start.getTime());
	  end.add(Calendar.MONTH, 1);
	  while (!start.getTime().after(end.getTime())) {
		 job.createSchema(start.getTime());
		 start.add(Calendar.DAY_OF_YEAR, 1);
	  }
   }

}
