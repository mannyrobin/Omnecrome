package ru.armd.pbk.enums.core;

import ru.armd.pbk.core.views.SelectItem;

import java.util.ArrayList;
import java.util.List;

public enum TaskRouteType {

   TASK(0L, "Основные маршруты, вошедшие в наряд-задание"),
   TO_BASIC(2L, "Сопутствующие маршруты от места встречи к основным"),
   FROM_BASIC(1L, "Сопутствующие маршруты от основных к СДиК"),
   BSK(3L, "Маршруты с проходов по БСК");

   private Long id;
   private String name;

   TaskRouteType(Long id, String name) {
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
	  for (TaskRouteType taskRouteType : TaskRouteType.values()) {
		 result.add(new SelectItem(new Long(taskRouteType.getId()), taskRouteType.getName()));
	  }
	  return result;
   }

}
