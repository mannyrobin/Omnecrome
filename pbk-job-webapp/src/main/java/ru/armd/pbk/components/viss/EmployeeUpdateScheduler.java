package ru.armd.pbk.components.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.domain.nsi.employee.EmployeeLastUpdate;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeLastUpdateRepository;
import ru.armd.pbk.repositories.viss.VisExchangeRepository;
import ru.armd.pbk.services.plans.employees.PlanEmployeeService;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Джоб актуализации сотрудников.
 */
@Component
public class EmployeeUpdateScheduler {

   @Autowired
   private EmployeeLastUpdateRepository repository;

   @Autowired
   private SettingsRepository settingsRepository;

   @Autowired
   private PlanEmployeeService planEmployeeService;

   @Autowired
   private VisExchangeRepository visExchangeRepository;

    @Scheduled(cron = "0 0 5 * * ?")
    public void doEmployeeUpdate() {
        Setting setting = settingsRepository.getById(Settings.EMPLOYEE_MAX_NOT_FHD_PERIOD.getId());
        Date fireDate = DateUtils.addDays(new Date(), -Integer.valueOf(setting.getValue()));
        List<Long> fireEmployees = new ArrayList<Long>();
        for (EmployeeLastUpdate lastUpdate : repository.getDomains(null)) {
            if (lastUpdate.getLastDepartmentId() != null && !lastUpdate.getCurrDepartmentId().equals(lastUpdate.getLastDepartmentId())) {
                //Различны индефикаторы у подразделений, значит надо перевести сотрудника
                planEmployeeService.moveEmployee(lastUpdate.getEmployeeId(), lastUpdate.getLastDepartmentId(),
                        lastUpdate.getCurrDepartmentId(), lastUpdate.getLastUpdate());
                lastUpdate.setIsScheduled(true);
                repository.save(lastUpdate);
            } else {
                if (fireDate.after(lastUpdate.getLastUpdate())) {
                    if (isFhdWorks()) {
                        //Дата увольнения больше последнего прихода сотрудника, значит его надо уволить
                        planEmployeeService.fireEmployee(lastUpdate.getEmployeeId(), lastUpdate.getCurrDepartmentId(), lastUpdate.getLastUpdate());
                        fireEmployees.add(lastUpdate.getId());
                    }
                } else if (lastUpdate.getIsFire() && lastUpdate.getLastUpdate().before(lastUpdate.getFireDate())) {
                    //Сотрудник уволен, и дата обновления раньше даты увольнения, значит его надо востановить
                    planEmployeeService.unfireEmployee(lastUpdate.getEmployeeId(), lastUpdate.getCurrDepartmentId());
                }
            }
        }
        if (fireEmployees.size() > 0) {
            repository.delete(fireEmployees);
        }
    }

   private boolean isFhdWorks() {
       List<Integer> configIds = new ArrayList<>();
       configIds.add(2);
       configIds.add(3);
       List<Integer> valids = visExchangeRepository.isValidDay(new Date(), configIds);
       return valids != null && valids.size() == 1 && valids.get(0) > 1;
   }

}
