package ru.armd.pbk.enums.nsi;

/**
 * Справочник типов билетов.
 */
public enum TicketType {

   PAID(1L, "Платные билеты"),
   FREE(2L, "Бесплатные билеты");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private TicketType(Long id, String name) {
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
   public static TicketType getEnumById(Long id) {
	  for (TicketType value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}
