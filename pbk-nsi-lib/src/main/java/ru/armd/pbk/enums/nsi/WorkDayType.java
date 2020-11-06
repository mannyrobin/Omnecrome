package ru.armd.pbk.enums.nsi;

/**
 * Справочник видов дней календаря.
 */
public enum WorkDayType {

   WORK_DAY(1, "Рабочий день"),
   DAY_OFF(2, "Выходной день"),
   HOLIDAY(3, "Праздничный день");

   private Integer id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private WorkDayType(Integer id, String name) {
	  this.id = id;
	  this.name = name;
   }

   public Integer getId() {
	  return id;
   }

   public String getName() {
	  return name;
   }

   /**
	* Получение элемента по id.
	*
	* @param id id.
	* @return
	*/
   public static WorkDayType getEnumById(Integer id) {
	  for (WorkDayType value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}