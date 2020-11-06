package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП".
 */
public enum So24ValueCell {

   TASK_DATE(0),
   EMPLOYEE(1),
   PERSONNEL_NUMBER(2),
   SHIFT_NAME(3),
   DEPARTMENT(4),
   BSO_NUMBER(5),
   TASK_STATE(6),
   IS_BSK(7),
   IS_VIDEO(8),
   IS_CLOSED(9);

   private final Integer col;

   So24ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
