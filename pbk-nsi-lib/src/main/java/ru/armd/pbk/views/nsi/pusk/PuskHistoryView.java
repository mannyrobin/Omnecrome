package ru.armd.pbk.views.nsi.pusk;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории ПУсКа.
 */
public class PuskHistoryView
	extends BaseVersionView {

   private String puskNumber;
   private String puskModel;
   private String description;

   public String getPuskNumber() {
	  return puskNumber;
   }

   public void setPuskNumber(String puskNumber) {
	  this.puskNumber = puskNumber;
   }

   public String getPuskModel() {
	  return puskModel;
   }

   public void setPuskModel(String puskModel) {
	  this.puskModel = puskModel;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }
}
