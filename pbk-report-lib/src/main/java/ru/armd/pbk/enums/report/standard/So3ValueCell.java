package ru.armd.pbk.enums.report.standard;

/**
 * Ячейки с данными для печатной формы отчёта "Количество бригад по местам встречи".
 */
public enum So3ValueCell {

   DATE(0),
   //COUNTY(1),
   //DISTRICT(2),
   VENUE(1),
   BRIGADE_TYPE(2),
   BRIGADE_COUNT(3);

   private final Integer col;

   So3ValueCell(Integer col) {
	  this.col = col;
   }

   public Integer getCol() {
	  return col;
   }
}
