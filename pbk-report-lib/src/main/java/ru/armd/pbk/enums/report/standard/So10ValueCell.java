package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сводная форма по эффективности работы контролеров".
 */
public enum So10ValueCell {

   CRITERION(0),
   CUR_PERIOD(1),
   PREV_PERIOD(2),
   TOTAL(3);

   private final Integer col;

   So10ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
