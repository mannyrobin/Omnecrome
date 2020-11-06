package ru.armd.pbk.enums.nsi;

/**
 * Множество типов билетов.
 *
 * @author nikita_lobanov
 */
public enum TicketTypes {

   PAID(1, "PAID", "Платные билеты", "Платные билеты"),
   FREE(2, "FREE", "Бесплатные билеты", "Бесплатные билеты");

   private Integer id;
   private String code;
   private String name;
   private String description;

   private TicketTypes(Integer id, String code, String name, String description) {
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
	* Получить название типа по id.
	*
	* @param id id
	* @return название типа
	*/
   public static String getNameById(Integer id) {
	  if (id == null) {
		 return "";
	  }

	  for (TicketTypes ticketType : TicketTypes.values()) {
		 if (ticketType.getId().equals(id)) {
			return ticketType.getName();
		 }
	  }

	  return "";
   }
}
