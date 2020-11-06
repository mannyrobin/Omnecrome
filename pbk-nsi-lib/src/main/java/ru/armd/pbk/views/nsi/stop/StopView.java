package ru.armd.pbk.views.nsi.stop;

import ru.armd.pbk.core.views.BaseGridView;

public class StopView
	extends BaseGridView {

   private Long id;
   private String name;
   private String gmStopName;
   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getGmStopName() {
	  return gmStopName;
   }

   public void setGmStopName(String gmStopName) {
	  this.gmStopName = gmStopName;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

}
