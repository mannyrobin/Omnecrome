package ru.armd.pbk.enums.tasks;

/**
 * Ячейки печатной формы задания, содержащие его параметры.
 */
public enum TaskValueCell {

   BSO_NUMBER("B6"),
   TASK_DATE("H6"),
   EMPLOYEE_NAME("C8"),
   PERSONAL_NUMBER("I8"),
  // LICENSE_NUMBER("G14"),
  // PUSK_NUMBER("G18"),
  // WORK_START_HOUR("F20"),
  // WORK_START_MINUTE("I20"),
   WORK_START_TIME("E11"),
   WORK_END_TIME("G11"),
  // WORK_END_HOUR("F22"),
  // WORK_END_MINUTE("I22"),
   BREAK_START_TIME("J11"),
   BREAK_END_TIME("K11"),
   EMPLOYEE_SIGN("H23"),
   ROUTES("A16"),
  // SPECIAL_TASK("D40"),
  // LICENSE_END_DATE("F16"),
   SPECIAL_MARK("D21");

   private final String pos;

   TaskValueCell(String pos) {
	  this.pos = pos;
   }

   public String getPos() {
	  return pos;
   }
}
