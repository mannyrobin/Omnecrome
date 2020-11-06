package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)
 * поостановочно".
 */
public enum So16ValueCell {

   STOP_NAME(0),
   ASKP_ARRIVAL_TIME(1),
   ASMPP_ARRIVAL_TIME(2),
   ASKP_PASSENGER_COUNT(3),
   ASMPP_PASSENGER_COUNT(4),
   DIFFS(5);

   private final Integer col;

   So16ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
