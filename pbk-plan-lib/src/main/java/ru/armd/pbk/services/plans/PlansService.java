package ru.armd.pbk.services.plans;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.domain.nsi.Shift.CODE;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.domain.plans.routes.ASKPTicketCkecks;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.domain.plans.schedules.PlanSchedule;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.domain.plans.schedules.ScheduleWorkMode;
import ru.armd.pbk.domain.tasks.CheckRoute;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.dto.plans.schedules.ScheduleShiftDTO;
import ru.armd.pbk.enums.core.TaskStates;
import ru.armd.pbk.enums.nsi.BsoType;
import ru.armd.pbk.enums.nsi.EmployeeAbsenceTypes;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.enums.nsi.ShiftModes;
import ru.armd.pbk.enums.nsi.TicketType;
import ru.armd.pbk.enums.nsi.WorkDayType;
import ru.armd.pbk.enums.nsi.WorkModeType;
import ru.armd.pbk.enums.tasks.TaskType;
import ru.armd.pbk.mappers.core.SettingsMapper;
import ru.armd.pbk.mappers.nsi.RouteMapper;
import ru.armd.pbk.mappers.nsi.ShiftMapper;
import ru.armd.pbk.mappers.nsi.bso.BsoMapper;
import ru.armd.pbk.mappers.nsi.calendar.CalendarMapper;
import ru.armd.pbk.mappers.nsi.venue.VenueMapper;
import ru.armd.pbk.mappers.plans.brigades.PlanBrigadeMapper;
import ru.armd.pbk.mappers.plans.routes.PlanRouteMapper;
import ru.armd.pbk.mappers.plans.schedules.PlanScheduleMapper;
import ru.armd.pbk.mappers.tasks.TaskMapper;
import ru.armd.pbk.repositories.nsi.bso.BsoRepository;
import ru.armd.pbk.repositories.nsi.department.DepartmentRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;
import ru.armd.pbk.repositories.plans.PlanRepository;
import ru.armd.pbk.repositories.plans.schedules.PlanScheduleRepository;
import ru.armd.pbk.repositories.tasks.TaskReportRepository;
import ru.armd.pbk.repositories.tasks.TaskRepository;
import ru.armd.pbk.repositories.tasks.TaskRouteRepository;
import ru.armd.pbk.services.nsi.bso.BsoService;
import ru.armd.pbk.services.tasks.AskpBindService;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Сервисов планов отдела.
 */
