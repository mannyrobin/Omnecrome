package ru.armd.pbk.components.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.VisScheduler;
import ru.armd.pbk.core.IHasLogger;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.services.tasks.TasksService;

/**
 * Created by Kirill Bakhaev on 06.09.2017.
 */

@Component
public class TasksScheduler implements IHasLogger {
    public static final Logger LOGGER = Logger.getLogger(TasksScheduler.class);

    @Autowired
    private TasksService tasksService;

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Если сотрудник является контролером МОК и у него есть дата увольнения - все задания, которые “дата задания >= дата увольнения”, переводим в статус “Отменено”
     */
    @Scheduled(cron = "0 30 5 * * ?")
    public void cancelTasksOfFiredEmployees() {
        getLogger().info("CancelTasksOfFiredEmployees job was started");
        tasksService.cancelTasksOfFiredEmployees();
        getLogger().info("CancelTasksOfFiredEmployees job was completed");
    }

    /**
     * Проверяем, историю сотрудника. Если он является контролером МОК и у него изменился МОК - все задания, которые “дата задания >= дата смены МОК у контролера”, переводим в статус “Отменено”.
     */
    @Scheduled(cron = "0 0 5 * * ?")
    public void cancelTasksOfMovedDepartmentEmployees() {
        getLogger().info("cancelTasksOfMovedDepartmentEmployees job was started");
        tasksService.cancelTasksOfMovedDepEmployees();
        getLogger().info("cancelTasksOfMovedDepartmentEmployees job was completed");
    }

    /**
     * Случай, когда сотрудник не увольнялся, не переходил в другое подразделение, а просто у него изменился статус с “участвует в планировании” на “не участвует в планировании”.
     */
    @Scheduled(cron = "0 0 6 * * ?")
    public void cancelTasksOfChangedPlanStatusEmployees() {
        getLogger().info("cancelTasksOfChangedPlanStatusEmployees job was started");
        tasksService.cancelTasksOfChangedPlanEmployees();
        getLogger().info("cancelTasksOfChangedPlanStatusEmployees job was completed");

    }

}
