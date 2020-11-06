package ru.armd.pbk.enums.nsi;

import ru.armd.pbk.core.views.SelectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Типы отсутсвий сотрудников.
 */
public enum EmployeeAbsenceTypes {

   HOSPITAL(1L, "Больничный"),
   VACATION(2L, "Отпуск");

   private Long id;
   private String name;

   private EmployeeAbsenceTypes(Long id, String name) {
	  this.setId(id);
	  this.setName(name);
   }

   public Long getId() {
	  return id;
   }

   private void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   private void setName(String name) {
	  this.name = name;
   }

   /**
	* Получение элемента по id.
	*
	* @param id id.
	* @return
	*/
   public static EmployeeAbsenceTypes getEnumById(Long id) {
	  for (EmployeeAbsenceTypes value : values()) {
		 if (value.getId().equals(id)) {
			return value;
		 }
	  }
	  return null;
   }

   /**
	* Получает список элементов для отображения в выпадающем списке.
	*
	* @return список элементов
	*/
   public static List<SelectItem> getSelectList() {
	  List<SelectItem> result = new ArrayList<>();
	  for (EmployeeAbsenceTypes value : values()) {
		 result.add(new SelectItem(value.getId(), value.getName()));
	  }
	  return result;
   }

   /**
	* Получает список элементов для отображения в выпадающем списке дневных смен (первая или вторая).
	*
	* @return список элементов
	*/
   public static List<SelectItem> getDaySelectList() {
	  List<SelectItem> result = new ArrayList<>();
	  for (EmployeeAbsenceTypes value : values()) {
		 if (!value.getId().equals(0L)) {
			result.add(new SelectItem(value.getId(), value.getName()));
		 }
	  }
	  return result;
   }
}