package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Количество совместных бригад с ГКУ ОП".
 */
public enum So19ValueCell {

   NN(0),
   WORK_DATE(1),
   SHIFT(2),
   GKU_PODR_NAME(3),
   MGT_EMPLOYEES(4),
   GKU_EMPLOYEES(5),
   VENUE(6),
   COUNTY(7),
   ROUTES(8),
   BRKIND0(9),
   BRKIND11(10),
   BRKIND12(11),
   BRKIND13(12),
   BRKIND21(13),
   BRKIND22(14),
   BRKIND23(15),
   DOPMGT(16),
   DOPGKU(17),
   DOPMGTNOGKU(18),
   MGTCOUNTINDAY(19),
   BRCOMMON(20),
   MGTCOUNTINCOMMON(21),
   GKUCOUNTINCOMMON(22);


   private final Integer col;

   So19ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
