package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Посменная численность контролёров ГУП "Мосгортранс"
 * и среднее значение численности за период".
 */
public enum So5ValueCell {

   BRANCH_NAME(0),
   PLAN_COUNT(1),
   FACT_COUNT(2),
   DAYS_SUMMARIES_BASE(3),
   DAYS_TOTAL_SUMMARIES_INIT_BASE(3),
   AVERAGE_INIT(6);

   private final Integer col;

   So5ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
