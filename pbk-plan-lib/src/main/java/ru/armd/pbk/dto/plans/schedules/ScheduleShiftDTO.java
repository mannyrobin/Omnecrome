package ru.armd.pbk.dto.plans.schedules;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;
import ru.armd.pbk.utils.validation.IScheduleValidator;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Дто смены расписания сотрудника.
 */
@IScheduleValidator
public class ScheduleShiftDTO
	extends BaseDTO {

   @NotNull(message = "Смена должна быть выбрана.")
   private Long shiftId;
   private Long shiftModeId;
   @NotNull(message = "Подразделение должно быть выбрано.")
   private Long deptId;
   @NotNull(message = "Сотрудник должен быть выбран.")
   private Long employeeId;
   @NotNull(message = "Дата должна быть выбрана.")
   private Date planDate;

   private Boolean isForCurrentDay = true;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date dateFrom;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date dateTo;

   private Integer settingShiftDay;

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Long getShiftModeId() {
	  return shiftModeId;
   }

   public void setShiftModeId(Long shiftModeId) {
	  this.shiftModeId = shiftModeId;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Date getDateTo() {
	  return dateTo;
   }

   public void setDateTo(Date dateTo) {
	  this.dateTo = dateTo;
   }

   public Date getDateFrom() {
	  return dateFrom;
   }

   public void setDateFrom(Date dateFrom) {
	  this.dateFrom = dateFrom;
   }

   public Boolean getIsForCurrentDay() {
	  return isForCurrentDay;
   }

   public void setIsForCurrentDay(Boolean isForCurrentDay) {
	  this.isForCurrentDay = isForCurrentDay;
   }

   public Integer getSettingShiftDay() {
	  return settingShiftDay;
   }

   public void setSettingShiftDay(Integer settingShiftDay) {
	  this.settingShiftDay = settingShiftDay;
   }

}
