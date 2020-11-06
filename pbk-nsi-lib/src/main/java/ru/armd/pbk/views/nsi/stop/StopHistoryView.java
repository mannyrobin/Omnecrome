package ru.armd.pbk.views.nsi.stop;

import ru.armd.pbk.core.views.BaseVersionView;

public class StopHistoryView
	extends BaseVersionView {

   private String name;

   private Long gmStopId;
   private String asduStopId;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getGmStopId() {
	  return gmStopId;
   }

   public void setGmStopId(Long gmStopId) {
	  this.gmStopId = gmStopId;
   }

   public String getAsduStopId() {
	  return asduStopId;
   }

   public void setAsduStopId(String asduStopId) {
	  this.asduStopId = asduStopId;
   }
}
