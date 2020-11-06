package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП".
 */
public enum So18ValueCell {

   DATE(0),
   EMPLOYEE(1),
   ROUTE_NUMBER(2),
   TS_CHECK_TASK_REPORT_COUNT(3),
   TS_CHECK_ASKP_COUNT(4);

   private final Integer col;

   So18ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
