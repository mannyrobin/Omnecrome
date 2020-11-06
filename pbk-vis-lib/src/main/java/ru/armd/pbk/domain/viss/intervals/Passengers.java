package ru.armd.pbk.domain.viss.intervals;

import java.util.Date;

/**
 * Класс Passengers.
 */
public class Passengers {

   private Long id;
   private Date date;
   private String routeCode;
   private Integer askpChecksCount;
   private Integer asmppAvgCount;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public String getRouteCode() {
	  return routeCode;
   }

   public void setRouteCode(String routeCode) {
	  this.routeCode = routeCode;
   }

   public Integer getAskpChecksCount() {
	  return askpChecksCount;
   }

   public void setAskpChecksCount(Integer askpChecksCount) {
	  this.askpChecksCount = askpChecksCount;
   }

   public Integer getAsmppAvgCount() {
	  return asmppAvgCount;
   }

   public void setAsmppAvgCount(Integer asmppAvgCount) {
	  this.asmppAvgCount = asmppAvgCount;
   }
}
