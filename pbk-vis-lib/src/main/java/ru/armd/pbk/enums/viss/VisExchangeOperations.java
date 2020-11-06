package ru.armd.pbk.enums.viss;

/**
 * Возможные операции обмена.
 */
public enum VisExchangeOperations {

   IMPORT(1L, "Импорт"),
   EXPORT(2L, "Экспорт");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private VisExchangeOperations(Long id, String name) {
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
   public static VisExchangeOperations getEnumById(Long id) {
	  for (VisExchangeOperations value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }


}
