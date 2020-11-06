package ru.armd.pbk.enums.core;

/**
 * Константы для аудита отчётов по заданиям.
 */
public class TaskReportAuditType
	extends AuditType {

   public static final AuditType TASK_TASK_REPORT = new AuditType(4_001_02L, "Отчёты по заданиям");

   protected TaskReportAuditType(Long id, String name) {
	  super(id, name);
   }
}
