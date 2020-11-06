package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Количество перевезенных пассажиров по маршрутам".
 */
public enum So7ValueCell {

   BRANCH(0),
   COUNTY(1),
   ROUTE(2),
   CUR_PASSENGER_COUNT(3),
   PREV_PASSENGER_COUNT(4);

   private final Integer col;

   So7ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
