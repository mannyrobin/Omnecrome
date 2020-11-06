package ru.armd.pbk.domain.plans.schedules;

import ru.armd.pbk.domain.plans.brigades.ReserveCount;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.views.plans.schedules.EmployeePlanScheduleView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Домен расписания отдела.
 */
public class PlanScheduleView {

   private List<EmployeePlanScheduleView> employees;
   private List<ScheduleShift> scheduleShifts;
   private List<Long> reserves;
   private Map<String, String> allEmpsCounts = new HashMap<String, String>();

   public List<ScheduleShift> getScheduleShifts() {
	  return scheduleShifts;
   }

   public void setScheduleShifts(List<ScheduleShift> scheduleShifts) {
	  this.scheduleShifts = scheduleShifts;
   }

   public List<EmployeePlanScheduleView> getEmployees() {
	  return employees;
   }

   public void setEmployees(List<EmployeePlanScheduleView> employees) {
	  this.employees = employees;
   }

   public List<Long> getReserves() {
	  return reserves;
   }

   public void setReserves(List<Long> reserves) {
	  this.reserves = reserves;
   }

   public void setReserveCount(List<ReserveCount> reserves) {
	  allEmpsCounts.clear();
	  if (reserves != null) {
		 Map<String, int[]> mapAll = new HashMap<String, int[]>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 for (ReserveCount rc : reserves) {
			String key = sdf.format(rc.getPlanDate());
			int[] shiftsAll = mapAll.get(key);
			if (shiftsAll == null) {
			   shiftsAll = new int[] {0, 0, 0, 0};
			}
			long sh = rc.getShiftId().longValue();
			int idx = sh == Shift.I.getId().longValue() ? 0
				: sh == Shift.II.getId().longValue() ? 1 : sh == Shift.DAY.getId().longValue() ? 2 : 3;
			shiftsAll[idx] += rc.getEmployeeCount();
			mapAll.put(key, shiftsAll);
		 }
		 for (Map.Entry<String, int[]> en : mapAll.entrySet()) {
			int[] vA = en.getValue();
			String v = "";
			for (int k = 0; k < vA.length; k++) {
			   v += (v.isEmpty() ? "" : " ") + (vA[k] < 0 ? 0 : vA[k]);
			}
			allEmpsCounts.put(en.getKey(), v);
		 }
	  }
   }

   public Map<String, String> getAllEmpsCounts() {
	  return allEmpsCounts;
   }

   public void setAllEmpsCounts(Map<String, String> allEmpsCounts) {
	  this.allEmpsCounts = allEmpsCounts;
   }
}