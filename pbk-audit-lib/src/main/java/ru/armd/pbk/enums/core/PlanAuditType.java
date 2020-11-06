package ru.armd.pbk.enums.core;

/**
 * Аудит модуля plan.
 */
public class PlanAuditType
	extends AuditType {

   public static final AuditType PLAN_PLAN = new AuditType(3_001_00L, "Планы");
   public static final AuditType PLAN_SCHEDULE = new AuditType(3_001_01L, "Расписания планов отдела");
   public static final AuditType PLAN_BRIGADE = new AuditType(3_001_02L, "Планы бригад");
   public static final AuditType PLAN_ROUTE = new AuditType(3_001_03L, "Планы маршрутов для отдела");
   public static final AuditType PLAN_TASK = new AuditType(3_001_04L, "");
   public static final AuditType PLAN_TASK_ROUTE = new AuditType(3_001_05L, "");
   public static final AuditType PLAN_TIMESHEET = new AuditType(3_001_06L, "");

   protected PlanAuditType(Long id, String name) {
	  super(id, name);
   }
}
