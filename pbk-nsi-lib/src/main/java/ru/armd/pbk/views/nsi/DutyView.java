package ru.armd.pbk.views.nsi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;
import ru.armd.pbk.utils.json.DotSepratedTimeSerializer;

import java.util.Date;

/**
 * View наряда.
 */
public class DutyView
	extends BaseGridView {

   private Long id;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date workDate;

   private String easuFhdRouteCode;
   private String tsDepoNumber;
   private String driverPersonalNumber;
   private String moveCode;

   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date moveStartTime;

   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date moveEndTime;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public String getTsDepoNumber() {
	  return tsDepoNumber;
   }

   public void setTsDepoNumber(String tsDepoNumber) {
	  this.tsDepoNumber = tsDepoNumber;
   }

   public String getDriverPersonalNumber() {
	  return driverPersonalNumber;
   }

   public void setDriverPersonalNumber(String driverPersonalNumber) {
	  this.driverPersonalNumber = driverPersonalNumber;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getMoveStartTime() {
	  return moveStartTime;
   }

   public void setMoveStartTime(Date moveStartTime) {
	  this.moveStartTime = moveStartTime;
   }

   public Date getMoveEndTime() {
	  return moveEndTime;
   }

   public void setMoveEndTime(Date moveEndTime) {
	  this.moveEndTime = moveEndTime;
   }

}
