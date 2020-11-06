package ru.armd.pbk.enums.core;

import ru.armd.pbk.core.views.SelectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Множество состояний БСО.
 */
public enum BsoStates {

   /*
   PRINTED(1, "Распечатан"),
   NOT_PRINTED(2, "Не распечатан"),
   */
   TRASHED(3, "Брак"),
   NOT_TRASHED(4, "Исправен"),
   BOUND(5, "Закреплён"),
   NOT_BOUND(6, "Не закреплён"),
   USED(7, "Использован"),
   NOT_USED(8, "Не использован");

   private Integer id;
   private String name;

   private BsoStates(Integer id, String name) {
	  this.id = id;
	  this.name = name;
   }

   public Integer getId() {
	  return id;
   }

   public String getName() {
	  return name;
   }

   public static List<SelectItem> getSelItems() {
	  List<SelectItem> result = new ArrayList<>();
	  for (BsoStates bsoState : BsoStates.values()) {
		 result.add(new SelectItem(new Long(bsoState.getId()), bsoState.getName()));
	  }
	  return result;
   }
}
