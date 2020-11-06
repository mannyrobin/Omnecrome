package ru.armd.pbk.components.plans;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.services.nsi.RouteService;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.services.plans.schedules.PlanScheduleService;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс отвечающий за запуск запланированных задач (по подсистеме
 * планирования), которые запускаются по расписанию.
 */
@Component
public class PlansScheduler
	implements IHasLogger {

   public static final Logger LOGGER = Logger.getLogger(PlansScheduler.class);

   @Autowired
   private PlansService planService;

   @Autowired
   private RouteService routeService;

   @Autowired
   private PlanScheduleService planScheduleService;

   @Override
	public Logger getLogger() {
		return LOGGER;
	}

   /**
	* Вызывает пересчет рейтингов маршрутов и привязку маршрутов к заданию.
	*/
   @Scheduled(cron = "0 0 18 * * ?")
   public void doGkuopEmployeeImport() {
	  Calendar cTo = Calendar.getInstance();
	  cTo.set(Calendar.HOUR_OF_DAY, 0);
	  cTo.set(Calendar.MINUTE, 0);
	  cTo.set(Calendar.SECOND, 0);
	  Calendar c = Calendar.getInstance();
	  c.setTime(cTo.getTime());
	  c.add(Calendar.DAY_OF_YEAR, -14);
	  Map<Long, Date> changes = new HashMap<Long, Date>();
	  do {
		 planService.makeRouteRating(c.getTime(), changes);
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  } while (c.getTime().before(cTo.getTime()));

	  planService.distributedRoutesToTasks(changes, null);

   }

   /**
	* Вызывает пересчет количество выходов маршрута.
	*/
   @Scheduled(cron = "0 0 9 * * ?")
   public void doRouteCountMoveUpdate() {
	  Calendar cTo = Calendar.getInstance();
	  cTo.set(Calendar.HOUR_OF_DAY, 0);
	  cTo.set(Calendar.MINUTE, 0);
	  cTo.set(Calendar.SECOND, 0);
	  routeService.updateRouteMoveCount(cTo.getTime());
   }

    //it works with some errors from 2019. need to check fhd_work_mode instead of this job.
	//@Scheduled(cron = "0 30 6 * * ?")
	public void doCheckShiftModes() {
		planScheduleService.checkShiftModes();
	}
}