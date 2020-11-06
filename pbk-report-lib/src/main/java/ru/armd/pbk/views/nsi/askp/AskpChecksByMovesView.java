package ru.armd.pbk.views.nsi.askp;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.List;
import java.util.Map;

/**
 * View для данных из АСКП.
 */
public class AskpChecksByMovesView
	extends BaseGridView {

   /**
	* День.
	*/
   public static class Day {
	  private String day;
	  private boolean holiday;

	  /**
	   * Конструктор.
	   *
	   * @param day     день
	   * @param holiday признак выходного
	   */
	  public Day(String day, boolean holiday) {
		 this.day = day;
		 this.holiday = holiday;
	  }

	  public String getDay() {
		 return day;
	  }

	  public void setDay(String day) {
		 this.day = day;
	  }

	  public boolean isHoliday() {
		 return holiday;
	  }

	  public void setHoliday(boolean holiday) {
		 this.holiday = holiday;
	  }
   }

   private String routeNumber;
   private String moveCode;
   private Integer sum;
   private Integer count;
   private Integer avg;
   private Integer min;
   private Integer max;
   private List<Day> days;
   private Map<String, Integer> extcols;
   private Map<String, AskpTicketChecksByMoveDetailView> extcolDetails;
   private Long routeId;
   private String routeName;

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Integer getSum() {
	  return sum;
   }

   public void setSum(Integer sum) {
	  this.sum = sum;
   }

   public Integer getCount() {
	  return count;
   }

   public void setCount(Integer count) {
	  this.count = count;
   }

   public Integer getAvg() {
	  return avg;
   }

   public void setAvg(Integer avg) {
	  this.avg = avg;
   }

   public Integer getMin() {
	  return min;
   }

   public void setMin(Integer min) {
	  this.min = min;
   }

   public Integer getMax() {
	  return max;
   }

   public void setMax(Integer max) {
	  this.max = max;
   }

   public List<Day> getDays() {
	  return days;
   }

   public void setDays(List<Day> days) {
	  this.days = days;
   }

   public Map<String, Integer> getExtcols() {
	  return extcols;
   }

   public void setExtcols(Map<String, Integer> days) {
	  this.extcols = days;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getRouteName() {
	  return routeName;
   }

   public void setRouteName(String routeName) {
	  this.routeName = routeName;
   }

   public Map<String, AskpTicketChecksByMoveDetailView> getExtcolDetails() {
	  return extcolDetails;
   }

   public void setExtcolDetails(Map<String, AskpTicketChecksByMoveDetailView> extcolDetails) {
	  this.extcolDetails = extcolDetails;
   }

}
