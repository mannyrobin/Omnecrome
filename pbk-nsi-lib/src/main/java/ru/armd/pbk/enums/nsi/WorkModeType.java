package ru.armd.pbk.enums.nsi;

/**
 * Варианты расписания сотрудника.
 */
public enum WorkModeType {
   HOLIDAY(1L, "Выходной день"),
   DAY(2L, "Дневная"),
   NIGHT(3L, "Ночная"),
   NOT_WORK(4L, "Не работает");

   private Long id;
   private String name;

   private WorkModeType(Long id, String name) {
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
   public static WorkModeType getEnumById(Long id) {
	  for (WorkModeType value : values()) {
		 if (value.getId().equals(id)) {
			return value;
		 }
	  }
	  return null;
   }
}