@Service
public class PlansService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(PlansService.class);

   private int MAX_CONTROLLER_LOAD = 25;

   @Autowired
   private DepartmentRepository departmentRepository;

   @Autowired
   private PlanScheduleMapper planScheduleMapper;

   @Autowired
   private PlanScheduleRepository planScheduleRepository;

   @Autowired
   private ShiftMapper shiftMapper;

   @Autowired
   private CalendarMapper calendarMapper;

   @Autowired
   private RouteMapper routeMapper;

   @Autowired
   private SettingsMapper settingsMapper;

   @Autowired
   private PlanRouteMapper planRouteMapper;

   @Autowired
   private PlanBrigadeMapper planBrigadeMapper;

   @Autowired
   private TaskMapper taskMapper;

   @Autowired
   private TaskRepository taskRepository;

   @Autowired
   private TaskRouteRepository taskRouteRepository;

   @Autowired
   private VenueMapper venueMapper;

   @Autowired
   private BsoService bsoService;

   @Autowired
   private TaskReportRepository taskReportRepository;

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   private BsoMapper bsoMapper;

   @Autowired
   private BsoRepository bsoRepository;

   @Autowired
   private AskpBindService askpBindService;

   @Autowired
   PlansService(PlanRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Формирование графика контроллера.
	*
	* @param sch         дто расписания сотрудника.
	* @param prevShiftId предыдущая смена сотрудника.
	*/
   public void generateControllerGraph(ScheduleShiftDTO sch, Long prevShiftId) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(sch.getPlanDate());
	  c.add(Calendar.DAY_OF_YEAR, 1);
	  List<ScheduleShift> prevSchedules = planScheduleMapper.getPreviousPlanSchedulesForDepts(c.getTime(), sch.getDeptId(),
		  sch.getEmployeeId());
	  generateControllerGraph(prevSchedules,
		  new Employee(sch.getEmployeeId(), sch.getDeptId()), sch.getPlanDate(),
		  (sch.getIsForCurrentDay() == null || !sch.getIsForCurrentDay())
			  && (Shift.getEnumById(sch.getShiftId()).isWorkDay()
			  || sch.getShiftId().equals(Shift.HOLIDAY.getId())) ? null : sch.getPlanDate(), prevShiftId);
   }

   /**
	* Формирование графика контроллера.
	*
	* @param sch дто расписания сотрудника.
	*/
   public void generateControllerGraph(ScheduleShiftDTO sch) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(sch.getDateFrom() == null ? sch.getPlanDate() : sch.getDateFrom());
	  c.add(Calendar.DAY_OF_YEAR, 1);
	  List<ScheduleShift> prevSchedules = planScheduleMapper.getPreviousPlanSchedulesForDepts(c.getTime(), sch.getDeptId(),
		  sch.getEmployeeId());
	  generateControllerGraph(prevSchedules,
		  new Employee(sch.getEmployeeId(), sch.getDeptId()),
		  sch.getDateFrom(), sch.getDateTo(), sch.getShiftId());
   }

   /**
	* Формирование графика контроллера.
	*
	* @param prevSchedules предыдущие смены.
	* @param employee      сотрудник.
	* @param startDate     дата начало формирования.
	* @param finishDate    дата окончания формирования.
	* @param prevShiftId   предыдущая смена.
	*/
   private void generateControllerGraph(List<ScheduleShift> prevSchedules, Employee employee, Date startDate,
										Date finishDate, Long prevShiftId) {
	  List<Long> updates = new ArrayList<Long>();

	  if (finishDate == null) {
		 int planPeriod = 60;
		 Setting period = settingsMapper.getByCode("PLAN_PERIOD");
		 if (period != null) {
			planPeriod = Integer.parseInt(period.getValue());
		 }
		 Calendar c = Calendar.getInstance();
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		 c.add(Calendar.DAY_OF_YEAR, planPeriod);
		 finishDate = c.getTime();
	  }

	  Map<Long, ScheduleShift> prevShifts = new HashMap<Long, ScheduleShift>();
	  if (prevSchedules != null) {
		 for (ScheduleShift ps : prevSchedules) {
			prevShifts.put(ps.getEmployeeId(), ps);
		 }
	  }
	  Map<CODE, Long> shifts = new HashMap<>();
	  for (CODE code : CODE.values()) {
		 shifts.put(code, shiftMapper.getByCode(code.name()).getId());
	  }
	  Long employeeId = employee.getId();
	  ScheduleShift prevPs = prevShifts.get(employeeId);
	  Calendar c = Calendar.getInstance();
	  Long shiftId = null;
	  CODE prevShift = null;
	  ShiftModes prevShiftMode = ShiftModes.H_6_7;

	  boolean isVacationPeriod = prevShiftId != null && (Shift.getEnumById(prevShiftId) == Shift.VACATION || Shift.getEnumById(prevShiftId) == Shift.SICK
		  || Shift.getEnumById(prevShiftId) == Shift.OTHER || Shift.getEnumById(prevShiftId) == Shift.LINE1 || Shift.getEnumById(prevShiftId) == Shift.LINE2);

	  if (prevPs != null) {
		 c.setTime(prevPs.getPlanDate());
		 prevShift = CODE.valueOf(shiftMapper.getById(prevPs.getShiftId()).getCode());
		 prevShiftMode = prevPs.getShiftModeId() == null ? (prevShift == CODE.DAY || prevShift == CODE.NIGHT ? ShiftModes.H_2_2 : ShiftModes.H_6_7)
			 : ShiftModes.getEnumById((long) prevPs.getShiftModeId());
		 shiftId = shifts.get(prevShift);

		 if (prevShift != CODE.valueOf(Shift.HOLIDAY.name())
			 && isHoliday(c.get(Calendar.DAY_OF_WEEK), prevShiftMode)) {
			prevPs.setShiftId(Shift.HOLIDAY.getId());
			savePlanSchedule(prevPs);
		 }
		 updates.add(c.getTime().getTime());
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  } else {
		 c.setTime(startDate);
		 c.add(Calendar.DAY_OF_YEAR, -1);
		 switch (c.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
			case Calendar.SATURDAY:
			   prevShift = CODE.II;
			   break;
			default:
			   prevShift = CODE.I;
		 }
		 prevShiftMode = ShiftModes.H_6_7;
		 c.setTime(startDate);
	  }
	  int holidayIdx = 0;
	  while (!c.getTime().after(finishDate)) {
		 int wd = c.get(Calendar.DAY_OF_WEEK);
		 if (prevShift == CODE.I || prevShift == CODE.II) {
			if (isHoliday(wd, prevShiftMode)) {
			   shiftId = shifts.get(CODE.HOLIDAY);
			} else if ((wd == Calendar.MONDAY && prevShiftMode == ShiftModes.H_6_7)
				|| (wd == Calendar.SUNDAY && prevShiftMode == ShiftModes.H_5_6)
				|| (wd == Calendar.TUESDAY && prevShiftMode == ShiftModes.H_7_1)) {
			   prevShift = prevShift == CODE.I ? CODE.II : CODE.I;
			   shiftId = shifts.get(prevShift);
			}
		 } else if (prevShift == CODE.III) {
			 if (isHoliday(wd, prevShiftMode)) {
				 shiftId = shifts.get(CODE.HOLIDAY);
			 } else if ((wd == Calendar.MONDAY && prevShiftMode == ShiftModes.H_6_7)
					 || (wd == Calendar.SUNDAY && prevShiftMode == ShiftModes.H_5_6)
					 || (wd == Calendar.TUESDAY && prevShiftMode == ShiftModes.H_7_1)) {
				 shiftId = shifts.get(prevShift);
			 }
		 } else	{
				if (prevShift == CODE.DAY) {
				   prevShift = CODE.NIGHT;
				   shiftId = shifts.get(prevShift);
				} else if (prevShift == CODE.NIGHT) {
				   if (holidayIdx++ < 2) {
					  shiftId = shifts.get(CODE.HOLIDAY);
				   } else {
					  holidayIdx = 0;
					  prevShift = CODE.DAY;
					  shiftId = shifts.get(prevShift);
				   }
				}
				prevShiftMode = ShiftModes.H_2_2;
		 }
		 if (!c.getTime().before(startDate)) {
			ScheduleShift psSave = getScheduleShift(employeeId, c.getTime());
			Shift psSaveShift = psSave == null ? null : Shift.getEnumById(psSave.getShiftId());
			// if (psSaveShift != Shift.SICK && psSaveShift !=
			// Shift.VACATION) {
			Shift shf = Shift.getEnumById(shiftId);
			Boolean isHoliday = false;
			if (shf == Shift.I || shf == Shift.II || shf == Shift.III || prevShiftMode == ShiftModes.H_5_6 || prevShiftMode == ShiftModes.H_6_7 || prevShiftMode == ShiftModes.H_7_1) {
			   ru.armd.pbk.domain.nsi.Calendar workDay = calendarMapper.getCalendarByDay(c.getTime());
			   if (workDay != null && workDay.getWorkDayTypeId().equals(WorkDayType.HOLIDAY.getId())) { // Праздник
				  shiftId = Shift.HOLIDAY.getId();
				  isHoliday = true;
			   }
			}
			if ((psSave == null || !psSave.getShiftId().equals(shiftId) || c.getTime().equals(startDate))
				&& !updates.contains(c.getTime().getTime())) {
			   updates.add(c.getTime().getTime());
			}
			isVacationPeriod = isVacationPeriod && (psSaveShift == Shift.VACATION || psSaveShift == Shift.SICK || psSaveShift == Shift.OTHER || psSaveShift == Shift.LINE1 || psSaveShift == Shift.LINE2);
			if (psSave == null
				|| ((!psSave.getShiftId().equals(shiftId) || psSave.getShiftModeId() == null || !psSave.getShiftModeId().equals(prevShiftMode.getId()))
				&& (psSaveShift.isWorkDay() || psSaveShift == Shift.HOLIDAY || isVacationPeriod))) {
			   ScheduleShift ps = new ScheduleShift();
			   ps.setId(psSave == null ? null : psSave.getId());
			   ps.setPlanDate(c.getTime());
			   ps.setShiftId(shiftId);
			   ps.setUpdateUserId(AuthenticationManager.getUserInfo().getUserId());
			   ps.setEmployeeId(employeeId);
			   ps.setDeptId(employee.getDepartmentId());
			   ps.setShiftModeId(prevShiftMode.getId());
			   savePlanSchedule(ps);
			}
			// }
			//Наткнулись на праздник, нужном вернуть смену обратно.
			if (isHoliday) {
			   shiftId = shifts.get(prevShift);
			}
		 }
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  }

	  for (Long dt : updates) {
		 formBrigades(employee.getDepartmentId(), new Date(dt), false, false);
	  }
   }

   private ScheduleShift getScheduleShift(Long employeeId, Date workDate) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("employeeId", employeeId);
	  params.put("workDate", workDate);
	  return planScheduleMapper.getSchedule(params);
   }

   private boolean isHoliday(int wd, ShiftModes mode) {
	  return (mode == ShiftModes.H_6_7 && (wd == Calendar.SUNDAY || wd == Calendar.SATURDAY))
		  || (mode == ShiftModes.H_5_6 && (wd == Calendar.FRIDAY || wd == Calendar.SATURDAY))
		  || (mode == ShiftModes.H_7_1 && (wd == Calendar.SUNDAY || wd == Calendar.MONDAY));
   }

   /**
	* Определение рейтинга маршрутов за период.
	*
	* @param startDate начальная дата
	* @param endDate   конечная дата
	* @param changes   изменения
	*/
   public void makeRouteRating(Date startDate, Date endDate, Map<Long, Date> changes) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(startDate);
	  c = trimCalendar(c);
	  Calendar c2 = Calendar.getInstance();
	  c2.setTime(endDate);
	  c2 = trimCalendar(c2);
	  c.add(Calendar.DAY_OF_YEAR, -14);
	  c2.add(Calendar.DAY_OF_YEAR, -14);
	  while (!c.getTime().after(c2.getTime())) {
		 makeRouteRating(c.getTime(), changes);
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  }
   }

   /**
	* Определение рейтинга маршрутов на определенную дату.
	*
	* @param workDate рабочая дата
	* @param changes  изменения
	*/
   public void makeRouteRating(Date workDate, Map<Long, Date> changes) {
	  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	  ru.armd.pbk.domain.nsi.Calendar wd = calendarMapper.getCalendarByDay(workDate);
	  Date weekDate = getSameWeekDay(wd);
	  Date yearDate = getSameYearDay(wd);
	  Date[] dates = new Date[] {workDate, weekDate, yearDate};

	  Setting setting = settingsMapper.getByCode("ROUTE_PROFIT_RATIO");
	  double mkr = setting == null || setting.getValue().isEmpty() ? 0 : Double.valueOf(setting.getValue());
	  List<ASKPTicketCkecks> tickets = planRouteMapper
		  .getPassengersCount(null/* route.getHeadId() */, dates);
	  List<RouteRatio> routeRatios = planRouteMapper.getRouteRatiosByDate(workDate);
	  for (RouteRatio rr : routeRatios) {
		 if (rr.isManual()) {
			continue;
		 }
		 int rating = 0;

		 double kr = rr.getProfitRatio() == null ? 0 : rr.getProfitRatio();
		 if (kr > mkr) {
			rating++;
		 }
		 long n = 0;
		 long s = 0;
		 long nw = 0;
		 long sw = 0;
		 long ny = 0;
		 long sy = 0;
		 int rtNw = 0;
		 int rtNy = 0;
		 int rtSw = 0;
		 int rtSy = 0;
		 for (ASKPTicketCkecks t : tickets) {
			if (t.getRouteId().equals(rr.getRouteId())) {
			   if (t.getWorkDate().equals(workDate)) {
				  if (t.getTicketId().equals(TicketType.PAID.getId())) {
					 n = t.getTicketCount();
				  } else {
					 s = t.getTicketCount();
				  }
			   } else if (t.getWorkDate().equals(weekDate)) {
				  if (t.getTicketId().equals(TicketType.PAID.getId())) {
					 nw = t.getTicketCount();
				  } else {
					 sw = t.getTicketCount();
				  }
			   } else if (t.getWorkDate().equals(yearDate)) {
				  if (t.getTicketId().equals(TicketType.PAID.getId())) {
					 ny = t.getTicketCount();
				  } else {
					 sy = t.getTicketCount();
				  }
			   }
			}
		 }
		 if (n < nw * 0.95) {
			rtNw = 1;
		 }
		 if (n < ny * 0.95) {
			rtNy = 1;
		 }
		 if (s > sw * 1.05) {
			rtSw = 1;
		 }
		 if (s > sy * 1.05) {
			rtSy = 1;
		 }
		 rating = rtNw + rtNy + rtSw + rtSy;
		 String descr = String.format("{\"week\":{\"date\":\"%s\", \"common\":\"%d\", \"social\":\"%d\"}, \"year\":{\"date\":\"%s\", \"common\":\"%d\", \"social\":\"%d\"}}",
			 sdf.format(weekDate), rtNw, rtSw, sdf.format(yearDate), rtNy, rtSy);
		 if (rr.getId() == null || rr.getRouteRatio() != rating || rr.getDescr() == null) {
			if (rr.getId() == null) {
			   RouteRatio rri = new RouteRatio();
			   rri.setWorkDate(workDate);
			   initCreaterInfo(rri);
			   initUpdaterInfo(rri);
			   rri.setRouteId(rr.getRouteId());
			   rri.setRouteRatio(rating);
			   rri.setDescr(descr);
			   planRouteMapper.insert(rri);
			} else {
			   initUpdaterInfo(rr);
			   rr.setRouteRatio(rating);
			   rr.setDescr(descr);
			   planRouteMapper.update(rr);
			}
			if (changes != null && !changes.containsKey(rr.getRouteId())) {
			   changes.put(rr.getRouteId(), workDate);
			}
		 }
	  }
   }

   private Date getSameWeekDay(ru.armd.pbk.domain.nsi.Calendar cwd) {
	  ru.armd.pbk.domain.nsi.Calendar swd = null;
	  if (cwd.getWorkDayTypeId().equals(WorkDayType.HOLIDAY.getId())) {
		 swd = calendarMapper.getSameDayByType(true, cwd.getWorkDate(),
			 new int[] {WorkDayType.HOLIDAY.getId(), WorkDayType.DAY_OFF.getId()});
	  } else {
		 Calendar c = Calendar.getInstance();
		 c.setTime(cwd.getWorkDate());
		 do {
			c.add(Calendar.DAY_OF_YEAR, -7);
			swd = calendarMapper.getCalendarByDay(c.getTime());
		 } while (swd != null && !swd.getWorkDayTypeId().equals(swd.getWorkDayTypeId()));
	  }
	  return swd == null ? null : swd.getWorkDate();
   }

   private Date getSameYearDay(ru.armd.pbk.domain.nsi.Calendar cwd) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(cwd.getWorkDate());
	  c.add(Calendar.DAY_OF_YEAR, -364);
	  ru.armd.pbk.domain.nsi.Calendar swd = calendarMapper.getCalendarByDay(c.getTime());
	  if (swd != null && ((cwd.getWorkDayTypeId().equals(WorkDayType.HOLIDAY.getId())
		  && swd.getWorkDayTypeId().equals(WorkDayType.HOLIDAY.getId()))
		  || !cwd.getWorkDayTypeId().equals(WorkDayType.HOLIDAY.getId())
		  && !swd.getWorkDayTypeId().equals(WorkDayType.HOLIDAY.getId()))) {
	  } else {
		 if (!cwd.getWorkDayTypeId().equals(WorkDayType.WORK_DAY.getId())) {
			do {
			   c.add(Calendar.DAY_OF_YEAR, 7);
			   swd = calendarMapper.getCalendarByDay(c.getTime());
			}
			while (((swd != null && !swd.getWorkDayTypeId().equals(WorkDayType.WORK_DAY.getId())) || (swd == null))
				&& c.getTime().before(cwd.getWorkDate()));
		 } else {
			swd = calendarMapper.getSameDayByType(false, c.getTime(), new int[] {WorkDayType.HOLIDAY.getId()});
		 }
	  }
	  return swd == null ? null : swd.getWorkDate();
   }

   /**
	* Удаление заданий бригад на дату.
	*
	* @param deptId   id подразделения
	* @param workDate дата
	*/
   //@Transactional
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void clearBrigadeTasks(Long deptId, Date workDate) {
	  /*taskRouteRepository.removeTasksRoutesByWorkDate(deptId, workDate);
	  taskMapper.removeTasksByWorkDate(deptId, workDate);*/

	  taskRouteRepository.removeTasksRoutesByWorkDate(deptId, workDate);
	  taskRepository.removeTasksByWorkDate(deptId, workDate);
   }

   /**
	* Формирование бригад.
	*
	* @param deptId          id подразделения
	* @param startDate       дата начала
	* @param finishDate      дата окончания
	* @param manual          признак ручного формирования бригад
	* @param resetManualData сбрасывать ли вручную введенные бригады
	*/
   public void formBrigades(Long deptId, Date startDate, Date finishDate, boolean manual, boolean resetManualData) {
	  Calendar c = Calendar.getInstance();
	  if (startDate != null) {
		 c.setTime(startDate);
	  }
	  Calendar cTo = Calendar.getInstance();
	  if (finishDate != null) {
		 cTo.setTime(finishDate);
	  } else {
		 cTo.set(Calendar.DAY_OF_MONTH, 1);
		 cTo.add(Calendar.MONTH, 2);
		 cTo.add(Calendar.DAY_OF_MONTH, -1);
	  }
	  trimCalendar(c);
	  trimCalendar(cTo);
	  while (!c.getTime().after(cTo.getTime())) {
		 formBrigades(deptId, c.getTime(), manual, resetManualData);
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  }
   }

   /**
	* Формирование бригад.
	*
	* @param deptId          ид подразделения.
	* @param workDate        работчая дата.
	* @param manual          признак ручного формирования бригад
	* @param resetManualData сбрасывать ли вручную введенные бригады
	*/

   public void formBrigades(Long deptId, Date workDate, boolean manual, boolean resetManualData) {
	  Calendar c = Calendar.getInstance();
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  c.add(Calendar.MONTH, 2);
	  if (!workDate.before(c.getTime())) {
		 return;
	  }

	  List<Brigade> brigades = planBrigadeMapper.getBrigades(workDate, deptId, null);
	  boolean isAgree = brigades != null && !brigades.isEmpty() && brigades.get(0).getIsAgree();

	  if (isAgree) {
		 for (Brigade br : brigades) {
			distributedControllersForChange(br);
		 }

		 // Создать задания для контроллеров без заданий
		 Map<Long, Task> createdTasks = new HashMap<Long, Task>();
		 List<PlanSchedule> schs = planScheduleMapper.findFreeController(deptId, workDate, null);
		 for (PlanSchedule sch : schs) {
			if (sch.getTaskId() == null) {
			   Shift shift = Shift.getEnumById(sch.getShiftId());
			   if (shift.isWorkDay()) {
				  List<Brigade> incompleteBrigades = planBrigadeMapper.checkBrigadeShiftsForVenues(
					  sch.getDeptId(), sch.getShiftId(), workDate, workDate);
				  boolean bFind = false;
				  if (incompleteBrigades.size() > 0) { // нашли неполную бригаду
					 for (Brigade br : incompleteBrigades) { // ищем бригаду нужного округа
						if (br.getCountyList().contains(String.valueOf(sch.getCountyId()))) {
						   createTask(br, sch, TaskStates.CREATED.getId(), createdTasks);
						   bFind = true;
						   break;
						}
					 }
				  }
				  if (!bFind) {
					 brigades = planBrigadeMapper.getBrigades(workDate, sch.getDeptId(), sch.getShiftId());
					 if (brigades.size() > 0) { // нашли какую-нибудь бригаду с нужной сменой на дату
						List<Brigade> brgsCountyList = new ArrayList<Brigade>();
						for (Brigade br : brigades) { // ищем бригады нужного округа
						   if (br.getCountyList().contains(String.valueOf(sch.getCountyId()))) {
							  brgsCountyList.add(br);
						   }
						}
						if (brgsCountyList.size() > 0) {
						   createTask(brgsCountyList.get((int) Math.floor(Math.random() * brgsCountyList.size())), sch, TaskStates.IN_RESERVE.getId(), createdTasks);
						}
					 }
				  }
			   }
			}
		 }
		 if (createdTasks.size() > 0) {
			createTasks(deptId, createdTasks);
			distributedRoutesToTasks(deptId, workDate);
		 }
		 return;
	  }

	  if (!manual) {
		 return;
	  }

	  clearBrigadeTasks(deptId, workDate);
	  planBrigadeMapper.removeBrigadesByWorkDate(deptId, workDate, resetManualData);

	  List<Brigade> newBrgs = prepareBrigades(deptId, workDate, null);
	  for (Brigade br : newBrgs) {
		 br.setGkuopCount(br.getGkuopCount() == -1 ? 2 * br.getMgtCount() : 0);
		 planBrigadeMapper.insert(br);
	  }
   }

   /**
	* Подготовить бригады.
	*
	* @param deptId    подразделение.
	* @param workDate  дата формирования.
	* @param checkBrgs проверенные бригады.
	* @return
	*/
   public List<Brigade> prepareBrigades(Long deptId, Date workDate, List<Brigade> checkBrgs) {
	  List<Brigade> allBrgs = new ArrayList<Brigade>();
	  List<Brigade> manualBrgs = checkBrgs == null ? planBrigadeMapper.getManualBrigades(deptId, workDate) : new ArrayList<Brigade>();
	  List<Venue> venues = venueMapper.getCityVenueByDept(deptId, workDate);

	  int[] cnt = prepareCnt(venues);

	  ru.armd.pbk.domain.nsi.Shift[] shifts = new ru.armd.pbk.domain.nsi.Shift[] {
		  shiftMapper.getDepartmentShift(Shift.I.name(), deptId, workDate), shiftMapper.getDepartmentShift(Shift.II.name(), deptId, workDate),
		  shiftMapper.getDepartmentShift(Shift.DAY.name(), deptId, workDate), shiftMapper.getDepartmentShift(Shift.NIGHT.name(), deptId, workDate),
		  shiftMapper.getDepartmentShift(Shift.III.name(), deptId, workDate)};
	  for (int i = 0; i < shifts.length; i++) {
		 ru.armd.pbk.domain.nsi.Shift shift = shifts[i];
		 if (cnt[i] > 0) {
			List<PlanSchedule> schs = planScheduleMapper.findFreeController(deptId, workDate, shift.getId());
			Set<Long> mbrgIds = new HashSet<Long>();
			for (Brigade mbr : manualBrgs) {
			   if (mbr.getShiftId().equals(shift.getId())) {
				  Iterator<PlanSchedule> it = schs.iterator();
				  int count = mbr.getMgtCount();
				  while (it.hasNext() && count > 0) {
					 PlanSchedule ps = it.next();
					 if (mbr.getCountyList().contains(String.valueOf(ps.getCountyId()))
						 || (checkBrgs != null && mbr.getShiftId().equals(Shift.NIGHT.getId()))) {
						it.remove();
						count--;
					 }
				  }
				  mbrgIds.add(mbr.getCityVenueId());
			   }
			}

			List<Brigade> newBrgs = new ArrayList<Brigade>();
			for (Venue v : venues) {
			   if ((v.isShiftI() == 1 && shift.getId().equals(Shift.I.getId()))
				   || (v.isShiftII() == 1 && shift.getId().equals(Shift.II.getId()))
				   || (v.isShiftIII() == 1 && shift.getId().equals(Shift.III.getId()))
				   || (v.isShiftDay() == 1 && shift.getId().equals(Shift.DAY.getId()))
				   || (v.isShiftNight() == 1 && shift.getId().equals(Shift.NIGHT.getId()))) {
				  if (mbrgIds.contains(v.getId())) {
					 continue;
				  }
				  Brigade br = new Brigade();
				  br.setDeptId(deptId);
				  br.setPlanDate(workDate);
				  br.setCityVenueId(v.getId());
				  br.setShiftId(shift.getId());
				  br.setCountyIds(v.getCountyIds());
				  br.setMgtCount(0);
				  br.setGkuopCount(v.getTpu() == 0 ? -1 : 0);
				  br.setCreateUserId(AuthenticationManager.getUserInfo().getUserId());
				  br.setCreateDate(new Date());
				  br.setUpdateUserId(AuthenticationManager.getUserInfo().getUserId());
				  br.setUpdateDate(new Date());
				  newBrgs.add(br);
			   }
			}
			if (newBrgs.size() == 0) {
			   continue;
			}

			final Map<Long, Brigade> checkShiftBrgs = new HashMap<>();
			if (checkBrgs != null) {
			   for (Brigade br : checkBrgs) {
				  if (shift.getId().equals(br.getShiftId()) && workDate.equals(br.getPlanDate())) {
					 if (br.getIsAgree()) {
						schs.clear();
						newBrgs.clear();
						break;
					 }
					 checkShiftBrgs.put(br.getCityVenueId(), br);
				  }
			   }
			}

			Iterator<PlanSchedule> it = schs.iterator();
			while (it.hasNext()) {
			   if (checkBrgs == null && schs.size() == shift.getReserveCount()) {
				  break;
			   }

			   PlanSchedule ps = it.next();
			   Collections.sort(newBrgs, new Comparator<Brigade>() {
				  @Override
				  public int compare(Brigade br1, Brigade br2) {
					 Brigade chkBr1 = checkShiftBrgs == null ? null : checkShiftBrgs.get(br1.getCityVenueId());
					 Brigade chkBr2 = checkShiftBrgs == null ? null : checkShiftBrgs.get(br2.getCityVenueId());
					 Integer br1MgtCount = chkBr1 == null ? br1.getMgtCount()
						 : Integer.valueOf(br1.getMgtCount() - chkBr1.getMgtCount());
					 Integer br2MgtCount = chkBr2 == null ? br2.getMgtCount()
						 : Integer.valueOf(br2.getMgtCount() - chkBr2.getMgtCount());
					 return br1MgtCount.compareTo(br2MgtCount);
				  }
			   });
			   for (Brigade br : newBrgs) {
				  if (br.getCountyList().contains(String.valueOf(ps.getCountyId())) || (checkBrgs != null && br.getShiftId().equals(Shift.NIGHT.getId()))) {
					 it.remove();
					 br.setMgtCount(br.getMgtCount() + 1);
					 break;
				  }
			   }
			}
			allBrgs.addAll(newBrgs);
		 }
	  }
	  return allBrgs;
   }

   /**
	* Подсчитывает количество смен мест встреч.
	*
	* @param venues места встречи
	* @return количество смен
	*/
   private int[] prepareCnt(List<Venue> venues) {
	  int[] cnt = new int[] {0, 0, 0, 0, 0};
	  for (Venue v : venues) {
		 if (v.isShiftI() == 1) {
			cnt[0]++;
		 }
		 if (v.isShiftII() == 1) {
			cnt[1]++;
		 }
		 if (v.isShiftDay() == 1) {
			cnt[2]++;
		 }
		 if (v.isShiftNight() == 1) {
			cnt[3]++;
		 }
		 if (v.isShiftIII() == 1) {
		 	cnt[4]++;
		 }
	  }
	  return cnt;
   }

   /**
	* Распределение контроллеров по бригадам.
	*
	* @param deptId   id подразделения
	* @param workDate дата
	*/
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void distributedControllersToBrigades(Long deptId, Date workDate, List<Long> approvedBrigades) {
	  Map<Long, Task> createdTasks = new HashMap<Long, Task>();
	  List<Brigade> brigades = planBrigadeMapper.getBrigades(workDate, deptId, null);
	  Map<Long, List<PlanSchedule>> schsByShift = new HashMap<Long, List<PlanSchedule>>();
	  Map<Long, List<Brigade>> brgsByShift = new HashMap<Long, List<Brigade>>();
	  Map<Long, Integer> brgTasksCount = new HashMap<Long, Integer>();
	  for (int step = 0; step < 1 /*2 - шаг - назначение в произвольное МВ убрано*/; step++) {
		 for (Brigade br : brigades) {
			if (approvedBrigades.contains(br.getId())) {
			   continue;
			}

			List<PlanSchedule> schedules = schsByShift.get(br.getShiftId());
			if (schedules == null) {
			   schedules = planScheduleMapper.findFreeController(br.getDeptId(), br.getPlanDate(), br.getShiftId());
			   Collections.shuffle(schedules);
			   schsByShift.put(br.getShiftId(), schedules);
			}

			List<Brigade> brgs = brgsByShift.get(br.getShiftId());
			if (brgs == null) {
			   brgs = new ArrayList<Brigade>();
			   brgsByShift.put(br.getShiftId(), brgs);
			}
			if (!brgs.contains(br)) {
			   brgs.add(br);
			}

			Integer i = brgTasksCount.get(br.getId());
			if (i == null) {
			   i = 0;
			}
			Iterator<PlanSchedule> it = schedules.iterator();
			while (it.hasNext()) {
			   PlanSchedule sch = it.next();
			   if (i++ < br.getMgtCount()) {
				  if (step == 0 && !br.getCountyList().contains(String.valueOf(sch.getCountyId()))
					  && !br.getShiftId().equals(Shift.NIGHT.getId())) {
					 i--;
				  } else {
					 createTask(br, sch, TaskStates.CREATED.getId(), createdTasks);
					 it.remove();
					 brgTasksCount.put(br.getId(), i);
				  }
			   } else {
				  break;
			   }
			}
		 }
	  }
	  for (List<PlanSchedule> schs : schsByShift.values()) {
		 brgTasksCount.clear();
		 Map<Long, List<Brigade>> brgsByCounty = new HashMap<>();
		 for (PlanSchedule sch : schs) {
			List<Brigade> brgs = brgsByCounty.get(sch.getCountyId());
			List<Brigade> allBrgs = brgsByShift.get(sch.getShiftId());
			if (brgs == null) {
			   brgs = new ArrayList<Brigade>();
			   for (Brigade brg : allBrgs) {
				  if (brg.getCountyList().contains(String.valueOf(sch.getCountyId()))) {
					 brgs.add(brg);
				  }
			   }
					/* if (brgs.isEmpty())
						brgs.addAll(allBrgs); */
			   brgsByCounty.put(sch.getCountyId(), brgs);
			}
			if (brgs.size() == 0) {
			   continue;
			}
			Integer i = brgTasksCount.get(sch.getCountyId());
			if (i == null) {
			   i = 0;
			}
			createTask(brgs.get(i % brgs.size()),
				sch, TaskStates.IN_RESERVE.getId(), createdTasks);
			brgTasksCount.put(sch.getCountyId(), ++i);
		 }
	  }
	  if (createdTasks.size() > 0) {
		 createTasks(deptId, createdTasks);
	  }
   }

   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   private void createTask(Brigade br, PlanSchedule sch, Long stateId, Map<Long, Task> createdTasks) {
	  // TODO поискать отмененное задание
	  if (sch.getTaskId() != null) {
		 Task t = taskMapper.getById(sch.getTaskId());
		 t.setStateId(stateId);
		 t.setPlanVenueId(br.getId());
		 taskRepository.save(t);
	  } else {
		 //createTask(br.getDeptId(), sch.getId(), sch.getEmployeeId(), br.getId(), stateId, false);
		 Task tsk = new Task();
		 tsk.setDeptId(br.getDeptId());
		 tsk.setTaskTypeId(TaskType.PLAN_TASK.getId());
		 tsk.setPlanScheduleId(sch.getId());
		 tsk.setPlanVenueId(br.getId());
		 tsk.setCreateUserId(AuthenticationManager.getUserInfo().getUserId());
		 tsk.setCreateDate(new Date());
		 tsk.setUpdateUserId(AuthenticationManager.getUserInfo().getUserId());
		 tsk.setUpdateDate(new Date());
		 tsk.setStateId(stateId);
		 createdTasks.put(sch.getEmployeeId(), tsk);
	  }
   }

   private Long getEmployeeBso(Long deptId, Long employeeId) {
	  Long bsoId = null;
	  List<SelectItem> bsos = bsoMapper.getNonUsedBsoForEmployee(employeeId, BsoType.TASK.getId());
	  if (bsos.size() == 0) {
		 List<Bso> newBsos = null;
		 newBsos = bsoMapper.getNonUsedBso(deptId, BsoType.TASK.getId(), 20);
		 if (newBsos != null && newBsos.size() > 0) {
			newBsos = newBsos.subList(0, Math.min(10, newBsos.size()));
		 }
		 if (newBsos == null || newBsos.size() == 0) {
			newBsos = bsoService.saveBulk(deptId, BsoType.TASK.getId(), 10);
		 }
		 for (Bso bso : newBsos) {
			bsoRepository.bind(bso.getId(), employeeId);
		 }
		 bsoId = newBsos.get(0).getId();
	  } else {
		 bsoId = bsos.get(0).getId();
	  }
	  return bsoId;
   }

   /**
	* Получить ИД бригады, которая относиться к округу расписания.
	*
	* @param brigades бригады.
	* @param ps       расписание.
	* @return
	*/
   private Long getBrigadeByCountyPlanSchedule(List<Brigade> brigades, PlanSchedule ps) {
	  for (Brigade brigade : brigades) {
		 if (brigade.getCountyList().contains(String.valueOf(ps.getCountyId()))) {
			return brigade.getId();
		 }
	  }
	  return null;
   }

   private void distributedControllersForChange(Brigade br) {
	  List<Task> tasks = taskMapper.getIncorrectControllerTasks(br.getId());
	  for (Task t : tasks) {
		 PlanSchedule ps = planScheduleMapper.getPlanScheduleById(t.getPlanScheduleId());
		 Shift shift = Shift.getEnumById(ps.getShiftId());
		 if (shift.isWorkDay()) { // изменилась смена - необходимо изменить бригаду
			List<Brigade> incompleteBrigades = planBrigadeMapper.checkBrigadeShiftsForVenues(
				br.getDeptId(), ps.getShiftId(), br.getPlanDate(), br.getPlanDate());
			Long brId = getBrigadeByCountyPlanSchedule(incompleteBrigades, ps);
			if (brId != null) { // нашли неполную бригаду
			   t.setPlanVenueId(brId);
			   t.setStateId(TaskStates.CREATED.getId());
			} else {
			   List<Brigade> brigades = planBrigadeMapper.getBrigades(br.getPlanDate(), br.getDeptId(), ps.getShiftId());
			   brId = getBrigadeByCountyPlanSchedule(brigades, ps);
			   if (brId != null) { // нашли какую-нибудь бригаду с нужной сменой на дату
				  t.setPlanVenueId(brId);
				  t.setStateId(TaskStates.IN_RESERVE.getId());
			   } else {
				  // отменить задание, т.к. нет МВ с нужной сменой
				  t.setStateId(TaskStates.CANCELED.getId());
				  t.setCancelReason("Нет МВ с нужной сменой");
			   }
			}
			initUpdaterInfo(t);
			taskRepository.save(t);
			distributedRoutesToTasks(br.getDeptId(), br.getPlanDate());
			continue;
		 }

		 if (t.getStateId().equals(TaskStates.IN_RESERVE.getId())) {
			if (t.getChangePlanScheduleId() == null) {
			   taskRepository.delete(t.getId());
			}
			continue;
		 }
		 List<PlanSchedule> schedules = !t.getStateId().equals(TaskStates.IN_RESERVE.getId()) ? planScheduleMapper.findFreeController(br.getDeptId(), br.getPlanDate(),
			 br.getShiftId()) : null;
			/*
			 * if (t.getBsoId() != null) {
			 * bsoService.disuseBsos(Arrays.asList(new Long[] { t.getBsoId() }),
			 * t.getId()); }
			 */
		 // TODO изменить статус у текущей задачи + изменить статус у второй
		 // задачи + прикрепить маршруты
		 // + учитывать откруг?
		 initUpdaterInfo(t);
		 t.setStateId(TaskStates.CANCELED.getId());
		 if (ps != null) {
			t.setCancelReason(shift.getName());
		 }
		 PlanSchedule changedPS = null;
			/*
			 * Задание не в резерве.
			 * Нужно найти задание на замену.
			 * Ищем сотрудника с нужным округом.
			 */
		 if (schedules != null) {
			for (PlanSchedule sch : schedules) {
			   if (br.getCountyIds() == null || sch.getCountyId() == null) {
				  continue;
			   }
			   if (br.getCountyIds().contains(String.valueOf(sch.getCountyId()))) {
				  changedPS = sch;
				  break;
			   }
			}
		 }

		 t.setChangePlanScheduleId(changedPS == null ? null : changedPS.getId());
		 taskMapper.changeController(t);

		 if (t.getChangePlanScheduleId() != null) {
			Task changingTask = null;
			if (changedPS.getTaskId() != null) {
			   changingTask = taskMapper.getById(changedPS.getTaskId());
			   changingTask.setStateId(TaskStates.CREATED.getId());
			   changingTask.setPlanVenueId(t.getPlanVenueId());
			   initUpdaterInfo(changingTask);
			} else {
			   ps = planScheduleMapper.getPlanScheduleById(t.getChangePlanScheduleId());
			   changingTask = createTask(ps.getDeptId(), ps.getId(), ps.getEmployeeId(), t.getPlanVenueId(),
				   TaskStates.CREATED.getId(), true);
			}
			changingTask = taskRepository.save(changingTask);
			taskMapper.moveRoutes(t.getId(), changingTask.getId(), AuthenticationManager.getUserInfo().getUserId());
		 }
	  }
   }

   private Task createTask(Long deptId, Long psId, Long employeeId, Long brigadeId, Long stateId, boolean createReport) {
	  Task tsk = new Task();
	  tsk.setDeptId(deptId);
	  tsk.setTaskTypeId(TaskType.PLAN_TASK.getId());
	  tsk.setPlanScheduleId(psId);
	  tsk.setPlanVenueId(brigadeId);
	  tsk.setBsoId(getEmployeeBso(deptId, employeeId));
	  tsk.setCreateUserId(AuthenticationManager.getUserInfo().getUserId());
	  tsk.setCreateDate(new Date());
	  tsk.setUpdateUserId(AuthenticationManager.getUserInfo().getUserId());
	  tsk.setUpdateDate(new Date());
	  tsk.setStateId(stateId);
	  taskMapper.insert(tsk);
	  if (createReport) {
		 taskReportRepository.createEmptyReport(tsk.getId());
	  }
	  bsoService.useBsos(Arrays.asList(new Long[] {tsk.getBsoId()}), tsk.getId());
	  askpBindService.bindAskpByPlanSchedulerId(tsk.getPlanScheduleId());
	  return tsk;
   }

   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   private void createTasks(Long deptId, Map<Long, Task> empTasks) {
	  List<SelectItem> empBsoMap4Bind = new ArrayList<SelectItem>();
	  List<Long> bsoIds = new ArrayList<>();
	  List<SelectItem> bsos = bsoMapper.getNonUsedBsoForEmployees(empTasks.keySet(), BsoType.TASK.getId());
	  int emptyBsoEmp = 0;
	  for (Long empId : empTasks.keySet()) {
		 Long bsoId = null;
		 for (SelectItem bso : bsos) {
			if (bso.getName().equals(empId.toString())) {
			   bsoId = bso.getId();
			   break;
			}
		 }
		 empTasks.get(empId).setBsoId(bsoId);
		 bsoIds.add(bsoId);
		 if (bsoId == null) {
			emptyBsoEmp++;
		 }
	  }
	  if (emptyBsoEmp > 0) {
		 List<Bso> newBsos = null;
		 newBsos = bsoMapper.getNonUsedBso(deptId, BsoType.TASK.getId(), emptyBsoEmp);
		 if (newBsos == null) {
			newBsos = new ArrayList<Bso>();
		 }
		 if (newBsos.size() < emptyBsoEmp) {
			newBsos.addAll(bsoService.saveBulk(deptId, BsoType.TASK.getId(), (emptyBsoEmp - newBsos.size()) * 5));
		 }
		 for (Map.Entry<Long, Task> empTask : empTasks.entrySet()) {
			if (empTask.getValue().getBsoId() == null) {
			   empTask.getValue().setBsoId(newBsos.remove(0).getId());
			   empBsoMap4Bind.add(new SelectItem(empTask.getKey(), empTask.getValue().getBsoId().toString()));
			   bsoIds.add(empTask.getValue().getBsoId());
			}
		 }
		 bsoMapper.binds(empBsoMap4Bind);
	  }
	  taskMapper.createTasks(empTasks.values());
	  bsoMapper.uses(bsoIds);
	  bsoMapper.updateBindsState(bsoIds, true);
	  bsoMapper.updateUsesState(bsoIds, true);
	  if (empTasks.values().iterator().hasNext()) {
		 askpBindService.bindAskpByPlanSchedulerId(empTasks.values().iterator().next().getPlanScheduleId());
	  }
	  taskReportRepository.insertReports(AuthenticationManager.getUserInfo().getUserId());
   }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
   public void distributedRoutesToTasks(Map<Long, Date> changeRoutes, Map<Long, Date> changeDepts) {
	  List<Long> deptIds = new ArrayList<Long>();
	  if (changeRoutes == null && changeDepts != null) {
		 deptIds.addAll(changeDepts.keySet());
	  } else {
		 List<Department> depts = departmentRepository.getDomains(new HashMap<String, Object>());
		 for (Department dept : depts) {
			deptIds.add(dept.getHeadId());
		 }
	  }
	  for (Long deptId : deptIds) {
/*		 Calendar c = Calendar.getInstance();
		 c.set(Calendar.HOUR_OF_DAY, 0);
		 c.set(Calendar.MINUTE, 0);
		 c.set(Calendar.SECOND, 0);
		 Calendar cTo = Calendar.getInstance();
		 cTo.setTime(c.getTime());
		 cTo.add(Calendar.DAY_OF_YEAR, 7);*/

		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("brigadeId", -1);
		 params.put("deptId", deptId);

		  if (changeDepts != null) {
			  if (changeDepts.containsKey(deptId)) {
				  distributedRoutesToTasks(deptId, changeDepts.get(deptId));
			  }
		  } else if (changeRoutes != null) {
			  List<RouteSelectItem> routes = routeMapper.getSelectItemsForTask(params);
			  for (ISelectItem r : routes) {
				  Date d = changeRoutes.get(r.getId());
				  if (d != null) {
					  distributedRoutesToTasks(deptId, d);
					  break;
				  }
				 /* if (d != null && !d.after(c.getTime())) {
					  distributedRoutesToTasks(deptId, c.getTime());
					  break;
				  }*/
			  }
		  }
/*		 do {
			 Date test2 = c.getTime();
			if (changeDepts != null) {
			   if (changeDepts.containsKey(deptId)
				   && !changeDepts.get(deptId).after(c.getTime())) {
				  distributedRoutesToTasks(deptId, c.getTime());
			   }
			} else if (changeRoutes != null) {
			   List<RouteSelectItem> routes = routeMapper.getSelectItemsForTask(params);
			   for (ISelectItem r : routes) {
				  Date d = changeRoutes.get(r.getId());
				  if (d != null && !d.after(c.getTime())) {
					 distributedRoutesToTasks(deptId, c.getTime());
					 break;
				  }
			   }
			}
			c.add(Calendar.DAY_OF_YEAR, 1);
		 } while (c.getTime().before(cTo.getTime()));*/
	  }
   }

   /**
	* Распределение маршрутов по заданиям подразделения {@code deptId}.
	*
	* @param deptId     ИД подразделения.
	* @param startDate  дата начала
	* @param finishDate дата окончания
	*/
   public void distributedRoutesToTasks(Long deptId, Date startDate, Date finishDate) {
	  Calendar c = Calendar.getInstance();
	  if (startDate != null) {
		 c.setTime(startDate);
	  }
	  Calendar cTo = Calendar.getInstance();
	  if (finishDate != null) {
		 cTo.setTime(finishDate);
	  } else {
		 cTo.add(Calendar.DAY_OF_YEAR, 7);
	  }
	  trimCalendar(c);
	  trimCalendar(cTo);
	  while (!c.getTime().after(cTo.getTime())) {
		 distributedRoutesToTasks(deptId, c.getTime());
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  }
   }

   /**
	* Назначение маршрутов контроллерам.
	*
	* @param deptId   ид подразделения.
	* @param workDate работчая дата.
	*/

   //@Transactional
   //@Transactional(propagation = Propagation.REQUIRES_NEW)
   private void distributedRoutesToTasks(Long deptId, Date workDate) {



	  List<Task> tasks = taskMapper.getTasksByWorkDate(deptId, workDate);
	  Map<Long, List<CheckRoute>> routesByPlanVenues = new HashMap<Long, List<CheckRoute>>();
	  final Map<Long, Integer> tasksLoadingInputs = new HashMap<Long, Integer>();
	  final Map<Long, Map<Long, Long>> tasksLoadingRoutes = new HashMap<Long, Map<Long, Long>>();
	  final Map<Long, Long> tasksDistrict = new HashMap<Long, Long>();
	  for (Task t : tasks) {
		 if (!routesByPlanVenues.containsKey(t.getPlanVenueId())) {
			routesByPlanVenues.put(t.getPlanVenueId(),
				planScheduleMapper.getAvailableForCheckRoutes(workDate, t.getPlanVenueId()));
		 }
		 tasksLoadingInputs.put(t.getId(), 0);
		 tasksLoadingRoutes.put(t.getId(), new HashMap<Long, Long>());
	  }
	  boolean stop = false;
	  while (!stop) {
		 stop = true;
		 Collections.sort(tasks, new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
			   int cmp = tasksLoadingInputs.get(t1.getId()).compareTo(tasksLoadingInputs.get(t2.getId()));
			   return cmp == 0 ? Integer.valueOf(tasksLoadingRoutes.get(t1.getId()).size()).compareTo(
				   Integer.valueOf(tasksLoadingRoutes.get(t2.getId()).size())) : cmp;
			}
		 });
		 for (Task t : tasks) {
			Integer load = tasksLoadingInputs.get(t.getId());
			Long districtId = null;
			List<CheckRoute> routes = routesByPlanVenues.get(t.getPlanVenueId());
			if (routes != null) {
			   Iterator<CheckRoute> itr = routes.iterator();
			   while (itr.hasNext()) {
				  CheckRoute route = itr.next();
				  if (load + route.getTsCount() <= MAX_CONTROLLER_LOAD) {
					 if (districtId == null) {
						if (t.getCountyId() == null
							|| (route.getCountyId() != null && route.getCountyId().equals(t.getCountyId()))) {
						   districtId = route.getDistrictId();
						   if (!tasksDistrict.containsKey(t.getId())) {
							  tasksDistrict.put(t.getId(), districtId);
						   }
						} else {
						   continue;
						}
					 } else if (!districtId.equals(route.getDistrictId())) {
						continue;
					 }
					 load += route.getTsCount();
					 tasksLoadingRoutes.get(t.getId()).put(route.getRouteId(), districtId);

					 for (List<CheckRoute> crs : routesByPlanVenues.values()) {
						Iterator<CheckRoute> it = crs.iterator();
						while (it.hasNext()) {
						   CheckRoute cr = it.next();
						   if (cr.getRouteId().equals(route.getRouteId())) {
							  it.remove();
						   }
						}
					 }
					 tasksLoadingInputs.put(t.getId(), load);
					 stop = false;
					 itr = routes.iterator();
				  } else {
					 break;
				  }
			   }
			}
			if (!stop) {
			   break;
			}
		 }
	  }

