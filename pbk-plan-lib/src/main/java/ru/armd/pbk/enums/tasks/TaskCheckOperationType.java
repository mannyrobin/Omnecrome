package ru.armd.pbk.enums.tasks;

/**
 * Справочник типов операиций проверок.
 */
public enum TaskCheckOperationType {

   TICKET_SALE(1L, "Продажа билетов"),
   TICKET_EXEMPT(2L, "Изъятие билетов"),
   PASSENGER_PLANT(3L, "Высажено пассажиров"),
   PASSENGER_OVD(4L, "Досталвено в ОВД"),
   DRIVER_RAPORT(5L, "Рапорты на водителей");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private TaskCheckOperationType(Long id, String name) {
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
   public static TaskCheckOperationType getEnumById(Long id) {
	  for (TaskCheckOperationType value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }

}
