package ru.armd.pbk.enums.core;

/**
 * Константы для аудита заданий.
 */
public class TaskAuditType
	extends AuditType {

   public static final AuditType TASK_TASK = new AuditType(4_001_00L, "Задания");
   //public static final AuditType PLAN_TASK 				= new AuditType(4_001_01L, "План заданий");
   public static final AuditType PLAN_TASK_TYPE = new AuditType(4_001_02L, "План заданий");
   public static final AuditType TASK_TASK_STATE = new AuditType(4_001_03L, "Состояния заданий");
   public static final AuditType TASK_FILE = new AuditType(4_001_04L, "Файлы заданий");
   public static final AuditType TASK_ROUTE = new AuditType(4_001_05L, "Маршруты заданий");
   public static final AuditType TASK_EMPLOYEE = new AuditType(4_001_06L, "Сотрудники заданий");
   public static final AuditType TASK_WITHDRAWN_CARD = new AuditType(4_001_07L, "Изъятые карты");


   protected TaskAuditType(Long id, String name) {
	  super(id, name);
   }
}
