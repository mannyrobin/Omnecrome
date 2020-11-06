package ru.armd.pbk.enums.core;

import ru.armd.pbk.core.views.SelectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Множество полов.
 */
public enum SexTypes {

   UNKNOWN(1, "UNKNOWN", "Не определен", "Не определен"),
   MALE(2, "MALE", "Мужской", "Мужской"),
   FEMALE(2, "FEMALE", "Женский", "Женский");

   private Integer id;
   private String code;
   private String name;
   private String description;

   private SexTypes(Integer id, String code, String name, String description) {
	  this.id = id;
	  this.code = code;
	  this.name = name;
	  this.description = description;
   }

   public Integer getId() {
	  return id;
   }

   public String getCode() {
	  return code;
   }

   public String getName() {
	  return name;
   }

   public String getDescription() {
	  return description;
   }

   /**
	* Получение выпадающего списка.
	*
	* @return список.
	*/
   public static List<SelectItem> getSelItems() {
	  List<SelectItem> result = new ArrayList<>();
	  for (SexTypes sexType : SexTypes.values()) {
		 result.add(new SelectItem(new Long(sexType.getId()), sexType.getName()));
	  }
	  return result;
   }
}
