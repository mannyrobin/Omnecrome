package ru.armd.pbk.views.tasks;

import java.util.List;

/**
 * Created by dmitry_kurkin on 07.07.2016.
 */
public class TaskRoutesReportView {

   private List<String> routeNumbers;

   /**
	* Конструктор.
	*
	* @param routeNumbers номера маршрутов.
	*/
   public TaskRoutesReportView(List<String> routeNumbers) {
	  this.routeNumbers = routeNumbers;
   }

   public List<String> getRouteNumbers() {
	  return routeNumbers;
   }

   public void setRouteNumbers(List<String> routeNumbers) {
	  this.routeNumbers = routeNumbers;
   }
}
