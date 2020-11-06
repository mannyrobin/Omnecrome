package ru.armd.pbk.views.report;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Количество перевезенных пассажиров по маршрутам".
 */
public class So7View
	extends SoView {

   private String branch;
   private String county;
   private String route;
   private Integer curPassengerCount;
   private Integer prevPassengerCount;

   public String getBranch() {
	  return branch;
   }

   public void setBranch(String branch) {
	  this.branch = branch;
   }

   public String getCounty() {
	  return county;
   }

   public void setCounty(String county) {
	  this.county = county;
   }

   public String getRoute() {
	  return route;
   }

   public void setRoute(String route) {
	  this.route = route;
   }

   public Integer getCurPassengerCount() {
	  return curPassengerCount;
   }

   public void setCurPassengerCount(Integer curPassengerCount) {
	  this.curPassengerCount = curPassengerCount;
   }

   public Integer getPrevPassengerCount() {
	  return prevPassengerCount;
   }

   public void setPrevPassengerCount(Integer prevPassengerCount) {
	  this.prevPassengerCount = prevPassengerCount;
   }
}
