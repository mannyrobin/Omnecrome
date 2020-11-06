package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Итоги ПБК по контролеру по данным АСУ ПБК".
 */
public enum So15ValueCell {

   CHECK_START_DATETIME(0),
   ROUTE_NUMBER(1),
   TS_OUTGO_NUMBER(2),
   EMPLOYEE(3),
   DEPARTMENT(4),
   CHECKED_PASSENGER_COUNT(5);

   private final Integer col;

   So15ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
