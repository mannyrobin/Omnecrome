package ru.armd.pbk.enums.core;

import ru.armd.pbk.core.views.SelectItem;

import java.util.ArrayList;
import java.util.List;

public enum TaskCheckType {

   NO_VIDEO(0L, "НЗ без видеозаписей"),
   NO_BSK(1L, "НЗ без данных БСК"),
   NOT_CLOSED(2L, "Незакрытые НЗ");

   private Long id;
   private String name;

   TaskCheckType(Long id, String name) {
	  this.id = id;
	  this.name = name;
   }

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

   public static List<SelectItem> getSelItems() {
	  List<SelectItem> result = new ArrayList<>();
	  for (TaskCheckType taskCheckType : TaskCheckType.values()) {
		 result.add(new SelectItem(taskCheckType.getId(), taskCheckType.getName()));
	  }
	  return result;
   }

}
