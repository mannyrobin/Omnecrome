package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Коды управляющих воздействий".
 */
public class GtfsImpactCode
	extends BaseDomain {

   private Date workDate;
   private Integer impactId;
   private String impactCode;
   private String impactName;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getImpactId() {
	  return impactId;
   }

   public void setImpactId(Integer impactId) {
	  this.impactId = impactId;
   }

   public String getImpactCode() {
	  return impactCode;
   }

   public void setImpactCode(String impactCode) {
	  this.impactCode = impactCode;
   }

   public String getImpactName() {
	  return impactName;
   }

   public void setImpactName(String impactName) {
	  this.impactName = impactName;
   }

}
