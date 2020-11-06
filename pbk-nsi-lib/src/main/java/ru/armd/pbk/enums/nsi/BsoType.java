package ru.armd.pbk.enums.nsi;

/**
 * Справчоник типов БСО.
 */
public enum BsoType {

   TASK(1L, "Наряд задание"),
   ACT(2L, "Акт изъятия проездных документов");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private BsoType(Long id, String name) {
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
   public static BsoType getEnumById(Long id) {
	  for (BsoType value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }
}