package ru.armd.pbk.dto.tasks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.domain.tasks.TaskRoute;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO для задания.
 */
public class TaskDTO
	extends BaseDTO {

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date taskDate;
   private Long bsoId;
   private Long deptId;
   private Long taskTypeId;
   private Long stateId;
   private Long planScheduleId;
   private Long changePlanScheduleId;
   private Long planVenueId;
   private List<Long> routes;
   private Long causeShiftId;
   private Date workStartTime;
   private Date workEndTime;
   private Date breakStartTime;
   private Date breakEndTime;
   @Length(max = StringUtils.XX_SHORT_INPUT_SIZE, message = "Число символов в поле \"Особые отметки\" не должно превышать "
	   + StringUtils.XX_SHORT_INPUT_SIZE + " символов.")
   private String specialMark;
   @Length(max = StringUtils.XX_SHORT_INPUT_SIZE, message = "Число символов в поле \"Особое задание\" не должно превышать "
	   + StringUtils.XX_SHORT_INPUT_SIZE + " символов.")
   private String specialTask;
   @Min(value = 0, message = "Фактическое количество часов не должно быть отрицательным.")
   @Max(value = 24, message = "Фактическое количество часов не должно превышать 24 часа.")
   private BigDecimal factHours;
   private String cancelReason;
   private Long shiftId;
   private List<TaskRoute> districtRoutes;
   private Long districtId;

   public Date getTaskDate() {
	  return taskDate;
   }

   public void setTaskDate(Date taskDate) {
	  this.taskDate = taskDate;
   }

   public Long getBsoId() {
	  return bsoId;
   }

   public void setBsoId(Long bsoId) {
	  this.bsoId = bsoId;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Long getTaskTypeId() {
	  return taskTypeId;
   }

   public void setTaskTypeId(Long taskTypeId) {
	  this.taskTypeId = taskTypeId;
   }

   public Long getPlanScheduleId() {
	  return planScheduleId;
   }

   public void setPlanScheduleId(Long planScheduleId) {
	  this.planScheduleId = planScheduleId;
   }

   public Long getChangePlanScheduleId() {
	  return changePlanScheduleId;
   }

   public void setChangePlanScheduleId(Long changePlanScheduleId) {
	  this.changePlanScheduleId = changePlanScheduleId;
   }

   public Long getPlanVenueId() {
	  return planVenueId;
   }

   public void setPlanVenueId(Long planVenueId) {
	  this.planVenueId = planVenueId;
   }

   public Long getStateId() {
	  return stateId;
   }

   public void setStateId(Long stateId) {
	  this.stateId = stateId;
   }

   public List<Long> getRoutes() {
	  return routes;
   }

   public void setRoutes(List<Long> routes) {
	  this.routes = routes;
   }

   public Long getCauseShiftId() {
	  return causeShiftId;
   }

   public void setCauseShiftId(Long causeShiftId) {
	  this.causeShiftId = causeShiftId;
   }

   public Date getWorkStartTime() {
	  return workStartTime;
   }

   public void setWorkStartTime(Date workStartTime) {
	  this.workStartTime = workStartTime;
   }

   public Date getWorkEndTime() {
	  return workEndTime;
   }

   public void setWorkEndTime(Date workEndTime) {
	  this.workEndTime = workEndTime;
   }

   public Date getBreakStartTime() {
	  return breakStartTime;
   }

   public void setBreakStartTime(Date breakStartTime) {
	  this.breakStartTime = breakStartTime;
   }

   public Date getBreakEndTime() {
	  return breakEndTime;
   }

   public void setBreakEndTime(Date breakEndTime) {
	  this.breakEndTime = breakEndTime;
   }

   public String getSpecialMark() {
	  return specialMark;
   }

   public void setSpecialMark(String specialMark) {
	  this.specialMark = specialMark;
   }

   public String getSpecialTask() {
	  return specialTask;
   }

   public void setSpecialTask(String specialTask) {
	  this.specialTask = specialTask;
   }

   public BigDecimal getFactHours() {
	  return factHours;
   }

   public void setFactHours(BigDecimal factHours) {
	  this.factHours = factHours;
   }

   public String getCancelReason() {
	  return cancelReason;
   }

   public void setCancelReason(String cancelReason) {
	  this.cancelReason = cancelReason;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public List<TaskRoute> getDistrictRoutes() {
	  return districtRoutes;
   }

   public void setDistrictRoutes(List<TaskRoute> districtRoutes) {
	  this.districtRoutes = districtRoutes;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}