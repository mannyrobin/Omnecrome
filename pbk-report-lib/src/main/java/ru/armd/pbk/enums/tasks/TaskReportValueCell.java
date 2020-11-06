package ru.armd.pbk.enums.tasks;

/**
 * Ячейки печатной формы отчёта по заданию, содержащие его параметры.
 */
public enum TaskReportValueCell {

    EMPLOYEE_NAME("C107"),
    PERSONAL_NUMBER("G107");

   private final String pos;

   TaskReportValueCell(String pos) {
	  this.pos = pos;
   }

   public String getPos() {
	  return pos;
   }
}