//	  try {
//		  Thread.sleep(2000);
//	  }catch(Exception e){}
	   taskRouteRepository.removeTasksRoutesByWorkDate(deptId, workDate);
	  for (Map.Entry<Long, Map<Long, Long>> en : tasksLoadingRoutes.entrySet()) {
		 if (en.getValue().size() > 0) {
			taskRouteRepository.insertRoutes(en.getKey(), en.getValue());
		 }
	  }
	  if (!tasksDistrict.isEmpty()) {
		 taskRepository.updateTaskDistrict(tasksDistrict);
	  }
	  taskRouteRepository.deleteZeroMoveRoutesByDate(workDate);
   }

   public void checkControllerGraph(Long employeeId, Date startDate, Date endDate, Long absenceMode) {
	  try {
		 Calendar c = Calendar.getInstance();
		 c.setTime(startDate);
		 c = trimCalendar(c);
		 Calendar c2 = Calendar.getInstance();
		 c2.setTime(endDate);
		 c2 = trimCalendar(c2);
		 while (!c.getTime().after(c2.getTime())) {
			if (planScheduleMapper.isForPlanUse(employeeId, c.getTime()) != null) {
			   ScheduleShift ps = planScheduleMapper.getPlanScheduleCell(employeeId, c.getTime());
			   if (ps == null) {
				  ps = new ScheduleShift();
				  ps.setPlanDate(c.getTime());
				  initCreaterInfo(ps);
				  ps.setEmployeeId(employeeId);
				  ps.setDeptId(employeeRepository.getActual(employeeId).getDepartmentId());
			   }
			   Long shiftId = absenceMode.equals(EmployeeAbsenceTypes.HOSPITAL.getId()) ? Shift.SICK.getId()
				   : Shift.VACATION.getId();
			   if (!(Shift.SICK.getId().equals(ps.getShiftId())
				   || Shift.VACATION.getId().equals(ps.getShiftId()))) {
				  ps.setShiftId(shiftId);
				  initUpdaterInfo(ps);
				  savePlanSchedule(ps);
				  formBrigades(ps.getDeptId(), c.getTime(), false, false);
			   }
			}
			c.add(Calendar.DAY_OF_YEAR, 1);
		 }
	  } catch (Exception e) {
		 getLogger().error("Employee absence process: " + employeeId + " " + startDate
			 + " " + endDate, e);
	  }
   }

   /**
	* Проверка расписания сотрудника. Если расписание расходится с ФХД,
	* оно будет скорректировано.
	*
	* @param employeeId    ИД сотрудника
	* @param workDate      дата проверки
	* @param workMode      режим работы
	* @param workPlanHours плановое время работы
	*/
   public void checkControllerGraph(Long employeeId, Date workDate, Long workMode, BigDecimal workPlanHours) {
	  checkControllerGraph(employeeId, workDate, workMode, workPlanHours);
   }

   /**
	* Проверка расписания сотрудника. Если расписание расходится с ФХД,
	* оно будет скорректировано после даты указаной в {@code exchangeWorkDate}.
	*
	* @param employeeId       ИД сотрудника
	* @param workDate         дата проверки
	* @param workMode         режим работы
	* @param workPlanHours    плановое время работы
	* @param exchangeWorkDate дата обмена с ФХД
	*/
   public void checkControllerGraph(Long employeeId, Date workDate, Long workMode,
									BigDecimal workPlanHours, Date exchangeWorkDate, boolean forMove) {
	   if (planScheduleMapper.isForPlanUse(employeeId, workDate) == null) {
		   return;
	   }
	   ScheduleShift ps;
	   if (forMove) {
		   Date oneDayBeforeExchangeWorkDate = DateUtils.addDays(exchangeWorkDate, -1);
		   ps = planScheduleMapper.getPlanScheduleCellForMove(employeeId, workDate, oneDayBeforeExchangeWorkDate);
		   //Устанавливаем новый dept_id для случая перевода сотрудника.
		   if (ps != null) {
			   ps.setDeptId(employeeRepository.getActual(employeeId).getDepartmentId());
			   savePlanSchedule(ps);
		   }
	   } else {
		   ps = planScheduleMapper.getPlanScheduleCell(employeeId, workDate);
	   }
	   if (exchangeWorkDate != null && workDate.before(exchangeWorkDate)) {
		   if (ps == null) {
			   savePlanSchedule(checkControllerGraph(ps, employeeId, workDate, workMode, workPlanHours).getValue());
		   }
	   } else {
		   Pair<Boolean, ScheduleShift> pair = checkControllerGraph(ps, employeeId, workDate, workMode, workPlanHours);
		   if (pair.getKey()) {
			   savePlanSchedule(pair.getValue());
			   formBrigades(pair.getValue().getDeptId(), workDate, false, false);
		   }
	   }
   }

   protected Pair<Boolean, ScheduleShift> checkControllerGraph(ScheduleShift ps, Long employeeId, Date workDate, Long workMode,
															   BigDecimal workPlanHours) {
	  boolean bChange = false;
	  if (ps == null) {
		 ps = new ScheduleShift();
		 ps.setPlanDate(workDate);
		 initCreaterInfo(ps);
		 ps.setEmployeeId(employeeId);
		 ps.setDeptId(employeeRepository.getActual(employeeId).getDepartmentId());
		 ps.setShiftId(Shift.HOLIDAY.getId());
		 bChange = true;
	  } else if (ps.getIsForPlanUse() == null || !ps.getIsForPlanUse()) {
		 return new MutablePair<Boolean, ScheduleShift>(bChange, ps);
	  }
	  if (workMode == 1 && !ps.getShiftId().equals(Shift.HOLIDAY.getId())) { // Выходной
		 ps.setShiftId(Shift.HOLIDAY.getId());
		 bChange = true;
	  } else if (workMode == 2) { // День
		 boolean bDay = workPlanHours.doubleValue() > 9;
		 if (!ps.getShiftId().equals(Shift.DAY.getId()) && !ps.getShiftId().equals(Shift.I.getId())
			 && !ps.getShiftId().equals(Shift.II.getId()) && !ps.getShiftId().equals(Shift.III.getId())) {
			if (bDay) {
			   ps.setShiftId(Shift.DAY.getId());
			   bChange = true;
			} else {
			   setWorkMode(ps);
			   bChange = true;
			}
		 } else if (bDay && (ps.getShiftId().equals(Shift.I.getId()) || ps.getShiftId().equals(Shift.II.getId()) || ps.getShiftId().equals(Shift.III.getId()))) {
			ps.setShiftId(Shift.DAY.getId());
			bChange = true;
		 } else if (!bDay && (ps.getShiftId().equals(Shift.DAY.getId()) || ps.getShiftId().equals(Shift.NIGHT.getId()))) {
			setWorkMode(ps);
			bChange = true;
		 }
	  } else if (workMode == 3 && !ps.getShiftId().equals(Shift.NIGHT.getId())) { // Ночь
		 ps.setShiftId(Shift.NIGHT.getId());
		 bChange = true;
	  } else if (workMode == 4 && !ps.getShiftId().equals(Shift.SICK.getId())
		  && !ps.getShiftId().equals(Shift.VACATION.getId())) { // Не работает
		 ps.setShiftId(Shift.VACATION.getId());
		 bChange = true;
	  }
	  return new MutablePair<Boolean, ScheduleShift>(bChange, ps);
   }

   private void setWorkMode(ScheduleShift ps) {
	  List<ScheduleShift> prevSchedules = planScheduleMapper.getPreviousPlanSchedulesForDepts(ps.getPlanDate(), ps.getDeptId(),
		  ps.getEmployeeId());
	  if (prevSchedules.size() == 0) {
		 ps.setShiftId(Shift.I.getId());
	  } else {
		 if (DateUtils.isOneWeek(prevSchedules.get(0).getPlanDate(), ps.getPlanDate())) {
			ps.setShiftId(prevSchedules.get(0).getShiftId());
		 } else {
			 if (prevSchedules.get(0).getShiftId().equals(Shift.I.getId())) ps.setShiftId(
					 Shift.II.getId());
			 else ps.setShiftId(
					 Shift.I.getId());
		 }
	  }
   }

   private void savePlanSchedule(ScheduleShift ps) {
	  planScheduleRepository.save(ps);
   }

   private Calendar trimCalendar(Calendar c) {
	  c.set(Calendar.HOUR_OF_DAY, 0);
	  c.set(Calendar.MINUTE, 0);
	  c.set(Calendar.SECOND, 0);
	  return c;
   }

   protected Boolean isNotWork(ScheduleWorkMode ps) {
	  return ps.getWorkModeId().equals(WorkModeType.HOLIDAY.getId())
		  || ps.getShiftId().equals(Shift.HOLIDAY.getId()) || ps.getShiftId().equals(Shift.OTHER.getId()) 
		  || ps.getShiftId().equals(Shift.LINE1.getId()) || ps.getShiftId().equals(Shift.LINE2.getId())
		  || ps.getShiftId().equals(Shift.SICK.getId()) || ps.getShiftId().equals(Shift.VACATION.getId());
   }

   protected Boolean isSickOrVacationWork(ScheduleWorkMode ps) {
	  return ps.getShiftId().equals(Shift.OTHER.getId())
		  || ps.getShiftId().equals(Shift.LINE1.getId())
		  || ps.getShiftId().equals(Shift.LINE2.getId())
		  || ps.getShiftId().equals(Shift.SICK.getId())
		  || ps.getShiftId().equals(Shift.VACATION.getId());
   }

   protected void processShiftModesForEmployee(Employee emp) {
	  Calendar c = Calendar.getInstance();
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  c.add(Calendar.MONTH, -1);

	  List<ScheduleWorkMode> pss = planScheduleMapper.getEmployeeWorkModes(emp.getHeadId(), c.getTime());

	  int holidayIdx = 0; // порядковый номер выходного
	  int workdayIdx = 0; // порядковый номер рабочего  дня
	  Date holidayDate = null;
	  Date vacationDate = null;
	  Boolean isDayNight = null; // признак смены (true - ДНВВ)
	  List<Long[]> psIds = new ArrayList<Long[]>(); // рабочие дни перед
	  // выходными
	  Long shiftModeId = null;
	  for (ScheduleWorkMode ps : pss) {
		 if (ps.getPsId() == null) { // в плановом расписании не
			// заполнена ячейка - пробрасываем
			// остальные записи контроллера
			break;
		 }
		 if (isNotWork(ps)) { // определили
			// день
			// как
			// выходной
			if (!isSickOrVacationWork(ps)) {
			   if (workdayIdx > 0) { // выходной после рабочего дня
				  holidayIdx++;
				  holidayDate = ps.getWorkDate();
			   }
			} else if (holidayIdx == 2) {
			   holidayIdx = 0;
			   workdayIdx = 0;
			   vacationDate = holidayDate;
			}
		 } else {
			if (holidayIdx != 0 || vacationDate != null) { // первый рабочий день после
			   // выходного
			   if (vacationDate != null) {
				  holidayDate = vacationDate;
				  vacationDate = null;
			   }
			   shiftModeId = saveEmployeeShiftModes(psIds, shiftModeId, holidayIdx, holidayDate,
				   isDayNight);
			   holidayIdx = 0;
			   isDayNight = null;
			   workdayIdx = 0;
			}
			if (!isNotWork(ps)) {
			   boolean currDayNight = ps.getShiftId().equals(Shift.DAY.getId())
				   || ps.getShiftId().equals(Shift.NIGHT.getId());
			   if (isDayNight == null) {
				  isDayNight = currDayNight;
			   } else {
				  if (isDayNight != currDayNight) {
					 if (isDayNight) {
						shiftModeId = ShiftModes.H_2_2.getId();
					 } else if (shiftModeId.equals(ShiftModes.H_2_2.getId())) {
						shiftModeId = ShiftModes.H_6_7.getId();
					 }
					 saveEmployeeShiftModes(psIds, shiftModeId, holidayIdx, holidayDate, isDayNight);
					 isDayNight = currDayNight;
				  }
			   }
			}
			workdayIdx++;
		 }
		 psIds.add(new Long[] {ps.getPsId(), ps.getShiftModeId()});
	  }
	  saveEmployeeShiftModes(psIds, shiftModeId, holidayIdx, holidayDate, isDayNight);
   }

   /**
	* Проставить режим работы для сотрудника
	* с персональным номером {@code personalNumber}.
	*
	* @param personalNumber персональный номер сотрудника МГТ.
	*/
   public void processShiftModes(String personalNumber) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("personalNumber", personalNumber);
	  Employee emp = employeeRepository.getDomain(params);
	  if (emp.getForPlanUse() == null || !emp.getForPlanUse()) {
		 return;
	  }
	  processShiftModesForEmployee(emp);
   }

   /**
	* Проставить режим работы для всех сотрудников.
	*/
   public void processShiftModes() {
	  List<Employee> emps = employeeRepository.getDomains(new HashMap<String, Object>());
	  for (Employee emp : emps) {
		 if (emp.getForPlanUse() == null || !emp.getForPlanUse()) {
			continue;
		 }
		 processShiftModesForEmployee(emp);
	  }
   }

   private Long saveEmployeeShiftModes(List<Long[]> psIds, Long shiftModeId, int holidayIdx, Date holidayDate,
									   Boolean isDayNight) {
	  Long resultShiftModeId = shiftModeId;
	  if (holidayIdx == 2) {
		 if (isDayNight != null && isDayNight) {
			resultShiftModeId = ShiftModes.H_2_2.getId();
		 } else if (isDayNight == null) {
			resultShiftModeId = ShiftModes.H_6_7.getId();
		 } else {
			Calendar c = Calendar.getInstance();
			c.setTime(holidayDate);
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			   resultShiftModeId = ShiftModes.H_5_6.getId();
			} else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			   resultShiftModeId = ShiftModes.H_6_7.getId();
			} else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			   resultShiftModeId = ShiftModes.H_7_1.getId();
			}
		 }

	  }
	  if (resultShiftModeId != null) {
		 for (Long[] ps : psIds) {
			if (ps[1] == null || !ps[1].equals(resultShiftModeId)) {
			   ScheduleShift sch = planScheduleMapper.getById(ps[0]);
			   if (sch != null) {
				  sch.setShiftModeId(resultShiftModeId);
				  planScheduleRepository.save(sch);
			   }
			}
		 }
	  }
	  if (holidayIdx == 2) {
		 psIds.clear();
	  }
	  return resultShiftModeId;
   }

   /**
	* Отменить некорректные задания для подразделения.
	* Задание не корректно, если, например,  сотрудник был уволен,
	* а для него есть задания.
	*
	* @param deptId   подразделение
	* @param fireDate дата
	*/
   public void cancelTasksForDept(Long deptId, Date fireDate) {
	  Calendar workDate = Calendar.getInstance();
	  Date date = DateUtils.shiftToDayStart(fireDate);
	  if (date != null) {
		 workDate.setTime(date);
	  }
	  Calendar cTo = Calendar.getInstance();
	  cTo.set(Calendar.DAY_OF_MONTH, 1);
	  cTo.add(Calendar.MONTH, 2);
	  cTo.add(Calendar.DAY_OF_MONTH, -1);
	  cTo.setTime(DateUtils.shiftToDayStart(cTo.getTime()));
	  while (!workDate.getTime().after(cTo.getTime())) {
		 Calendar c = Calendar.getInstance();
		 c.set(Calendar.DAY_OF_MONTH, 1);
		 c.add(Calendar.MONTH, 2);
		 if (!workDate.getTime().before(c.getTime())) {
			return;
		 }

		 List<Brigade> brigades = planBrigadeMapper.getBrigades(workDate.getTime(), deptId, null);
		 boolean isAgree = brigades != null && !brigades.isEmpty() && brigades.get(0).getIsAgree();

		 if (isAgree) {
			for (Brigade br : brigades) {
			   cancelTasksForBridge(br);
			}
		 }
		 workDate.add(Calendar.DAY_OF_YEAR, 1);
	  }
   }

   protected void cancelTasksForBridge(Brigade br) {
	  List<Task> tasks = taskMapper.getIncorrectControllerTasks(br.getId());
	  for (Task t : tasks) {
		 PlanSchedule ps = planScheduleMapper.getPlanScheduleById(t.getPlanScheduleId());
		 Shift shift = Shift.getEnumById(ps.getShiftId());

		 List<PlanSchedule> schedules = !t.getStateId().equals(TaskStates.IN_RESERVE.getId()) ? planScheduleMapper.findFreeController(br.getDeptId(), br.getPlanDate(),
			 br.getShiftId()) : null;
		 initUpdaterInfo(t);
		 t.setStateId(TaskStates.CANCELED.getId());
		 if (ps != null) {
			t.setCancelReason(shift.getName());
		 }
		 PlanSchedule changedPS = null;
			
			/*
			 * Задание не в резерве.
			 * Нужно найти задание на замену.
			 * Ищем сотрудника с нужным округом.
			 */
		 if (schedules != null) {
			for (PlanSchedule sch : schedules) {
			   if (br.getCountyIds() == null || sch.getCountyId() == null) {
				  continue;
			   }
			   if (br.getCountyIds().contains(String.valueOf(sch.getCountyId()))) {
				  changedPS = sch;
				  break;
			   }
			}
		 }

		 t.setChangePlanScheduleId(changedPS == null ? null : changedPS.getId());
		 taskMapper.changeController(t);

		 if (t.getChangePlanScheduleId() != null) {
			Task changingTask = null;
			if (changedPS.getTaskId() != null) {
			   changingTask = taskMapper.getById(changedPS.getTaskId());
			   changingTask.setStateId(TaskStates.CREATED.getId());
			   changingTask.setPlanVenueId(t.getPlanVenueId());
			   initUpdaterInfo(changingTask);
			} else {
			   ps = planScheduleMapper.getPlanScheduleById(t.getChangePlanScheduleId());
			   changingTask = createTask(ps.getDeptId(), ps.getId(), ps.getEmployeeId(), t.getPlanVenueId(),
				   TaskStates.CREATED.getId(), true);
			}
			changingTask = taskRepository.save(changingTask);
			taskMapper.moveRoutes(t.getId(), changingTask.getId(), AuthenticationManager.getUserInfo().getUserId());
		 }
	  }
   }
}
