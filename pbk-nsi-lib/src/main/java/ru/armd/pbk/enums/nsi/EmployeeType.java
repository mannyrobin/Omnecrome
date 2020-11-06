package ru.armd.pbk.enums.nsi;

/**
 * Справчоник типов сотрудников.
 */
public enum EmployeeType {

   EASU_FHD(1L, "Пользователь из внешней системы ЕАСУ ФХД"),
   PBK(2L, "Внутрений пользоватлеь ПБК");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private EmployeeType(Long id, String name) {
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
   public static EmployeeType getEnumById(Long id) {
	  for (EmployeeType value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return null;
   }


}
