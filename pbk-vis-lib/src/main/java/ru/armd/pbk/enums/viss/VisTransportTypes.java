package ru.armd.pbk.enums.viss;

/**
 * Типы транспорта обмена.
 */
public enum VisTransportTypes {

   SOAP(1L, "SOAP"),
   FTP(2L, "FTP"),
   FILE_SYSTEM(3L, "FILE_SYSTEM"),
   HTTP(4L, "HTTP");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private VisTransportTypes(Long id, String name) {
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
   public static VisTransportTypes getEnumById(Long id) {
	  for (VisTransportTypes value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }


}
