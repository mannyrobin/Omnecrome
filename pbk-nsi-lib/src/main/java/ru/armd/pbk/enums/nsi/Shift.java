package ru.armd.pbk.enums.nsi;

/**
 * Справочник смен.
 * - Выходной;
 * - Дневная;
 * - Ночная;
 * - Отпуск;
 * - Больничный;
 * - Первая;
 * - Вторая.
 */
public enum Shift {

   HOLIDAY(1L, "Выходной", false),
   DAY(2L, "Дневная", true),
   NIGHT(3L, "Ночная", true),
   VACATION(4L, "Отпуск", false),
   SICK(5L, "Больничный", false),
   I(6L, "Первая", true),
   II(7L, "Вторая", true),
   OTHER(8L, "Иное", true),
   III(14L, "Третья", true),
   LINE1(15L, "Линия1", true),
   LINE2(16L, "Линия2", true);

   private Long id;
   private String name;
   private boolean isWorkDay;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private Shift(Long id, String name, boolean isWorkDay) {
	  this.id = id;
	  this.name = name;
	  this.isWorkDay = isWorkDay;
   }

   public Long getId() {
	  return id;
   }

   public String getName() {
	  return name;
   }

   public boolean isWorkDay() {
	  return isWorkDay;
   }

   /**
	* Получение элемента по id.
	*
	* @param id id.
	* @return
	*/
   public static Shift getEnumById(Long id) {
	  for (Shift value : values()) {
		 if (value.getId().equals(id)) {
			return value;
		 }
	  }
	  return null;
   }

}
