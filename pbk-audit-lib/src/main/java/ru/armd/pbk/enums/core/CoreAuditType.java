package ru.armd.pbk.enums.core;

/**
 * Аудит модуля core.
 */
public class CoreAuditType
	extends AuditType {

   public static final AuditType CORE_AUDIT = new AuditType(101_000L, "Журнал аудита");
   public static final AuditType CORE_SETTING = new AuditType(101_001L, "Системные настройки");
   public static final AuditType CORE_ROLE = new AuditType(101_002L, "Роли системы");
   public static final AuditType CORE_USER = new AuditType(101_003L, "Пользователи системы");
   public static final AuditType CORE_SESSION = new AuditType(101_004L, "Сессии пользователей системы");
   public static final AuditType CORE_AUDIT_TYPE = new AuditType(101_005L, "Журнал событий аудита");
   public static final AuditType CORE_MODULE = new AuditType(101_006L, "Модули системы");

   protected CoreAuditType(Long id, String name) {
	  super(id, name);
   }

}
