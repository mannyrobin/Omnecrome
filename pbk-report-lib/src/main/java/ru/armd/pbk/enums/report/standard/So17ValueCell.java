package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)".
 */
public enum So17ValueCell {

   WORK_DATE(0),
   ROUTE_NUMBER(1),
   ASKP_PASSENGER_COUNT(2),
   ASMPP_PASSENGER_COUNT(3),
   DIFFS(4);

   private final Integer col;

   So17ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
