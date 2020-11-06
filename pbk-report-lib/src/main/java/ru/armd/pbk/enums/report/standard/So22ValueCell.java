package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сверка с ГКУ ОП".
 */
public enum So22ValueCell {

   WORK_DATE_ID(0),
   DEPT_NAME(1),
   GKUOP_NAME(2),
   SHIFT_NAME(3),
   MGT_HEAD(4),
   GKUOP_HEAD(5);

   private final Integer col;

   So22ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
