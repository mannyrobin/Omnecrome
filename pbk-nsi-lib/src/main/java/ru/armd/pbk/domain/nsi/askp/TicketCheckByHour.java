package ru.armd.pbk.domain.nsi.askp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен проверки билетов по времени.
 */
public class TicketCheckByHour
	extends BaseDomain {

   private Date workDate;
   private Integer hour;
   private String askpRouteCode;
   private String moveCode;
   private Integer askpChecksCount;
   private Long routeId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getHour() {
	  return hour;
   }

   public void setHour(Integer hour) {
	  this.hour = hour;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Integer getAskpChecksCount() {
	  return askpChecksCount;
   }

   public void setAskpChecksCount(Integer askpChecksCount) {
	  this.askpChecksCount = askpChecksCount;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

}
