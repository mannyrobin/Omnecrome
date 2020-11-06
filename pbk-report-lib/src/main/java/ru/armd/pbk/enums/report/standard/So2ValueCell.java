package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Табель фактически отработанного времени".
 */
public enum So2ValueCell {

   DEPT_NAME(0),
   EMPLOYEE(1),
   PERSONAL_NUMBER(2),
   DAY_BASE(3);

   private final Integer col;

   So2ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
