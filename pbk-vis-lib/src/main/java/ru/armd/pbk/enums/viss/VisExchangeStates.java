package ru.armd.pbk.enums.viss;

/**
 * Статусы журнальной записи обмена.
 */
public enum VisExchangeStates {

   TO_PROCESS(1L, "К обработке"),
   IN_PROCESS(2L, "В обработке"),
   SUCCESS(3L, "Успешно обработан"),
   FAIL(4L, "Ошибка обработки");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private VisExchangeStates(Long id, String name) {
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
   public static VisExchangeStates getEnumById(Long id) {
	  for (VisExchangeStates value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }

}
