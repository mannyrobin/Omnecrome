package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * Created by Yakov Volkov.
 */
public class So20CheckPlanView
	extends SoView {

   private String routeNumber;
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date checkDate;
   private String shift;
   private String moc;

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Date getCheckDate() {
	  return checkDate;
   }

   public void setCheckDate(Date checkDate) {
	  this.checkDate = checkDate;
   }

   public String getShift() {
	  return shift;
   }

   public void setShift(String shift) {
	  this.shift = shift;
   }

   public String getMoc() {
	  return moc;
   }

   public void setMoc(String moc) {
	  this.moc = moc;
   }
}
