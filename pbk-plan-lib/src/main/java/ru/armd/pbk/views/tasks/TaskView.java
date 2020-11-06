package ru.armd.pbk.views.tasks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.views.nsi.route.RouteView;

import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * View для заданий.
 */
public class TaskView
	extends BaseGridView {

   private Long id;

   @JsonSerialize(using = TaskDateSerializer.class)
   private Date taskDate;
   private String bsoNumber;
   private String taskState;
   private String employeeName;
   private String personnelNumber;
   private String changeEmployeeName;
   private String departmentName;
   private String shiftName;
   private String changeShiftName;
   private String planVenueName;
   private List<RouteView> routes;
   private String isEquals;
   private String taskTypeCod;
   private String countyName;
   private String districtName;
   private String scanStreamId;
   private String scanFileName;

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

      public String getPersonnelNumber() {
		  return personnelNumber;
	   }

	   public void setPersonnelNumber(String personnelNumber) {
		  this.personnelNumber = personnelNumber;
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

   public String getDistrictName() {
	  return districtName;
   }

   public void setDistrictName(String districtName) {
	  this.districtName = districtName;
   }

   public String getScanStreamId() {
	  return scanStreamId;
   }

   public void setScanStreamId(String scanStreamId) {
	  this.scanStreamId = scanStreamId;
   }

   public String getScanFileName() {
	  return scanFileName;
   }

   public void setScanFileName(String scanFileName) {
	  this.scanFileName = scanFileName;
   }

}