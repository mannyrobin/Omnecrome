package ru.armd.pbk.enums.tasks;

/**
 * Справочник типов заданий.
 */
public enum TaskType {

   PLAN_TASK(1L, "Задания на основе плановых заданий"),
   MANUAL(2L, "Задания вручную");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private TaskType(Long id, String name) {
	  this.id = id;
	  this.name = name;
   }

   public Long getId() {
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
   public static TaskType getEnumById(Long id) {
	  for (TaskType value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}
