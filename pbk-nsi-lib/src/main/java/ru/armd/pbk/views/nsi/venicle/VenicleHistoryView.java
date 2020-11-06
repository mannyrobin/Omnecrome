package ru.armd.pbk.views.nsi.venicle;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории ТС.
 */
public class VenicleHistoryView
	extends BaseVersionView {

   private String depoNumber;

   private String asduVenicleId;

   private Long tsModelId;

   private String stateNumber;

   private String vinNumber;

   private String asduDepotId;

   public String getDepoNumber() {
	  return depoNumber;
   }

   public void setDepoNumber(String depoNumber) {
	  this.depoNumber = depoNumber;
   }

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
   }

   public Long getTsModelId() {
	  return tsModelId;
   }

   public void setTsModelId(Long tsModelId) {
	  this.tsModelId = tsModelId;
   }

   public String getStateNumber() {
	  return stateNumber;
   }

   public void setStateNumber(String stateNumber) {
	  this.stateNumber = stateNumber;
   }

   public String getVinNumber() {
	  return vinNumber;
   }

   public void setVinNumber(String vinNumber) {
	  this.vinNumber = vinNumber;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }
}
