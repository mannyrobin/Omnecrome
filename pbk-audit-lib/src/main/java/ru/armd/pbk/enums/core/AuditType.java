package ru.armd.pbk.enums.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Базовый аудит.
 */
public class AuditType {

   public static final AuditType SYSTEM = new AuditType(1L, "Система");
   public static final AuditType NOT_DEFINED = new AuditType(2L, "Не определен тип");
   public static final AuditType EXCEPTION = new AuditType(3L, "Исключение");
   public static final AuditType LOG_IN = new AuditType(4L, "Вход в систему");
   public static final AuditType LOG_OUT = new AuditType(5L, "Выход из системы");
   public static final AuditType LDAP = new AuditType(6L, "LDAP");
   public static final AuditType LOCK = new AuditType(7L, "Блокировка пользователя");
   public static final AuditType UNLOCK = new AuditType(8L, "Разблокировка пользователя");

   private static List<AuditType> values = null;

   private Long id;
   private String name;

   protected AuditType(Long id, String name) {
	  this.id = id;
	  this.name = name;

	  getValues().add(this);
   }

   public Long getId() {
	  return id;
   }

   public String getName() {
	  return name;
   }

   /**
	* Получить список значений аудита.
	*
	* @return список значений аудита.
	*/
   public static List<AuditType> getValues() {
	  if (values == null) {
		 values = new ArrayList<>();
	  }
	  return values;
   }

   /**
	* Получение типа по id.
	*
	* @param id id.
	* @return
	*/
   public static AuditType getEnumById(Long id) {
	  for (AuditType value : getValues()) {
		 if (value.getId() == id) {
			return value;
		 }
	  }
	  return NOT_DEFINED;
   }
}
