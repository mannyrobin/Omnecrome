package ru.armd.pbk.domain.nsi.asdu;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class AsduRouteMoveTrip
	extends BaseDomain {

   private String depotNumber;
   private Long grafic;
   private String mrCode;
   private Date date;

   public String getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(String depotNumber) {
	  this.depotNumber = depotNumber;
   }

   public Long getGrafic() {
	  return grafic;
   }

   public void setGrafic(Long grafic) {
	  this.grafic = grafic;
   }

   public String getMrCode() {
	  return mrCode;
   }

   public void setMrCode(String mrCode) {
	  this.mrCode = mrCode;
   }

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

}
