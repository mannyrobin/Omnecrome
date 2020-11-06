package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Cписок маршрутов АСМ-ПП".
 */
public enum So25ValueCell {

   WORK_DATE(0),
   ROUTE_NAME(1),
   TYPE_TS(2),
   STOPS(3);

   private final Integer col;

   So25ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
