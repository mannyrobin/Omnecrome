package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Совместный график по местам встреч".
 */
public enum So12ValueCell {

   COUNTY(0),
   DISTRICT(1),
   VENUE(2),
   VENUE_HOURS(3),
   DAY_BASE(4);

   private final Integer col;

   So12ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
