package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Проходы по БСК контролера".
 */
public enum So21ValueCell {

   BRANCH(0),
   EMPLOYEE(1),
   BSK(2),
   DATETIMES(3),
   OPERATOR(4),
   ROUTE(5),
   RUN(6);

   private final Integer col;

   So21ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
