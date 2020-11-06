package ru.armd.pbk.enums.tasks;

/**
 * Справочник статусов заданий.
 */
public enum TaskStates {

   CREATED(1L, "Создано"),
   IN_PROGRESS(2L, "В работе"),
   DONE(3L, "Выполнено"),
   CANCELED(4L, "Отменено"),
   CLOSED(5L, "Закрыто"),
   IN_RESERVE(6L, "В резерве");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private TaskStates(Long id, String name) {
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
   public static TaskStates getEnumById(Long id) {
	  for (TaskStates value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}
