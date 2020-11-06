package ru.armd.pbk.enums.core;

/**
 * Операции над объектом логирования.
 */
public enum AuditObjOperation {

   OTHER(1L, "Другое"),
   SELECT(2L, "Выборка"),
   INSERT(3L, "Создание"),
   UPDATE(4L, "Обновление"),
   DELETE(5L, "Удаление"),
   EXECUTE(6L, "Исполнение"),
   EXCHANGE(7L, "Информационный обмен"),
   IMPORT(8L, "Импорт"),
   EXPORT(9L, "Экспорт"),
   SEND_CMD(10L, "Отправка команды");

   private Long id;
   private String name;

   /**
	* Конструктор.
	*
	* @param id   id.
	* @param name name.
	*/
   private AuditObjOperation(Long id, String name) {
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
   public static AuditObjOperation getEnumById(Long id) {
	  for (AuditObjOperation value : values()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return OTHER;
   }
}
