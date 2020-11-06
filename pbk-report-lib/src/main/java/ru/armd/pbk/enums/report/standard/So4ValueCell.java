package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Ежедневная посменная численность контролёров ГУП "Мосгортранс"
 * по территориальному подразделению".
 */
public enum So4ValueCell {

   DATE(0),
   FIRST_SHIFT_COUNT(1),
   SECOND_SHIFT_COUNT(2),
   DAY_COUNT(3),
   NIGHT_COUNT(4),
   TOTAL_COUNT(5);

   private final Integer col;

   So4ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
