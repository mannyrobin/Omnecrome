package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSeparatedListDateSerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yakov Volkov.
 */
public class So20MergedCheckPlanView
	extends SoView {

   private String routeNumber;
   @JsonSerialize(using = DotSeparatedListDateSerializer.class)
   private List<Date> checkDates = new ArrayList<>();
   private List<String> shifts = new ArrayList<>();
   private List<String> mocs = new ArrayList<>();

   public String getType() {
	  return "So20MergedCheckPlan";
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public List<Date> getCheckDates() {
	  return checkDates;
   }

   public void setCheckDates(List<Date> checkDates) {
	  this.checkDates = checkDates;
   }

   public List<String> getShifts() {
	  return shifts;
   }

   public void setShifts(List<String> shifts) {
	  this.shifts = shifts;
   }

   public List<String> getMocs() {
	  return mocs;
   }

   public void setMocs(List<String> mocs) {
	  this.mocs = mocs;
   }
}
