package ru.armd.pbk.domain.plans.brigades;

import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.views.plans.brigades.BrigadeVenueView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Домен плана бригад плана отдела.
 */
public class PlanBrigade {

   private List<BrigadeVenueView> brigadeVenues;
   private List<Brigade> brigades;
   private Map<String, String> reserveCounts = new HashMap<String, String>();

   public List<BrigadeVenueView> getBrigadeVenues() {
	  return brigadeVenues;
   }

   public void setBrigadeVenues(List<BrigadeVenueView> brigadeVenues) {
	  this.brigadeVenues = brigadeVenues;
   }

   public List<Brigade> getBrigades() {
	  return brigades;
   }

   public void setBrigades(List<Brigade> brigades) {
	  this.brigades = brigades;
   }

   public void setReserveCount(List<ReserveCount> reserves) {
	  reserveCounts.clear();
	  if (reserves != null) {
		 Map<String, int[]> mapReserve = new HashMap<String, int[]>();
		 Map<String, int[]> mapAll = new HashMap<String, int[]>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 for (ReserveCount rc : reserves) {
			String key = sdf.format(rc.getPlanDate());
			int[] shiftsReserve = mapReserve.get(key);
			int[] shiftsAll = mapAll.get(key);
			if (shiftsReserve == null) {
			   shiftsReserve = new int[] {0, 0, 0, 0};
			   shiftsAll = new int[] {0, 0, 0, 0};
			}
			long sh = rc.getShiftId().longValue();
			int idx = sh == Shift.I.getId().longValue() ? 0
				: sh == Shift.II.getId().longValue() ? 1 : sh == Shift.DAY.getId().longValue() ? 2 : 3;
			shiftsReserve[idx] += rc.getEmployeeCount() - rc.getPlanVenuesCount();
			mapReserve.put(key, shiftsReserve);
			shiftsAll[idx] += rc.getEmployeeCount();
			mapAll.put(key, shiftsAll);
		 }
		 for (Map.Entry<String, int[]> en : mapReserve.entrySet()) {
			int[] vR = en.getValue();
			int[] vA = mapAll.get(en.getKey());
			String v = "";
			for (int k = 0; k < vR.length; k++) {
			   v += (v.isEmpty() ? "" : " ") + (vA[k] < 0 ? 0 : vA[k]) + "/" + (vR[k] < 0 ? 0 : vR[k]);
			}
			reserveCounts.put(en.getKey(), v);
		 }
	  }
   }

   public Map<String, String> getReserveCounts() {
	  return reserveCounts;
   }
}