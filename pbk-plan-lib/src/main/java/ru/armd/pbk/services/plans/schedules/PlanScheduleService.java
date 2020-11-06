package ru.armd.pbk.services.plans.schedules;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.dto.plans.schedules.ScheduleShiftDTO;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.enums.nsi.ShiftModes;
import ru.armd.pbk.matcher.plans.PlanScheduleMatcher;
import ru.armd.pbk.repositories.plans.schedules.PlanScheduleRepository;
import ru.armd.pbk.services.core.SettingsService;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.plans.schedules.SchedulesTableView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Сервисов расписания планов отдела.
 */
@Service
public class PlanScheduleService
	extends BaseDomainService<ScheduleShift, ScheduleShiftDTO> {

   private static final Logger LOGGER = Logger.getLogger(PlanScheduleService.class);

   @Autowired
   private PlansService planService;

   @Autowired
   private SettingsService settingsService;

   private PlanScheduleRepository repository;

   @Autowired
   PlanScheduleService(PlanScheduleRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public ScheduleShift toDomain(ScheduleShiftDTO dto) {
	  return PlanScheduleMatcher.INSTANCE.toDomainSchedule(dto);
   }

   @Override
   public ScheduleShiftDTO toDTO(ScheduleShift domain) {
	  ScheduleShiftDTO dto = PlanScheduleMatcher.INSTANCE.toDTO(domain);
	  dto.setSettingShiftDay(Integer.valueOf(settingsService.getDTOById(Settings.PLAN_PERIOD.getId()).getValue()));
	  return dto;
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  SchedulesTableView planScheduleView = PlanScheduleMatcher.INSTANCE
		  .getPlanScheduleView(repository.getPlanSchedules(params));
	  return createJsonGridData(planScheduleView, params.getPage(), params.getCount());
   }

   /**
	* Получает список элементов для отображения в выпадающем списке выходных.
	*
	* @return список элементов
	*/
   public List<SelectItem> getHolidayShifts() {
	  return ShiftModes.getDaySelectList();
   }

   /**
	* Получить смену расписания по параметрам.
	*
	* @param params параметры
	* @return смену расписания по параметрам
	*/
   public ScheduleShiftDTO getSchedule(BaseSelectListParams params) {
	  return PlanScheduleMatcher.INSTANCE.toDTO(repository.getSchedule(params));
   }

   /**
	* Получить смену расписания по id задания.
	*
	* @param taskId id задания
	* @return смену расписания по параметрам
	*/
   public ScheduleShiftDTO getScheduleByTaskId(Long taskId) {
	  ScheduleShift scheduleShift = repository.getScheduleByTaskId(taskId);
	  if (scheduleShift.getShiftId().equals(Shift.SICK.getId()) || scheduleShift.getShiftId().equals(Shift.VACATION.getId())
			  || scheduleShift.getShiftId().equals(Shift.OTHER.getId()) || scheduleShift.getShiftId().equals(Shift.LINE1.getId()) || scheduleShift.getShiftId().equals(Shift.LINE2.getId())) {
		 ScheduleShift changeScheduleShift = repository.getChangeScheduleByTaskId(taskId);
		 if (changeScheduleShift != null) {
			return PlanScheduleMatcher.INSTANCE.toDTO(changeScheduleShift);
		 }
	  }
	  return PlanScheduleMatcher.INSTANCE.toDTO(scheduleShift);
   }

   @Override
   public ScheduleShiftDTO saveDTO(ScheduleShiftDTO dto) {
	   if (dto.getShiftId().equals(Shift.III.getId())) {
	   		dto.setShiftModeId(ShiftModes.H_6_7.getId());
	   }
	   Boolean isForCurrentDay = dto.getIsForCurrentDay();
	  boolean isPeriodShift = dto.getShiftId().equals(Shift.SICK.getId()) || dto.getShiftId().equals(Shift.VACATION.getId()) || dto.getShiftId().equals(Shift.OTHER.getId()) || dto.getShiftId().equals(Shift.LINE1.getId()) || dto.getShiftId().equals(Shift.LINE2.getId());
	  if ((isForCurrentDay != null && isForCurrentDay && !isPeriodShift) /*применить только к текущему дню*/
		  || (isPeriodShift && (dto.getDateFrom() == null || dto.getDateTo() == null)))/*для выходного/отпуска с незаполненными датами*/ {
		 if (!(isPeriodShift && dto.getDateFrom() != null)) {
			dto.setDateFrom(dto.getPlanDate());
		 }
		 if (!(isPeriodShift && dto.getDateTo() != null)) {
			dto.setDateTo(dto.getPlanDate());
		 }
	  }
	  Date from = dto.getDateFrom(), to = dto.getDateTo();
	  ScheduleShift ps = repository.getPlanScheduleCell(dto.getEmployeeId(), from != null ? from : dto.getPlanDate());
	  Long prevShiftId = ps == null ? null : ps.getShiftId();
	  if (from != null && to != null) {
		 if (isForCurrentDay != null && !isForCurrentDay && !isPeriodShift) {
			ps = repository.getPlanScheduleCell(dto.getEmployeeId(), from);
			dto.setId(ps == null ? null : ps.getId());
			if (ps != null && dto.getShiftModeId() == null) {
			   dto.setShiftModeId(ps.getShiftModeId());
			}
			super.saveDTO(dto);
			dto.setShiftId(ps == null ? null : ps.getShiftId());
			planService.generateControllerGraph(dto);
		 } else {
			Calendar c = Calendar.getInstance();
			c.setTime(from);
			do {
			   dto.setPlanDate(c.getTime());
			   ps = repository.getPlanScheduleCell(dto.getEmployeeId(), c.getTime());
			   dto.setId(ps == null ? null : ps.getId());
			   if (ps != null && dto.getShiftModeId() == null) {
				  dto.setShiftModeId(ps.getShiftModeId());
			   }
			   super.saveDTO(dto);
			   if (!isPeriodShift && !(isForCurrentDay != null && isForCurrentDay && dto.getShiftId().equals(Shift.HOLIDAY.getId()))) {
				  planService.generateControllerGraph(dto, prevShiftId);
			   } else {
				  planService.formBrigades(dto.getDeptId(), c.getTime(), false, false);
			   }
			   c.add(Calendar.DAY_OF_YEAR, 1);
			} while (!c.getTime().after(to));
		 }
	  } else {
		 super.saveDTO(dto);
		 planService.generateControllerGraph(dto, prevShiftId);
	  }
	  return dto;
   }

   /**
	* Получение списка расписания для отображения в комбобоксах при создании
	* заданий.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	*/
   public List<SelectItem> getSelectItemsForCreatingTask(BaseSelectListParams params) {
	  List<SelectItem> selectItemList = repository.getSelectItemsForCreatingTask(params);
	  return selectItemList;
   }

   /**
	* Получение списка сотрудников для которых не существует расписания
	* в заданном временом промежутке.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	*/
   public List<SelectItem> getEmployeesWithoutSchedule(BaseSelectListParams params) {
	  List<SelectItem> selectItemList = repository.getEmployeesWithoutSchedule(params);
	  return selectItemList;
   }

   @Transactional
   public void checkShiftModes() {
		List<ScheduleShift> shifts = repository.getScheduleForCheckingMode();
		String shiftDesc;
		for (ScheduleShift shift : shifts) {
			shiftDesc = shift.getShiftDescription();
			//Проверяем на корректность мод с выходными ПТ + СБ
			if (shiftDesc.contains("6,7,6") && (shift.getShiftModeId().equals(ShiftModes.H_6_7.getId()) || shift.getShiftModeId().equals(ShiftModes.H_7_1.getId()))) {
				for (ScheduleShift currentShift : repository.getPlanScheduleCells(shift.getEmployeeId(), new Date())) {
					currentShift.setShiftModeId(ShiftModes.H_5_6.getId());
					repository.save(currentShift);
				}
			}
			//Проверяем на корректность мод с выходными СБ + ВС
			if (shiftDesc.contains("7,1,7") && (shift.getShiftModeId().equals(ShiftModes.H_7_1.getId()) || shift.getShiftModeId().equals(ShiftModes.H_5_6.getId()))) {
				for (ScheduleShift currentShift : repository.getPlanScheduleCells(shift.getEmployeeId(), new Date())) {
					currentShift.setShiftModeId(ShiftModes.H_6_7.getId());
					repository.save(currentShift);
				}
			}
			//Проверяем на корректность мод с выходными ВС + ПН
			if (shiftDesc.contains("1,2,1") && (shift.getShiftModeId().equals(ShiftModes.H_5_6.getId()) || shift.getShiftModeId().equals(ShiftModes.H_6_7.getId()))) {
				for (ScheduleShift currentShift : repository.getPlanScheduleCells(shift.getEmployeeId(), new Date())) {
					currentShift.setShiftModeId(ShiftModes.H_7_1.getId());
					repository.save(currentShift);
				}
			}
		}
   }
}