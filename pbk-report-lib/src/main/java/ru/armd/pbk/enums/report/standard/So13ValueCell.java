package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Маршруты и выходы".
 */
public enum So13ValueCell {

   WORK_DATE(0),
   TICKET_TYPE_CODE(1),
   TICKET_TYPE_NAME(2),
   PASS_COUNTS_BASE(3),
   PASS_COUNTS_TOTAL(27);

   private final Integer col;

   So13ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
