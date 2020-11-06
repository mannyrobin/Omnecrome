package ru.armd.pbk.views.tasks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * View БСК из АСКП.
 */
public class TaskContactlessCardView
	extends BaseGridView {

   private Long id;
   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date checkTime;
   private String routeNumber;
   private String moveCode;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getCheckTime() {
	  return checkTime;
   }

   public void setCheckTime(Date checkTime) {
	  this.checkTime = checkTime;
   }

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
}
