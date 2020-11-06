package ru.armd.pbk.enums.core;

/**
 * Состояния заданий.
 */
public enum TaskStates {

   CREATED(1L, "CREATED", "Создано", "Создано"),
   IN_PROGRESS(2L, "IN_PROGRESS", "В работе", "В работе"),
   DONE(3L, "DONE", "Выполнено", "Выполнено"),
   CANCELED(4L, "CANCELED", "Отменено", "Отменено"),
   IN_RESERVE(6L, "IN_RESERVE", "В резерве", "В резерве");

   private Long id;
   private String code;
   private String name;
   private String description;

   private TaskStates(Long id, String code, String name, String description) {
	  this.id = id;
	  this.code = code;
	  this.name = name;
	  this.description = description;
   }

   public Long getId() {
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

}
