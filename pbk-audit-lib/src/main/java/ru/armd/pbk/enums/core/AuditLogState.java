package ru.armd.pbk.enums.core;

/**
 * Статусы результатов очистки логов аудита.
 */
public enum AuditLogState {

   SUCCESS(1L, "Успешно"),
   NOT_SUCCESS(2L, "Неуспешно");

   private Long id;
   private String name;

   private AuditLogState(Long id, String name) {
	  this.setId(id);
	  this.setName(name);
   }

   public Long getId() {
	  return id;
   }

   private void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   private void setName(String name) {
	  this.name = name;
   }
}
