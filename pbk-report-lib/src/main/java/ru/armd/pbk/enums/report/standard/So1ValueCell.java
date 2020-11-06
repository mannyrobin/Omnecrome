package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "График работы контролеров по территориальному подразделению".
 */
public enum So1ValueCell {

   DEPT_NAME(0),
   EMPLOYEE(1),
   PERSONAL_NUMBER(2),
   DAY_BASE(4);

   private final Integer col;

   So1ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
