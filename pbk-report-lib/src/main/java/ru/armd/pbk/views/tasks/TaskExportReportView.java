package ru.armd.pbk.views.tasks;

import ru.armd.pbk.core.views.IReportView;
import ru.armd.pbk.views.nsi.route.RouteView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

public class TaskExportReportView
	implements IReportView {

   private Long id;
   private Date taskDate;
   private String bsoNumber;
   private String taskState;
   private String employeeName;
   private String changeEmployeeName;
   private String departmentName;
   private String shiftName;
   private String changeShiftName;
   private String planVenueName;
   private List<RouteView> routes;
   private String isEquals;
   private String taskTypeCod;
   private String countyName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getTaskDate() {
	  return taskDate;
   }

   public void setTaskDate(Date taskDate) {
	  this.taskDate = taskDate;
   }

   public String getBsoNumber() {
	  return bsoNumber;
   }

   public void setBsoNumber(String bsoNumber) {
	  this.bsoNumber = bsoNumber;
   }

   public String getTaskState() {
	  return taskState;
   }

   public void setTaskState(String taskState) {
	  this.taskState = taskState;
   }

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

   public String getShiftName() {
	  return shiftName;
   }

   public void setShiftName(String shiftName) {
	  this.shiftName = shiftName;
   }

   public String getPlanVenueName() {
	  return planVenueName;
   }

   public void setPlanVenueName(String planVenueName) {
	  this.planVenueName = planVenueName;
   }

   public String getChangeEmployeeName() {
	  return changeEmployeeName;
   }

   public void setChangeEmployeeName(String changeEmployeeName) {
	  this.changeEmployeeName = changeEmployeeName;
   }

   public String getChangeShiftName() {
	  return changeShiftName;
   }

   public void setChangeShiftName(String changeShiftName) {
	  this.changeShiftName = changeShiftName;
   }

   public List<RouteView> getRoutes() {
	  return routes;
   }

   public void setRoutes(List<RouteView> routes) {
	  this.routes = routes;
   }

   public String getIsEquals() {
	  return isEquals;
   }

   public void setIsEquals(String isEquals) {
	  this.isEquals = binaryToMarkSymbol(isEquals);
   }

   public String getTaskTypeCod() {
	  return taskTypeCod;
   }

   public void setTaskTypeCod(String taskTypeCod) {
	  this.taskTypeCod = taskTypeCod;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   @Override
   public List<Object> getReportList() {
	  DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	  List<Object> result = new LinkedList<Object>();
	  result.add(df.format(taskDate));
	  result.add(employeeName);
	  result.add(shiftName);
	  result.add(planVenueName);
	  result.add(departmentName);
	  result.add(bsoNumber);
	  result.add(taskState);
	  result.add(isEquals);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("Дата выполнения", "Сотрудник", "Смена",
		  "Место встречи", "Подразделение", "Номер БСО", "Статус",
		  "АСКП");
   }

}
