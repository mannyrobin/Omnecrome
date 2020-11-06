package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Сводные данные по работе контролеров за период"
 * и среднее значение численности за период".
 */
public enum So23ValueCell {

   EMPLOYEE_NAME(0),
   PERSONAL_NUMBER(1),
   DAYS_SUMMARIES_BASE(2),
   DAYS_TOTAL_SUMMARIES_INIT_BASE(3);
   
   private final Integer col;

   So23ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
