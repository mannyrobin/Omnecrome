package ru.armd.pbk.views.plans.schedules;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * View расписания плана отдела.
 */
public class EmployeePlanScheduleView
	extends BaseGridView {

   private Long id;
   private String name;
   private String surname;
   private String patronumic;
   private String personalNumber;
   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date fireDate;
   private Map<String, ShiftInfoView> shifts;
   private String shortCountyName;

   public EmployeePlanScheduleView() {

   }

   public EmployeePlanScheduleView(EmployeePlanScheduleView elem) {
	  this.id = elem.id;
	  this.name = elem.name;
	  this.surname = elem.surname;
	  this.patronumic = elem.patronumic;
	  this.personalNumber = elem.personalNumber;
	  this.fireDate = elem.fireDate;
	  this.shortCountyName = elem.shortCountyName;
	  if (elem.shifts == null || elem.shifts.isEmpty()) {
		 this.shifts = new HashMap<>();
	  } else {
		 this.shifts.putAll(elem.shifts);
	  }
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public Map<String, ShiftInfoView> getShifts() {
	  return shifts;
   }

   public void setShifts(Map<String, ShiftInfoView> shifts) {
	  this.shifts = shifts;
   }

   public Date getFireDate() {
	  return fireDate;
   }

   public void setFireDate(Date fireDate) {
	  this.fireDate = fireDate;
   }

   public String getShortCountyName() {
	  return shortCountyName;
   }

   public void setShortCountyName(String shortCountyName) {
	  this.shortCountyName = shortCountyName;
   }

}
